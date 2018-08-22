package com.lesson2.ServiceRouteStep.beans;


import org.hibernate.mapping.List;

public class Service {
    private Long id;
    private String name;
    private List paramToCall;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getParamToCall() {
        return paramToCall;
    }

    public void setParamToCall(List paramToCall) {
        this.paramToCall = paramToCall;
    }
}
