package com.lesson6.hw.dao;

import com.lesson6.hw.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;

@Repository
@Transactional
public class PlaneDao implements GeneralDao<Plane> {

    @PersistenceContext
    private EntityManager entityManager;

    private Plane plane;

    @Autowired
    public PlaneDao(Plane plane) {
        this.plane = plane;
    }


    @Override
    public void save(Plane plane) {
        entityManager.persist(plane);

    }

    @Override
    public void delete(Plane plane) {
        plane = findById(plane.getId());
        entityManager.remove(plane);

    }

    @Override
    public Plane update(Plane plane) {
        return entityManager.merge(plane);
    }

    @Override
    public Plane findById(Long id) throws NullPointerException {
        plane = entityManager.find(Plane.class, id);
        if (plane == null)
            throw new NullPointerException("Plane with id: " + id + "not found");
        return plane;

    }


    public ArrayList<Plane> oldPlanes() {
        LocalDate date = LocalDate.now().minusYears(20);
        ArrayList<Plane> planes = (ArrayList<Plane>) entityManager
                .createQuery("from Plane where yearProduced < :date")
                .setParameter("date", date)
                .getResultList();
        return planes;


    }

}
