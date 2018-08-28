package com.lesson2.ServiceRouteStep.beans;



import java.util.Map;
import java.util.Objects;


public class Step {
    private Long id;
    private Service serviceFrom;
    private Service serviceTo;
    private Map paramsServiceFrom;
    private Map paramsServiceTo;

    public Step() {
    }

    public Step(Long id, Service serviceFrom, Service serviceTo, Map paramsServiceFrom, Map paramsServiceTo) {
        this.id = id;
        this.serviceFrom = serviceFrom;
        this.serviceTo = serviceTo;
        this.paramsServiceFrom = paramsServiceFrom;
        this.paramsServiceTo = paramsServiceTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Service getServiceFrom() {
        return serviceFrom;
    }

    public void setServiceFrom(Service serviceFrom) {
        this.serviceFrom = serviceFrom;
    }

    public Service getServiceTo() {
        return serviceTo;
    }

    public void setServiceTo(Service serviceTo) {
        this.serviceTo = serviceTo;
    }

    public Map getParamsServiceFrom() {
        return paramsServiceFrom;
    }

    public void setParamsServiceFrom(Map paramsServiceFrom) {
        this.paramsServiceFrom = paramsServiceFrom;
    }

    public Map getParamsServiceTo() {
        return paramsServiceTo;
    }

    public void setParamsServiceTo(Map paramsServiceTo) {
        this.paramsServiceTo = paramsServiceTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return Objects.equals(id, step.id) &&
                Objects.equals(serviceFrom, step.serviceFrom) &&
                Objects.equals(serviceTo, step.serviceTo) &&
                Objects.equals(paramsServiceFrom, step.paramsServiceFrom) &&
                Objects.equals(paramsServiceTo, step.paramsServiceTo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, serviceFrom, serviceTo, paramsServiceFrom, paramsServiceTo);
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", serviceFrom=" + serviceFrom +
                ", serviceTo=" + serviceTo +
                ", paramsServiceFrom=" + paramsServiceFrom +
                ", paramsServiceTo=" + paramsServiceTo +
                '}';
    }
}
