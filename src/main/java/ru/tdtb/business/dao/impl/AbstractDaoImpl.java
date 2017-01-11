package ru.tdtb.business.dao.impl;

import lombok.Getter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.tdtb.business.dao.AbstractDao;

import javax.annotation.Resource;
import java.io.Serializable;

@SuppressWarnings("unchecked")
public abstract class AbstractDaoImpl<T, IDType extends Serializable> implements AbstractDao<T, IDType> {
    @Resource
    private SessionFactory sessionFactory;

    @Getter
    private Class<T> type;

    public AbstractDaoImpl(Class<T> domainType) {
        this.type = domainType;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected final Criteria newCriteria(Class<T> c) {
        return getCurrentSession().createCriteria(c);
    }

    @Override
    public T get(IDType id) {
        return (T) getCurrentSession().get(getType(), id);
    }

    @Override
    public void update(T o) {
        getCurrentSession().merge(o);
    }

    @Override
    public IDType save(T obj) {
        return (IDType) getCurrentSession().save(obj);
    }

    @Override
    public void delete(T obj) {
        getCurrentSession().delete(obj);
    }

    @Override
    public void delete(IDType id) {
        delete(get(id));
    }
}
