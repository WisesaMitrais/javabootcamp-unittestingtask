package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.repository.BookRepository;
import com.mitrais.booklibrarymanagement.util.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService implements IntfBookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> displayAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> displayBooksByStatus(BookStatus status) {
        return bookRepository.findByStatus(status);
    }

    @Override
    public List<Book> displayBooksByTitleAndStatus(String title, BookStatus status) {
        return bookRepository.findByTitleAndStatus(title, status);
    }
}
