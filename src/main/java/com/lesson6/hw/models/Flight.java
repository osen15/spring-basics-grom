package com.lesson6.hw.models;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Component
@Entity
@Table(name = "FLIGHT")
public class Flight {
    @Id
    @SequenceGenerator(name = "F_SEQ", sequenceName = "FLIGHT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "F_SEQ")
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "FLIGHT_AND_PASSENGER",
            joinColumns = @JoinColumn(name = "FLIGHT_ID"),
            inverseJoinColumns = @JoinColumn(name = "PASSENGER_ID"))
    private Collection<Passenger> passengers = new HashSet<>();


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

    public Collection<Passenger> getPassengers() {
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