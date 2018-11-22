package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.model.Shelf;

import java.util.*;

public interface IntfShelfService {

    List<Shelf> displayAllShelfs();
    Optional<Shelf> displayShelfById(int idShelf);
    int checkIsExistBook(int idBook);
    int checkIsBookAddedAtShelf(int idBook, int idShelf);
    String addBookIntoShelf(Book book, int idShelf);
    String removeBookFromShelf(int idBook, int idShelf);
}
