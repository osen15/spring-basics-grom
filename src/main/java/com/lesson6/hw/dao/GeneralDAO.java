package com.lesson6.hw.dao;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository
public abstract class GeneralDAO<T> {


    @PersistenceContext
    private EntityManager entityManager;


    public void settClass(Class<T> tClass) {
        Class<T> tClass1 = tClass;
    }

    public T get(Class<T> tClass, Long id) {
        return entityManager.find(tClass, id);
    }


    public void save(T t) {
        entityManager.persist(t);

    }


    public T update(T t) {
        return entityManager.merge(t);
    }


    public void delete(Class<T> tClass, long id) {
        entityManager.remove(get(tClass, id));
    }



}
