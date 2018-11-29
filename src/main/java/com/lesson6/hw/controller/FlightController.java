package com.lesson6.hw.controller;

import com.lesson6.hw.models.Filter;
import com.lesson6.hw.models.Flight;
import com.lesson6.hw.service.FlightService;
import com.lesson6.hw.utils.JsonToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/flight")

public class FlightController {
    private FlightService flightService;
    private final JsonToModel<Flight> flightJsonToModel;
    private final JsonToModel<Filter> filterJsonToModel;

    @Autowired
    public FlightController(FlightService flightService,
                            JsonToModel<Flight> flightJsonToModel, JsonToModel<Filter> filterJsonToModel) {
        this.flightService = flightService;
        this.flightJsonToModel = flightJsonToModel;
        this.filterJsonToModel = filterJsonToModel;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = "text-plain")
    public @ResponseBody
    String getFlight(@RequestParam("id") String id) throws NullPointerException, NumberFormatException {
        try {
            return flightService.get(Long.parseLong(id)).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong, flight not found";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text-plain")
    public @ResponseBody
    String saveFlight(HttpServletRequest req) throws NullPointerException, IllegalArgumentException {
        Flight flight = flightJsonToModel.jsonToEntity(req, Flight.class);
        try {
            flightService.save(flight);
            return "flight saved";
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong";
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text-plain")
    public @ResponseBody
    String updateFlight(HttpServletRequest req) throws NullPointerException, IllegalArgumentException {
        Flight flight = flightJsonToModel.jsonToEntity(req, Flight.class);
        try {
            flightService.update(flight);
            return "flight with id: " + flight.getId() + " updated";
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong";
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text-plain")
    public @ResponseBody
    String deleteFlight(@RequestParam("id") String id) throws NullPointerException, IllegalArgumentException {
        try {
            flightService.delete(Long.parseLong(id));
            return "flight deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filter", produces = "text-plain")
    public @ResponseBody
    String getFlightsByDate(HttpServletRequest req) throws NullPointerException, IllegalArgumentException {
        Filter filter = filterJsonToModel.jsonToEntity(req, Filter.class);
        try {
            return flightService.filterFlights(filter).toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/most-popular-to", produces = "text-plain")
    public @ResponseBody
    String getMostPopularTo() throws NullPointerException, NumberFormatException {
        try {
            return flightService.mostPopularTo().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong, flight not found";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/most-popular-from", produces = "text-plain")
    public @ResponseBody
    String getMostPopularFrom() throws NullPointerException, NumberFormatException {
        try {
            return flightService.mostPopularFrom().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "something went wrong, flight not found";
        }
    }
}
