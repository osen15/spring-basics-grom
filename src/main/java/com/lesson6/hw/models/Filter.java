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
    private String planeName;


    public Filter() {

    }

    public Date getDateFlight() {
        return dateFlight;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

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

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return Objects.equals(dateFlight, filter.dateFlight) &&
                Objects.equals(startDate, filter.startDate) &&
                Objects.equals(finishDate, filter.finishDate) &&
                Objects.equals(cityFrom, filter.cityFrom) &&
                Objects.equals(cityTo, filter.cityTo) &&
                Objects.equals(planeName, filter.planeName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dateFlight, startDate, finishDate, cityFrom, cityTo, planeName);
    }

    @Override
    public String toString() {
        return "Filter{" +
                "dateFlight=" + dateFlight +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", planeName='" + planeName + '\'' +
                '}';
    }
}