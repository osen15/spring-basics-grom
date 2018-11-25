package com.lesson6.hw.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Plane")
@Component
public class Plane {
    private Long id;
    private String name;
    private String model;
    private Date yearProduced;
    private Double avgFuelConsumption;



    @Id
    @SequenceGenerator(name = "PL_SEQ", sequenceName = "PLANE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PL_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "P_NAME")
    public String getName() {
        return name;
    }

    @Column(name = ("P_MODEL"))
    public String getModel() {
        return model;
    }

    @Column(name = "YEAR_PRODUCED")
    public Date getYearProduced() {
        return yearProduced;
    }

    @Column(name = "AVG_FUEL_CONSUMPTION")
    public Double getAvgFuelConsumption() {
        return avgFuelConsumption;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYearProduced(Date yearProduced) {
        this.yearProduced = yearProduced;
    }

    public void setAvgFuelConsumption(Double avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return Objects.equals(id, plane.id) &&
                Objects.equals(name, plane.name) &&
                Objects.equals(model, plane.model) &&
                Objects.equals(yearProduced, plane.yearProduced) &&
                Objects.equals(avgFuelConsumption, plane.avgFuelConsumption);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, model, yearProduced, avgFuelConsumption);
    }


    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", yearProduced=" + yearProduced +
                ", avgFuelConsumption=" + avgFuelConsumption +
                '}';
    }
}
