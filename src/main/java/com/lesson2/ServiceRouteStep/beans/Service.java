package com.lesson2.ServiceRouteStep.beans;


import java.util.List;
import java.util.Objects;

public class Service {
    private Long id;
    private String name;
    private List paramToCall;

    public Service() {
    }

    public Service(Long id, String name, List paramToCall) {
        this.id = id;
        this.name = name;
        this.paramToCall = paramToCall;
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(id, service.id) &&
                Objects.equals(name, service.name) &&
                Objects.equals(paramToCall, service.paramToCall);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, paramToCall);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", paramToCall=" + paramToCall +
                '}';
    }
}
