package com.mitrais.booklibrarymanagement.controller;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    BookService bookService;

    @Autowired
    public void setImplBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value="/getByStatus", method=RequestMethod.GET)
    public List<Book> displayBooksByStatus(@RequestParam("status") String status){
        List<Book> books = bookService.displayBooksByStatus(status);
        return books;
    }

    @RequestMapping(value="/getByTitleAndStatus", method=RequestMethod.GET)
    public List<Book> displayBooksByTitleAndStatus(@RequestParam("title") String title,
                                               @RequestParam("status") String status){
        List<Book> books = bookService.displayBooksByTitleAndStatus(title, status);
        return books;
    }
}
