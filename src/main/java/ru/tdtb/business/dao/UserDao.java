package ru.tdtb.business.dao;

import ru.tdtb.business.domain.User;

public interface UserDao extends AbstractDao<User, Long> {
    User getByLogin(String login);

    User getByToken(String token);
}
