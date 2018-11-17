package com.lesson6.hw;

import com.lesson6.hw.dao.PlaneDao;
import com.lesson6.hw.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service

public class PlaneService {

    private PlaneDao planeDao;

    @Autowired
    public PlaneService(PlaneDao planeDao) {
        this.planeDao = planeDao;
    }

    public Plane getPlane(Long id) {
        try {
            return planeDao.findById(id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
