package com.lesson6.hw.service;

import com.lesson6.hw.dao.PlaneDao;
import com.lesson6.hw.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class PlaneService extends GeneralService<Plane> {

    private PlaneDao planeDao;

    @Autowired
    public PlaneService(PlaneDao planeDao) {
        this.planeDao = planeDao;
    }


    @Override
    public Plane get(Long id) throws Exception {
        Plane plane = planeDao.get(id);
        if (plane == null) {
            throw new Exception("Something went wrong. " +
                    "Plane with id: " + id + " not found");
        }

        return plane;
    }

    @Override
    public void save(Plane plane)throws Exception {
        if (plane == null){
            throw  new Exception("Something went wrong: plain is null");
        }
        planeDao.save(plane);

    }

    @Override
    public Plane update(Plane plane) {
       return planeDao.update(plane);
    }

    @Override
    public void delete(Long id) {
        planeDao.delete(id);

    }

//    public Plane getPlane(Long id) throws Exception {
//        Plane plane = planeDao.get(id);
//        if (plane == null) {
//            throw new Exception("Something went wrong. " +
//                    "Plane with id: " + id + " not found");
//        }
//
//        return plane;
//    }

    public ArrayList<Plane> oldPlanes() throws Exception {

        ArrayList<Plane> planes = planeDao.oldPlanes();
        if (planes == null || planes.isEmpty()) {
            throw new Exception("Something went wrong: old planes not found");
        }
        return planes;


    }


}


