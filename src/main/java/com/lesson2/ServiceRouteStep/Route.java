package com.lesson2.ServiceRouteStep;


import org.hibernate.mapping.List;

public class Route {
    private String id;
    private List steps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getSteps() {
        return steps;
    }

    public void setSteps(List steps) {
        this.steps = steps;
    }
}
