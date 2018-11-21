package com.lesson6.hw.controller;

import com.lesson6.hw.service.PlaneService;
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
    String getPlane(@RequestParam("id") String id) throws Exception {
        return planeService.get(Long.parseLong(id)).toString();

    }

    @RequestMapping(method = RequestMethod.GET, value = "/old-planes", produces = "text-plain")
    public @ResponseBody
    String getOldPlanes() throws Exception {
        return planeService.oldPlanes().toString();
        }
}