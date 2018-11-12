package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;

import java.util.List;

public interface IntfBookService {

    List<Book> displayBooksByStatus(String status);
    List<Book> displayBooksByTitleAndStatus(String title, String status);
}
