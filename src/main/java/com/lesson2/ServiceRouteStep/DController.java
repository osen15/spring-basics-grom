package com.lesson2.ServiceRouteStep;

import com.lesson2.ServiceRouteStep.beans.Route;
import com.lesson2.ServiceRouteStep.beans.Service;
import com.lesson2.ServiceRouteStep.beans.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DController {
    @Autowired
    private Step step;
    @Autowired
    private Route route;
    @Autowired
    private Service service;

    @RequestMapping(method = RequestMethod.GET, value = "/callByBean")
    @ResponseBody
    String callByBean() {


//        step.getId();
//        step.getParamsServiceFrom();
//        step.getParamsServiceTo();
//        step.getServiceFrom();
//        step.getServiceTo();

//
//        route.getId();
//        route.getSteps();
//
//        service.getId();
//        service.getName();
//        service.getParamToCall();

       return step.toString();


    }


}
