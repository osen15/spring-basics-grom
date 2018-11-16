package com.lesson6.hw.dao;


public interface GeneralDao<T> {

    void save(T t);

    void delete(T t);

    T update(T t);

    T findById(Long id);
}
