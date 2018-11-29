package com.lesson6.hw.controller;


import com.lesson6.hw.models.Flight;
import com.lesson6.hw.models.Passenger;
import com.lesson6.hw.service.PassengerService;
import com.lesson6.hw.utils.JsonToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Transactional
@RequestMapping(value = "/passenger")
public class PassengerController {

    private PassengerService passengerService;
    private JsonToModel<Passenger> passengerJsonToModel;

    @Autowired
    public PassengerController(PassengerService passengerService, JsonToModel<Passenger> passengerJsonToModel) {
        this.passengerService = passengerService;
        this.passengerJsonToModel = passengerJsonToModel;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = "text-plain")
    public @ResponseBody
    String getFlight(@RequestParam("id") String id) throws NullPointerException, NumberFormatException {
        try {
            return passengerService.get(Long.parseLong(id)).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text-plain")
    public @ResponseBody
    String savePassenger(HttpServletRequest req) throws NullPointerException, IllegalArgumentException {
        Passenger passenger = passengerJsonToModel.jsonToEntity(req, Passenger.class);
        System.out.println(passenger.toString());
        try {
            passengerService.save(passenger);
            return "passenger saved";
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong";
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text-plain")
    public @ResponseBody
    String deletePassenger(@RequestParam("id") String id) throws NullPointerException, NumberFormatException {
        try {
            passengerService.delete(Long.parseLong(id));
            return "passenger with id: " + id + " deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong";
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text-plain")
    public @ResponseBody
    String updatePassenger(HttpServletRequest req) throws NullPointerException, IllegalArgumentException {
        Passenger passenger = passengerJsonToModel.jsonToEntity(req, Passenger.class);
        try {
            passengerService.update(passenger);
            return "passenger with id: " + passenger.getId() + " updated";
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/regularPass", produces = "text-plain")
    public @ResponseBody
    String getRegularPassengers() {
        try {
            return passengerService.regularPassengers().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong";
        }
    }
}
