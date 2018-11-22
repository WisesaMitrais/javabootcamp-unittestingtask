package com.mitrais.booklibrarymanagement.controller;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.service.BookService;
import com.mitrais.booklibrarymanagement.util.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setImplBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value="/get-all", method=RequestMethod.GET,
            produces = "application/json")
    public List<Book> displayAllBooks(){
        return bookService.displayAllBooks();
    }

    @RequestMapping(value="/get-by-status", method=RequestMethod.GET,
            produces = "application/json")
    public List<Book> displayBooksByStatus(@RequestParam("status") BookStatus status){
        return bookService.displayBooksByStatus(status);
    }

    @RequestMapping(value="/get-by-title-and-status", method=RequestMethod.GET,
            produces = "application/json")
    public List<Book> displayBooksByTitleAndStatus(@RequestParam("title") String title,
                                               @RequestParam("status") BookStatus status){
        return bookService.displayBooksByTitleAndStatus(title, status);
    }
}
