package com.lesson6.hw.models;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@Entity
@Table(name = "PASSENGER")
public class Passenger {
    private Long id;
    private String lastName;
    private String nationality;
    private Date dateOfBirth;
    private String passCode;
    private List flights;


    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "NATIONALITY")
    public String getNationality() {
        return nationality;
    }

    @Column(name = "DATE_OF_BIRTH")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Column(name = "PASS_CODE")
    public String getPassCode() {
        return passCode;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Flight.class)
    // @Column(name = "FLIGHTS")
    public List getFlights() {
        return flights;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

    public void setFlights(List<Flight> flights) {
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
