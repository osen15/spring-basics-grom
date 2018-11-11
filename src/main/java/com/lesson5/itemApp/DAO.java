package com.lesson5.itemApp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class DAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Item save(Item item) {
        entityManager.persist(item);
        return item;
    }

    public void delete(long id) {
        entityManager.remove(findById(id));

    }

    public Item update(Item item) {
        entityManager.merge(item);
        return item;
    }

    public Item findById(long id) {
        return entityManager.find(Item.class, id);


    }


}
