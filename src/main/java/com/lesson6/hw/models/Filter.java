package com.lesson6.hw.models;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class Filter {
    private Date dateFlight;
    private Date startDate;
    private Date finishDate;
    private String cityFrom;
    private String cityTo;
    private Plane plane;


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getDateFlight() {
        return dateFlight;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return Objects.equals(dateFlight, filter.dateFlight) &&
                Objects.equals(cityFrom, filter.cityFrom) &&
                Objects.equals(cityTo, filter.cityTo) &&
                Objects.equals(plane, filter.plane);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dateFlight, cityFrom, cityTo, plane);
    }

    @Override
    public String toString() {
        return "Filter{" +
                "dateFlight=" + dateFlight +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", plane=" + plane +
                '}';
    }
}
