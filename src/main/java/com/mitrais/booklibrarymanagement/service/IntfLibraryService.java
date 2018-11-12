package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.model.Shelf;

public interface IntfLibraryService {

    Shelf displayShelfById(int id);
    String addBookIntoShelf(Book book, int idShelf);
    String removeBookFromShelf(int idBook, int idShelf);
}
