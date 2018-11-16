package com.mitrais.booklibrarymanagement.controller;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.model.Shelf;
import com.mitrais.booklibrarymanagement.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class LibraryController {

    LibraryService libraryService;
    String result;

    @Autowired
    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping(value="/getById", method=RequestMethod.GET)
    public String displayShelfById(@RequestParam("id") int id){
        String shelf = libraryService.displayShelfById(id);
        return shelf;
    }

    @RequestMapping(value = "/addBook", method=RequestMethod.POST)
    public String addBookIntoShelf(@RequestBody Book book,
                                   @RequestParam("shelf-id") int idShelf){
        result = libraryService.addBookIntoShelf(book, idShelf);
        return result;
    }

    @RequestMapping(value = "/removeBook", method=RequestMethod.DELETE)
    public String removeBookFromShelf(@RequestParam("book-id") int idBook,
                                      @RequestParam("shelf-id") int idShelf){
        result = libraryService.removeBookFromShelf(idBook, idShelf);
        return result;
    }

    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    public String EntityNotFoundException(javax.persistence.EntityNotFoundException ex){
        return ex.getMessage();
    }
}
