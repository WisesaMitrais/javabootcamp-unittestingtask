package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.util.BookStatus;

import java.util.*;

public interface IntfBookService {

    List<Book> displayAllBooks();
    List<Book> displayBooksByStatus(BookStatus status);
    List<Book> displayBooksByTitleAndStatus(String title, BookStatus status);
}
