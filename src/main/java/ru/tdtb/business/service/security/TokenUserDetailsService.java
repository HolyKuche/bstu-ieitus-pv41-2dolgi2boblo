package ru.tdtb.business.service.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenUserDetailsService {
    UserDetails loadUserByToken(String token);

    String generateToken();
}
