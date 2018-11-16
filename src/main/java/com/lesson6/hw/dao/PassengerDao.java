package com.lesson6.hw.dao;

import com.lesson6.hw.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class PassengerDao implements GeneralDao<Passenger> {

    @PersistenceContext
    private EntityManager entityManager;
    private Passenger passenger;

    @Autowired
    public PassengerDao(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public void save(Passenger passenger) {
        entityManager.persist(passenger);
    }

    @Override
    public void delete(Passenger passenger) {
        passenger = findById(passenger.getId());
        entityManager.remove(passenger);

    }

    @Override
    public Passenger update(Passenger passenger) {
        return entityManager.merge(passenger);
    }

    @Override
    public Passenger findById(Long id) throws NullPointerException {
        passenger = entityManager.find(Passenger.class, id);
        if (passenger == null)
            throw new NullPointerException("Passenger with id: " + id + " not found");
        return passenger;
    }





}
