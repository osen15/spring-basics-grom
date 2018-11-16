package com.lesson6.hw.controller;

import com.lesson6.hw.dao.PlaneDao;
import com.lesson6.hw.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class PlaneController {


    private Plane plane;
    private PlaneDao planeDao;


    @Autowired
    public PlaneController(Plane plane) {
        this.plane = plane;
    }

    @Autowired
    public PlaneController(PlaneDao planeDao) {
        this.planeDao = planeDao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-plane", produces = "text-plain")
    public @ResponseBody
    String getPlane(@RequestParam("id") String id) {
      plane = planeDao.findById(Long.parseLong(id));
      return plane.toString();
            }


}
