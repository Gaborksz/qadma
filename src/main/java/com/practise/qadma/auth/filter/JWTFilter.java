package com.practise.qadma.auth.filter;

import com.practise.qadma.QadmaApplication;
import com.practise.qadma.auth.entity.JWTAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO
// Check that the token is on a BLackList in the DB (/singout implementation)

//TODO
// If someone is trying to sign up and sends a valid jwt, should token be blacklisted?

public class JWTFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final List<String> ignoredUrls;

    @Autowired
    public JWTFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.ignoredUrls = getIgnoredUrlList();
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestURl = request.getRequestURL().toString();
        Optional<Cookie> jwtCookie = extractJWTCookie(request, "jwt");

        if (!ignoredUrls.contains(requestURl) && jwtCookie.isPresent()) {

            JWTAuthenticationToken unVerifiedToken =
                    new JWTAuthenticationToken(jwtCookie.get().getValue());

            Authentication authenticatedUser = authenticationManager.authenticate(unVerifiedToken);

            SecurityContext newContext = SecurityContextHolder.createEmptyContext();
            newContext.setAuthentication(authenticatedUser);
            SecurityContextHolder.setContext(newContext);
        }

        filterChain.doFilter(request, response);
    }

    private static Optional<Cookie> extractJWTCookie(HttpServletRequest request, String tokenName) {
        List<Cookie> cookies = request.getCookies() != null ?
                List.of(request.getCookies()) : new ArrayList<>();


        return cookies.stream()
                .filter(c -> c.getName().equalsIgnoreCase(tokenName))
                .findFirst();
    }

    private List<String> getIgnoredUrlList() {

        return List.of(
                QadmaApplication.BASEURL + "/api/auth/signup",
                QadmaApplication.BASEURL + "/api/auth/user-name");
    }
}
