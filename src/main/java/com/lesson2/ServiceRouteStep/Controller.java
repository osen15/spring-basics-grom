package com.lesson2.ServiceRouteStep;

import org.springframework.beans.factory.annotation.Autowired;

public class Controller {
    @Autowired
    Step step;
    @Autowired
    Route route;
    @Autowired
    Service service;

    public void callByBean(){
        step.getId();
        step.getParamsServiceFrom();
        step.getParamsServiceTo();
        step.getServiceFrom();
        step.getServiceTo();


        route.getId();
        route.getSteps();

        service.getId();
        service.getName();
        service.getParamToCall();






    }





}
