package com.lesson6.hw.models;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class Flight {

    private Long id;
    private Plane plane;
    private Collection<Passenger> passengers;
    private Date dateFlight;
    private String cityFrom;
    private String cityTo;

    public Long getId() {
        return id;
    }

    public Plane getPlane() {
        return plane;
    }

    public Collection<Passenger> getPassengers() {
        return passengers;
    }

    public Date getDateFlight() {
        return dateFlight;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setPassengers(Collection<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) &&
                Objects.equals(plane, flight.plane) &&
                Objects.equals(passengers, flight.passengers) &&
                Objects.equals(dateFlight, flight.dateFlight) &&
                Objects.equals(cityFrom, flight.cityFrom) &&
                Objects.equals(cityTo, flight.cityTo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, plane, passengers, dateFlight, cityFrom, cityTo);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", plane=" + plane +
                ", passengers=" + passengers +
                ", dateFlight=" + dateFlight +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                '}';
    }
}
