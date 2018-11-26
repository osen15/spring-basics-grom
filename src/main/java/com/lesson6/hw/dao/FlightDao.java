package com.lesson6.hw.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesson6.hw.models.Filter;
import com.lesson6.hw.models.Flight;
import com.lesson6.hw.models.Plane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;


@Repository

public class FlightDao extends GeneralDAO<Flight> {

    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;


    private Flight flight;

    @Autowired
    public FlightDao(Flight flight) {
        this.flight = flight;
    }


    public void save(Flight flight) {
        entityManager.persist(flight);

    }


    public void delete(Long id) {
        flight = findById(id);
        entityManager.remove(flight);

    }


    public Flight update(Flight flight) {
        return entityManager.merge(flight);
    }

    public Flight findById(Long id) throws NullPointerException {
        return entityManager.find(Flight.class, id);
    }


    public HashSet<Flight> flightsByDate(Filter filter) {
        HashSet<Flight> flights;

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> filterParameters = objectMapper.convertValue(filter, Map.class);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> flightCriteria = criteriaBuilder.createQuery(Flight.class);
        Root<Flight> flightRoot = flightCriteria.from(Flight.class);
        Join<Plane, Flight> planeJoin = flightRoot.join("plane");
        Predicate predicate = criteriaBuilder.conjunction();
        for (String parameter : filterParameters.keySet()) {
            if (filterParameters.get(parameter) != null) {
                if (parameter.equals("dateFlight")
                        || parameter.equals("cityFrom")
                        || parameter.equals("cityTo")
                        || parameter.equals("plane")) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder
                            .equal(planeJoin.get(parameter), filterParameters.get(parameter)));
                }
                if (filter.getStartDate() != null && filter.getFinishDate() != null) {
                    predicate = criteriaBuilder
                            .and(predicate, criteriaBuilder
                                    .between(planeJoin.get("dateFlight"), filter.getStartDate(), filter.getFinishDate()));
                } else {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder
                            .equal(flightRoot.get(parameter), filterParameters.get(parameter)));
                }
            }
        }
        flightCriteria.select(flightRoot).where(predicate);
        flights = (HashSet<Flight>) entityManager.createQuery(flightCriteria).getResultList();
        return flights;

    }
}



