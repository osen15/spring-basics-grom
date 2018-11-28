package com.lesson6.hw.dao;

import com.lesson6.hw.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PassengerDao {

    @PersistenceContext
    private EntityManager entityManager;
    private Passenger passenger;

    @Autowired
    public PassengerDao(Passenger passenger) {
        this.passenger = passenger;
        }

        private String regular_Passengers = "select passenger from Passenger join";

    public void save(Passenger passenger) {
        entityManager.persist(passenger);
    }

    public void delete(Passenger passenger) {
        passenger = findById(passenger.getId());
        entityManager.remove(passenger);
    }

    public Passenger update(Passenger passenger) {
        return entityManager.merge(passenger);
    }

    public Passenger findById(Long id) throws NullPointerException {
        passenger = entityManager.find(Passenger.class, id);
        if (passenger == null)
            throw new NullPointerException("Passenger with id: " + id + " not found");
        return passenger;
    }


}
