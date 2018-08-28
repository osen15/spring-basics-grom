package com.lesson2.ServiceRouteStep.beans;




import java.util.List;
import java.util.Objects;


public class Route {
    private String id;
    private List steps;

    public Route() {
    }


    public Route(String id, List steps) {
        this.id = id;
        this.steps = steps;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) &&
                Objects.equals(steps, route.steps);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, steps);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", steps=" + steps +
                '}';
    }
}
