package com.lesson5.itemApp;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
public class Item {
    private long id;
    private  String description;

    @Id
    @SequenceGenerator(name = "ITEM_SEQ", sequenceName = "ITEM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ")
    public long getId() {
        return id;
    }


    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
