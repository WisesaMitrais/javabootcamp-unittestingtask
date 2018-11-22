package com.mitrais.booklibrarymanagement.model;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity
public class Shelf implements Serializable{

    @Id
    private int id;
    @Column(nullable = false)
    private int maxCapacity;
    @Column(nullable = false)
    private int curCapacity;
    @ElementCollection
    @CollectionTable(name = "shelf_books", joinColumns = @JoinColumn(name = "shelf_id"))
    private List<Integer> books;

    public Shelf(){}

    public Shelf(int id, int maxCapacity, int curCapacity, List<Integer> books) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.curCapacity = curCapacity;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurCapacity() {
        return curCapacity;
    }

    public void setCurCapacity(int curCapacity) {
        this.curCapacity = curCapacity;
    }

    public List<Integer> getBooks() {
        return books;
    }

    public void setBooks(List<Integer> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "id=" + id +
                ", maxCapacity=" + maxCapacity +
                ", curCapacity=" + curCapacity +
                ", books=" + books +
                '}';
    }
}
