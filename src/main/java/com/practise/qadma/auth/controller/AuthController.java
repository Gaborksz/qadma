package com.practise.qadma.auth.controller;

import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.auth.payload.QadmaUserDTO;
import com.practise.qadma.auth.payload.SignInDTO;
import com.practise.qadma.auth.payload.SignUpDTO;
import com.practise.qadma.auth.service.QadmaAuthenticationService;
import com.practise.qadma.exception.InvalidJWTokenException;
import com.practise.qadma.exception.UserExistException;
import com.practise.qadma.util.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserDetailsManager userDetailsManager;
    private final QadmaAuthenticationService authenticationService;
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthController(UserDetailsManager userDetailsManager, QadmaAuthenticationService authenticationService, JWTUtil jwtUtil) {
        this.userDetailsManager = userDetailsManager;
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public ResponseEntity<QadmaUserDTO> signin(@RequestBody SignInDTO signInCredentials,
                                               HttpServletRequest request,
                                               HttpServletResponse response) {

        //TODO
        // Return true / false and let frontend extract user data for access to app features. Same for /signedin


        QadmaUserDTO userDTO = new QadmaUserDTO();
        String token;
        Optional<Cookie> jwtCookie = extractCookie(request, "jwt");

        if (jwtCookie.isEmpty()) {
            userDTO = authenticationService.authenticate(signInCredentials);
            token = jwtUtil.generateToken(userDTO);
        } else {
            token = jwtCookie.get().getValue();
            if (jwtUtil.verifyToken(token)) userDTO = authenticationService.getCurrentUser();
        }

        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(3600)
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(userDTO);
    }


    @PostMapping("signup")
    public ResponseEntity<Boolean> signUp(@RequestBody SignUpDTO signUpCredentials) throws UserExistException {

        if (userDetailsManager.userExists(signUpCredentials.getUserName())) {
            throw new UserExistException("username: " + signUpCredentials.getUserName() + " taken");
        }

        QadmaUser newUser = QadmaAuthenticationService.buildBasicUser(signUpCredentials);

        userDetailsManager.createUser(newUser);

        return ResponseEntity.ok(true);
    }


    @GetMapping("signout")
    public String signOut() {

        //TODO
        // Add token to BlackList that gets verified by the JWTFilter

        return "You have successfully signed out";
    }

    @GetMapping("signedin")
    public ResponseEntity<QadmaUserDTO> isSignedIn(HttpServletRequest request) throws InvalidJWTokenException {

        //TODO
        // Return true / false and let frontend extract user data for access to app feature. Same for /signin

        Optional<Cookie> jwtCookie = extractCookie(request, "jwt");

        if (jwtCookie.isEmpty() || !jwtUtil.verifyToken(jwtCookie.get().getValue())) {
            throw new InvalidJWTokenException();
        }

        QadmaUserDTO user = authenticationService.getCurrentUser();

        return ResponseEntity.ok(user);
    }


    @PostMapping("user-name")
    public boolean userExists(@RequestBody String userName) {
        return !userDetailsManager.userExists(userName);
    }


    private static Optional<Cookie> extractCookie(HttpServletRequest request, String cookieName) {
        List<Cookie> cookies = request.getCookies() != null ?
                List.of(request.getCookies()) : new ArrayList<>();

        return cookies.stream()
                .filter(c -> c.getName().equalsIgnoreCase(cookieName))
                .findFirst();
    }
}