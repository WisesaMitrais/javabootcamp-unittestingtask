package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IntfBookService {

    @Autowired
    BookRepository bookRepository;

    List<Book> result = new ArrayList<>();

    @Override
    public List<Book> displayBooksByStatus(String status) {
        result = bookRepository.findByStatus(status);
        return result;
    }

    @Override
    public List<Book> displayBooksByTitleAndStatus(String title, String status) {
        result = bookRepository.findByTitleAndStatus(title, status);
        return result;
    }
}
