package com.lesson3.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FILE1")
public class File {
    private long id;
    private String name;
    private String format;
    private long size;
    private Storage storage;

    public File() {

    }

    public File(long id, String name, String format, long size, Storage storage) {
        this.id = id;
        this.name = name;
        this.format = format;
        this.size = size;
        this.storage = storage;
    }

    @Id
    @SequenceGenerator(name = "FL_SEQ", sequenceName = "F_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FL_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "FORMAT")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Column(name = "FILE_SIZE")
    public long getSize() {

        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @ManyToOne
    @JoinColumn(name = "STORAGE_ID")
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return id == file.id &&
                size == file.size &&
                Objects.equals(name, file.name) &&
                Objects.equals(format, file.format) &&
                Objects.equals(storage, file.storage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, format, size, storage);
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                ", storage=" + storage +
                '}';
    }
}

