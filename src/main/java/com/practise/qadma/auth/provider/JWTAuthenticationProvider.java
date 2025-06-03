package com.practise.qadma.auth.provider;

import com.practise.qadma.auth.entity.JWTAuthenticationToken;
import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.auth.payload.QadmaUserDTO;
import com.practise.qadma.exception.InvalidJWTokenException;
import com.practise.qadma.util.JWTUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public JWTAuthenticationProvider(JWTUtil jwtUtil, ModelMapper modelMapper) {
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JWTAuthenticationToken jwtAuthentication = (JWTAuthenticationToken) authentication;

        if (!jwtUtil.verifyToken(jwtAuthentication.getToken())) {
            throw new InvalidJWTokenException();
        }

        QadmaUserDTO userDTO = jwtUtil.extractUser(jwtAuthentication.getToken());
        QadmaUser user = modelMapper.map(userDTO, QadmaUser.class);


        //TODO
        // Could do a check against a date (date of last change to authorities for example).
        // If the token was issued prior to this "last change date to authorities" the user should be queried from the DB
        // so user would be allowed to enter using the up-to date authorities
        // (not the ones from the token because those could be out of date)

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
