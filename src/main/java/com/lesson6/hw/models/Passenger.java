package com.lesson6.hw.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Component
@Entity
@Table(name = "PASSENGER")
public class Passenger {
    @Id
    @SequenceGenerator(name = "P_SEQ", sequenceName = "PASSENGER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "P_SEQ")
    @Column(name = "ID")
    private Long id;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "NATIONALITY")
    private String nationality;
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;
    @Column(name = "PASS_CODE")
    private String passCode;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "passengers")
    private Collection<Flight> flights = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

    public Collection<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Collection<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(id, passenger.id) &&
                Objects.equals(lastName, passenger.lastName) &&
                Objects.equals(nationality, passenger.nationality) &&
                Objects.equals(dateOfBirth, passenger.dateOfBirth) &&
                Objects.equals(passCode, passenger.passCode) &&
                Objects.equals(flights, passenger.flights);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, lastName, nationality, dateOfBirth, passCode, flights);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passCode='" + passCode + '\'' +
                ", flights=" + flights +
                '}';
    }
}