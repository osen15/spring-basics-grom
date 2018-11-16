package com.lesson6.hw.dao;

import com.lesson6.hw.models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class FlightDao implements GeneralDao<Flight> {

    @PersistenceContext
    private EntityManager entityManager;

    private Flight flight;

    public FlightDao(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void save(Flight flight) {
        entityManager.persist(flight);

    }

    @Override
    public void delete(Flight flight) {
        flight = findById(flight.getId());
        entityManager.remove(flight);

    }

    @Override
    public Flight update(Flight flight) {
        return entityManager.merge(flight);
    }

    @Override
    public Flight findById(Long id) throws NullPointerException {
        flight = entityManager.find(Flight.class, id);
        if (flight == null)
            throw new NullPointerException("Flight with id: " + id + " not found");
        return flight;
    }
}
