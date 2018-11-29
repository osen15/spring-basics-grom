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

    private final String old_Planes = "FROM Plane WHERE yearProduced < :date";
    private final String regular_Planes = "SELECT p.ID FROM PLANE p INNER JOIN FLIGHT f ON p.ID = f.PLANE_ID\n" +
            "WHERE f.DATE_FLIGHT BETWEEN ADD_MONTHS(CURRENT_DATE, -12) AND CURRENT_DATE\n" +
            "HAVING COUNT(DISTINCT f.ID) >= 300 GROUP BY p.ID\n";

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
                .createQuery(old_Planes)
                .setParameter("date", date)
                .getResultList();
        return planes;
    }

    public ArrayList<Plane> regularPlanes(){

        ArrayList<Plane> planes = (ArrayList<Plane>) entityManager
                .createNativeQuery(regular_Planes, Plane.class)
                .getResultList();
        return planes;


    }
}
