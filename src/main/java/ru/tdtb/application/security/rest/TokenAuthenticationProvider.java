package ru.tdtb.application.security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import ru.tdtb.business.service.security.TokenUserDetailsService;

public class TokenAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private TokenUserDetailsService service;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        TokenAuthentication tokenAuth = (TokenAuthentication) auth;
        if (auth.isAuthenticated()) return auth;
        String token = tokenAuth.getToken();
        if (token == null) return null;
        UserDetails userDetails = service.loadUserByToken(token);
        return new PreAuthenticatedAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(TokenAuthentication.class);
    }
}
