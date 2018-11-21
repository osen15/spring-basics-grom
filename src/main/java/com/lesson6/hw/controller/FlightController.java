package com.lesson6.hw.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lesson6.hw.models.Filter;
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
public class FlightController {
    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    // Filter filter = jsonToEntity(request);
    @RequestMapping(method = RequestMethod.GET, value = "/get-flight", produces = "text-plain")
    public @ResponseBody
    String getFlight(@RequestParam("id") String id) throws Exception {
        return flightService.get(Long.parseLong(id)).toString();

    }


    private Filter jsonToEntity(HttpServletRequest req) {
        StringBuilder stb = new StringBuilder();
        Gson gson = new GsonBuilder().create();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null)
                stb.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(stb.toString(), Filter.class);
    }


}
