package ru.tdtb.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.tdtb.business.dao.UserDao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class AbstractService {
    //protected ZonedDateTimeConverter zonedDateTimeConverter = new ZonedDateTimeConverter();

    @Autowired
    private UserDao userDao;

    private String getCurrentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        return user.getUsername();
    }

    protected ru.tdtb.business.domain.User getCurrentUser() {
        String currentUserName = getCurrentUserName();
        ru.tdtb.business.domain.User user = userDao.getByLogin(currentUserName);
        if (user == null) {
            throw new UsernameNotFoundException(currentUserName);
        }
        return user;
    }

    LocalDate getSysDate() {
        return LocalDate.now();
    }

    LocalDateTime getSysTime() {
        return LocalDateTime.now();
    }
/*
    protected Date convertToDate(LocalDateTime ldt) {
        Instant instant = ldt.atZone(SYSTEM_TIMEZONE_ID).toInstant();
        return Date.from(instant);
    }

    protected LocalDateTime convertToLocalDateTime(Date ts) {
        Instant instant = Instant.ofEpochMilli(ts.getTime());
        return LocalDateTime.ofInstant(instant, SYSTEM_TIMEZONE_ID);
    }
    */
}