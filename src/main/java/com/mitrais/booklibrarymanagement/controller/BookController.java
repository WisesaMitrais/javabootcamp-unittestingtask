package com.mitrais.booklibrarymanagement.controller;

import com.google.gson.Gson;
import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    Gson gson = new Gson();
    BookService bookService;
    String result;

    @Autowired
    public void setImplBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value="/getByStatus", method=RequestMethod.GET)
    public String displayBooksByStatus(@RequestParam("status") String status){
        List<Book> books = bookService.displayBooksByStatus(status);
        result = gson.toJson(books);
        return result;
    }

    @RequestMapping(value="/getByTitleAndStatus", method=RequestMethod.GET)
    public String displayBooksByTitleAndStatus(@RequestParam("title") String title,
                                               @RequestParam("status") String status){
        List<Book> books = bookService.displayBooksByTitleAndStatus(title, status);
        result = gson.toJson(books);
        return result;
    }
}
