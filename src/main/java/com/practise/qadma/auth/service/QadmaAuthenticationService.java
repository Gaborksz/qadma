package com.practise.qadma.auth.service;

import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.auth.entity.QadmaUserAuthority;
import com.practise.qadma.auth.entity.QadmaUserAuthorityType;
import com.practise.qadma.auth.payload.QadmaUserDTO;
import com.practise.qadma.auth.payload.SignInDTO;
import com.practise.qadma.auth.payload.SignUpDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QadmaAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;


    @Autowired
    public QadmaAuthenticationService(AuthenticationManager authenticationManager, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;

    }

    public QadmaUserDTO authenticate(SignInDTO signInCredentials) {
        UsernamePasswordAuthenticationToken auth
                = UsernamePasswordAuthenticationToken.unauthenticated(signInCredentials.getUserName(), signInCredentials.getPassword());

        Authentication authenticatedUser = this.authenticationManager.authenticate(auth);

        QadmaUser user = (QadmaUser) authenticatedUser.getPrincipal();
        user.setPassword(null);

        setSecurityContext(authenticatedUser);

        return modelMapper.map(user, QadmaUserDTO.class);
    }

    public QadmaUserDTO getCurrentUser() {
        return modelMapper.map(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                , QadmaUserDTO.class);
    }

    public static QadmaUser buildBasicUser(SignUpDTO signUpCredentials) {

        QadmaUserAuthority basicAuthority = new QadmaUserAuthority(QadmaUserAuthorityType.BASIC);
        basicAuthority.setId(QadmaUserAuthorityType.BASIC.getId());

        return new QadmaUser(
                0,
                signUpCredentials.getUserName(),
                signUpCredentials.getPassword(),
                List.of(basicAuthority));
    }

    private static void setSecurityContext(Authentication authenticatedUser) {
        SecurityContext newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(authenticatedUser);
        SecurityContextHolder.setContext(newContext);
    }
}
