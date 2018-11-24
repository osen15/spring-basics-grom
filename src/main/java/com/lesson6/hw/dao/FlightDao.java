package com.lesson6.hw.dao;

import com.lesson6.hw.models.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;


@Repository
@Transactional
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


        try {

            return entityManager.find(Flight.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

//            flight = entityManager.find(Flight.class, id);
//            if (flight == null)
//                throw new NullPointerException("Flight with id: " + id + " not found");

        return null;
    }


    public ArrayList<Flight> flightsByDate() {
        ArrayList<Flight> flights;

        //ObjectMapper objectMapper = new ObjectMapper();

        //  Map<String, Object> stringObjectMap = objectMapper.convertValue(filter, Map.class);


        flights = (ArrayList<Flight>) entityManager.createQuery("from Flight").getResultList();
        return flights;


//        Predicate where = criteriaBuilder.conjunction();
        //return (ArrayList<Flight>) query.getResultList();
//
//
//        for (String param : stringObjectMap.keySet()) {
//            if (stringObjectMap.get(param) != null) {
//                if (param.equals("startDate") && param.equals("finishDate"))
//
//                    where = criteriaBuilder
//                            .between(from.get("dateFlight"), filter.getStartDate(), filter.getFinishDate());
//
//                if (param.equals("dateFlight") || param.equals("cityFrom")
//                        || param.equals("cityTo") || param.equals("plane"))
//                where = criteriaBuilder
//                        .and(where, criteriaBuilder.equal(join.get(param), stringObjectMap.get(param)));
//
//
//
//            } else where = criteriaBuilder
//                    .and(where, criteriaBuilder.equal(from.get(param), stringObjectMap.get(param)));
//
//
//        }

//         flights = (TreeSet<Flight>) entityManager
//                 .createQuery(flightCriteriaQuery
//                         .select(from).where(where))
//                 .getResultList();
//        return flights;
//
//
    }


    public SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }


}



