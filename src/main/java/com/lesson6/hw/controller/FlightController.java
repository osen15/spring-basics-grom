package com.lesson6.hw.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lesson6.hw.models.Filter;
import com.lesson6.hw.models.Flight;
import com.lesson6.hw.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@Controller
@RequestMapping(value = "/flight")

public class FlightController {
    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = "text-plain")
    public @ResponseBody
    String getFlight(@RequestParam("id") String id) throws NullPointerException, NumberFormatException {
        try {
            return flightService.get(Long.parseLong(id)).toString();
        } catch (NullPointerException | NumberFormatException e) {
            return "something went wrong, flight not found";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text-plain")
    public @ResponseBody
    String saveFlight(HttpServletRequest req) throws Exception {
        Flight flight = (Flight) jsonToEntity(req);
        flightService.save(flight);
        return "flight saved";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update", produces = "text-plain")
    public @ResponseBody
    String updateFlight(HttpServletRequest req) {
        Flight flight = (Flight) jsonToEntity(req);
        flightService.update(flight);
        return "flight updated";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete", produces = "text-plain")
    public @ResponseBody
    String deleteFlight(@RequestParam("id") String id) {
        flightService.delete(Long.parseLong(id));
        return "flight deleted";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filter", produces = "text-plain")
    public @ResponseBody
    String getFlightsByDate(HttpServletRequest req) throws Exception {
        Filter filter = (Filter) jsonToEntity(req);
        return flightService.filterFlights(filter).toString();

    }


    private Object jsonToEntity(HttpServletRequest req) {
        StringBuilder stb = new StringBuilder();
        Gson gson = new GsonBuilder().create();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null)
                stb.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(stb.toString(), Object.class);
    }


}
