package ru.tdtb.business.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.tdtb.business.dao.UserDao;
import ru.tdtb.business.domain.User;

@Repository
@SuppressWarnings("uncheked")
public class UserDaoImpl extends AbstractDaoImpl<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User getByLogin(String login) {
        return (User)newCriteria(User.class)
                .add(Restrictions.eq("login", login))
                .setCacheable(true)
                .uniqueResult();
    }

    @Override
    public User getByToken(String token) {
        return (User)newCriteria(User.class)
                .add(Restrictions.eq("apiToken", token))
                .setCacheable(true)
                .uniqueResult();
    }
}
