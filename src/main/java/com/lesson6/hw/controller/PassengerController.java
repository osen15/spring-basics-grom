package com.lesson6.hw.controller;

import com.lesson6.hw.dao.PassengerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PassengerController {

    private PassengerDao passengerDao;

    @Autowired
    public PassengerController(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-passenger", produces = "text-plain")
    public @ResponseBody
    String getFlight(@RequestParam("id") String id) {
        return passengerDao.findById(Long.parseLong(id)).toString();

    }


}
