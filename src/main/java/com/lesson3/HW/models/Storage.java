package com.lesson3.HW.models;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
@Component
@Entity
@Table(name = "STORAGE")
public class Storage {
    private long id;
    private String stringFormat;
    private long storageSize;

    public Storage() {
    }


    public Storage(long id, String stringFormat, long storageSize) {
        this.id = id;
        this.stringFormat = stringFormat;
        this.storageSize = storageSize;
    }

    @Id
    @SequenceGenerator(name = "ST_SEQ", sequenceName = "S_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ST_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "STRING_FORMAT")
    public String getStringFormat() {
        return stringFormat;
    }

    public void setStringFormat(String stringFormat) {
        this.stringFormat = stringFormat;
    }

    @Column(name = "STORAGE_SIZE")
    public long getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return id == storage.id &&
                storageSize == storage.storageSize &&
                Objects.equals(stringFormat, storage.stringFormat);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stringFormat, storageSize);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", stringFormat='" + stringFormat + '\'' +
                ", storageSize=" + storageSize +
                '}';
    }
}