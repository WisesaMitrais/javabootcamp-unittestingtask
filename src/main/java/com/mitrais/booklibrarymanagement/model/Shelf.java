package com.mitrais.booklibrarymanagement.model;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Shelf.setShelfCurrentCapacity",
        query = "UPDATE Shelf s SET s.current_capacity = ?1 WHERE s.shelf_id = ?2")
public class Shelf {

    @Id
    private int shelf_id;
    private int max_capacity;
    private int current_capacity;

    @ElementCollection
    @CollectionTable(name = "shelf_books", joinColumns = @JoinColumn(name = "shelf_shelf_id"))
    private List<Integer> books;

    public Shelf(){}

    public Shelf(int shelf_id, int max_capacity, int current_capacity, List<Integer> books) {
        this.shelf_id = shelf_id;
        this.max_capacity = max_capacity;
        this.current_capacity = current_capacity;
        this.books = books;
    }

    public int getShelf_id() {
        return shelf_id;
    }

    public void setShelf_id(int shelf_id) {
        this.shelf_id = shelf_id;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public int getCurrent_capacity() {
        return current_capacity;
    }

    public void setCurrent_capacity(int current_capacity) {
        this.current_capacity = current_capacity;
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
                "shelf_id=" + shelf_id +
                ", max_capacity=" + max_capacity +
                ", current_capacity=" + current_capacity +
                ", books=" + books +
                '}';
    }
}
