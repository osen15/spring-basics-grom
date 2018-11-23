package com.lesson6.hw.models;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;


@Component
@Entity
@Table(name = "FLIGHT")
public class Flight {
    @Id
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "PLANE_ID")
    private Plane plane;
    @Column(name = "DATE_FLIGHT")
    private Date dateFlight;
    @Column(name = "CITY_FROM")
    private String cityFrom;
    @Column(name = "CITY_TO")
    private String cityTo;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "FLIGHT_AND_PASSENGER", joinColumns = @JoinColumn(name = "FLIGHT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PASSENGER_ID", referencedColumnName = "ID"))
    private List<Passenger> passengers;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
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

    public List<Passenger> getPassenger() {
        return passengers;
    }

    public void setPassenger(List<Passenger> passenger) {
        this.passengers = passenger;
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
                ", passenger=" + passengers +
                '}';
    }
}