package com.lesson6.hw.service;

import org.springframework.stereotype.Component;

@Component
public abstract class GeneralService<T> {

    public abstract T get(Long id) throws Exception;

    public abstract void save(T t) throws Exception;

    public abstract T update(T t);

    public abstract void delete(Long id);

}
