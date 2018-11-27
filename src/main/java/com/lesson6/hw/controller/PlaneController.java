package com.lesson6.hw.controller;


import com.lesson6.hw.models.Plane;
import com.lesson6.hw.service.PlaneService;
import com.lesson6.hw.utils.JsonToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/plane")
public class PlaneController {
    private PlaneService planeService;
    private final JsonToModel<Plane> jsonToModel;


    @Autowired
    public PlaneController(PlaneService planeService, JsonToModel<Plane> jsonToModel) {
        this.planeService = planeService;

        this.jsonToModel = jsonToModel;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = "text-plain")
    public @ResponseBody
    String getPlane(@RequestParam("id") String id) throws Exception {
        return planeService.get(Long.parseLong(id)).toString();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text-plain")
    public @ResponseBody
    String savePlane(HttpServletRequest req) throws Exception {
        Plane plane = jsonToModel.jsonToEntity(req, Plane.class);
        System.out.println(plane.toString());
        planeService.save(plane);
        return "the plane is saved";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text-plain")
    public @ResponseBody
    String deletePlane(@RequestParam("id") String id) throws Exception {
        planeService.delete(Long.parseLong(id));
        return "plane with id: " + id + " deleted";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text-plain")
    String updatePlane(HttpServletRequest req) throws Exception {
        Plane plane = jsonToModel.jsonToEntity(req, Plane.class);
        planeService.update(plane);
        return "plane with id : " + plane.getId() + " updated";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/old-planes", produces = "text-plain")
    public @ResponseBody
    String getOldPlanes() throws Exception {
        return planeService.oldPlanes().toString();
    }


}