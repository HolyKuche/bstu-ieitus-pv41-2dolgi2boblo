package ru.tdtb.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.tdtb.business.dao.UserDao;
import ru.tdtb.business.domain.User;
import ru.tdtb.application.security.CustomUserDetails;
import ru.tdtb.business.service.AbstractService;
import ru.tdtb.business.service.TokenUserDetailsService;
import ru.tdtb.business.service.exception.InvalidApiTokenException;

import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
public class TokenUserDetailsServiceImpl extends AbstractService implements UserDetailsService, TokenUserDetailsService{
    @Autowired
    private UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.getByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return map(user);
    }

    @Override
    public UserDetails loadUserByToken(String token) {
        User user = dao.getByToken(token);
        if (user == null) {
            throw new InvalidApiTokenException(token);
        }
        return map(user);
    }

    @Override
    public String generateToken() {
        User currentUser = getCurrentUser();
        String token = UUID.randomUUID().toString();
        currentUser.setApiToken(token);
        return token;
    }

    private CustomUserDetails map(User user) {
        return new CustomUserDetails(
                user.getLogin(),
                user.getHashPass(),
                true,
                true,
                true,
                true,
                user.getRoles().stream().map(role ->
                        new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()
                ),
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
