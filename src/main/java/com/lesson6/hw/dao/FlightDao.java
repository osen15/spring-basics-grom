package com.lesson6.hw.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesson6.hw.models.Filter;
import com.lesson6.hw.models.Flight;
import com.lesson6.hw.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Map;


@Repository
public class FlightDao extends GeneralDAO<Flight> {
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

    public ArrayList<Flight> flightsByDate(Filter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> flightCriteria = criteriaBuilder.createQuery(Flight.class);
        Root<Flight> from = flightCriteria.from(Flight.class);
        Join<Flight, Plane> planeJoin = from.join("plane");
        Predicate predicate = criteriaBuilder.conjunction();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> stringObjectMap = objectMapper.convertValue(filter, Map.class);
        System.out.println(stringObjectMap.toString());
        for (String parameter : stringObjectMap.keySet()) {
            if (stringObjectMap.get(parameter) != null) {
                if (parameter.equals("dateFlight")
                        || parameter.equals("cityFrom")
                        || parameter.equals("cityTo")) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder
                            .equal(from.get(parameter), stringObjectMap.get(parameter)));
                }
                if (filter.getStartDate() != null && filter.getFinishDate() != null) {
                    predicate = criteriaBuilder
                            .and(predicate, criteriaBuilder
                                    .between(from.get("dateFlight"), filter.getStartDate(), filter.getFinishDate()));
                }
                if (filter.getPlaneName() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder
                            .equal(planeJoin.get("name"), filter.getPlaneName()));
                }
            }
        }
        flightCriteria.select(from).where(predicate);
        return (ArrayList<Flight>) entityManager.createQuery(flightCriteria).getResultList();
    }
}