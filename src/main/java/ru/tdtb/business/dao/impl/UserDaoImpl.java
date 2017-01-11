package ru.tdtb.business.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.tdtb.business.dao.UserDao;
import ru.tdtb.business.domain.User;

import java.util.List;

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

    @Override
    public List<User> getBySearchString(String searchString) {
        Criteria criteria = newCriteria(User.class);
        Criterion rest1 = Restrictions.like("firstName", searchString, MatchMode.ANYWHERE),
                rest2 = Restrictions.like("lastName", searchString, MatchMode.ANYWHERE),
                rest3 = Restrictions.like("login", searchString, MatchMode.ANYWHERE);
        Criterion or1 = Restrictions.or(rest1, rest2),
                or2 = Restrictions.or(or1, rest3);
        return criteria.add(Restrictions.or(or2)).list();
    }
}
