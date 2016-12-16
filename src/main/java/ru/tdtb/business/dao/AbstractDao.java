package ru.tdtb.business.dao;

import java.io.Serializable;

public interface AbstractDao<T, IDType extends Serializable> {
    T get(IDType id);

    void update(T o);

    IDType save(T o);

    void delete(T o);

    void delete(IDType id);
}
