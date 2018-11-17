package com.lesson6.hw.controller;

import com.lesson6.hw.PlaneService;
import com.lesson6.hw.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PlaneController {
    private PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-plane", produces = "text-plain")
    public @ResponseBody
    String getPlane(@RequestParam("id") String id) {
        Plane plane = planeService.getPlane(Long.parseLong(id));
        return plane.toString();
    }
}
