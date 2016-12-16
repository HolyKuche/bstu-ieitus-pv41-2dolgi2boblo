package ru.tdtb.application.security.rest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("tdtb-api-token");

        if (token != null) {
            if (SecurityContextHolder.getContext() == null)
                SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());

            SecurityContextHolder.getContext().setAuthentication(new TokenAuthentication(token));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
