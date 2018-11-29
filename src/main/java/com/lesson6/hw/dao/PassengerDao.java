package com.lesson6.hw.dao;

import com.lesson6.hw.models.Passenger;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;

@Repository
public class PassengerDao {

    @PersistenceContext
    private EntityManager entityManager;
    private Passenger passenger;

    private final String regular_Passengers = "SELECT\n" +
            "  p.ID,\n" +
            "  p.LAST_NAME,\n" +
            "  p.NATIONALITY,\n" +
            "  p.DATE_OF_BIRTH,\n" +
            "  p.PASS_CODE\n" +
            "FROM PASSENGER p INNER JOIN \n" +
            "FLIGHT_AND_PASSENGER fp ON p.ID = fp.PASSENGER_ID\n" +
            "JOIN FLIGHT f ON fp.FLIGHT_ID = f.ID\n" +
            "WHERE f.DATE_FLIGHT BETWEEN ADD_MONTHS(CURRENT_DATE , -12) and CURRENT_DATE \n" +
            "HAVING COUNT(DISTINCT f.ID) >= 1  GROUP BY p.ID, p.LAST_NAME, p.NATIONALITY, p.DATE_OF_BIRTH, p.PASS_CODE";


    @Autowired
    public PassengerDao(Passenger passenger) {
        this.passenger = passenger;
    }


    public void save(Passenger passenger) {
        entityManager.persist(passenger);
    }

    public void delete(Long id) {
        passenger = findById(id);
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

    public ArrayList<Passenger> regularPassengers() {
      return (ArrayList<Passenger>) entityManager
              .createNativeQuery(regular_Passengers, Passenger.class).getResultList();

    }


}
