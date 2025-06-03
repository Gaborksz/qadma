package com.practise.qadma.auth.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

@Getter
@Setter
public class JWTAuthenticationToken extends AbstractAuthenticationToken {

    private String token;

    public JWTAuthenticationToken(String token) {
        super(new ArrayList<>());
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        throw new RuntimeException("Cannot be set");
    }
}
