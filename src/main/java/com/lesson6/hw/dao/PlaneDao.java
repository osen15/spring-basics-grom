package com.lesson6.hw.dao;

import com.lesson6.hw.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Repository
@Transactional
public class PlaneDao extends GeneralDAO<Plane> {

    @PersistenceContext
    private EntityManager entityManager;

    public Plane get(Long id) {
        return super.get(Plane.class, id);
    }

    public void save(Plane plane) {
        super.save(plane);
    }

    public Plane update(Plane plane) {
        return super.update(plane);
    }

    public void delete(Long id) {
        super.delete(Plane.class, id);
    }

    public ArrayList<Plane> oldPlanes() {
        Date date = java.sql.Date.valueOf(LocalDate.now().minusYears(20));

        ArrayList<Plane> planes = (ArrayList<Plane>) entityManager
                .createQuery("from Plane where yearProduced < :date")
                .setParameter("date", date)
                .getResultList();
        return planes;
    }
}
