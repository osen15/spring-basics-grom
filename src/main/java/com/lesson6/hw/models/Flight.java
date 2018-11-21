package com.lesson6.hw.models;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;


@Component
@Entity
@Table(name = "FLIGHT")
public class Flight {

    private Long id;
    private Plane plane;
    private Date dateFlight;
    private String cityFrom;
    private String cityTo;
    private Collection passengers;

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLANE_ID")
    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @Column(name = "DATE_FLIGHT")
    public Date getDateFlight() {
        return dateFlight;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

    @Column(name = "CITY_FROM")
    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    @Column(name = "CITY_TO")
    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }
    @OneToMany(targetEntity = Passenger.class,
            fetch = FetchType.LAZY)
   // @Column(name = "PASSENGERS")
    public Collection getPassengers() {
        return passengers;
    }

    public void setPassengers(Collection<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) &&
                Objects.equals(plane, flight.plane) &&
                Objects.equals(dateFlight, flight.dateFlight) &&
                Objects.equals(cityFrom, flight.cityFrom) &&
                Objects.equals(cityTo, flight.cityTo) &&
                Objects.equals(passengers, flight.passengers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, plane, dateFlight, cityFrom, cityTo, passengers);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", plane=" + plane +
                ", dateFlight=" + dateFlight +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", passengers=" + passengers +
                '}';
    }
}


