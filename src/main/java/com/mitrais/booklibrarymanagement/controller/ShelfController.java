package com.mitrais.booklibrarymanagement.controller;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.model.Shelf;
import com.mitrais.booklibrarymanagement.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/shelf")
public class ShelfController {

    ShelfService shelfService;

    @Autowired
    public void setShelfService(ShelfService shelfService) {
        this.shelfService = shelfService;
    }

    @RequestMapping(value="/get-all", method=RequestMethod.GET,
            produces = "application/json")
    public List<Shelf> displayAllShelfs(){
        return shelfService.displayAllShelves();
    }

    @RequestMapping(value="/get-by-id", method=RequestMethod.GET,
            produces = "application/json")
    public Optional<Shelf> displayShelfById(@RequestParam("id") int id){
        return shelfService.displayShelfById(id);
    }

    @RequestMapping(value = "/add", method=RequestMethod.POST,
            produces = "text/plain", consumes = "application/json")
    public String addBookIntoShelf(@RequestBody Book book,
                                   @RequestParam("id-shelf") int id){
        return shelfService.addBookIntoShelf(book, id);
    }

    @RequestMapping(value = "/remove", method=RequestMethod.DELETE)
    public String removeBookFromShelf(@RequestParam("id-book") int idBook,
                                      @RequestParam("id-shelf") int idShelf){
        return shelfService.removeBookFromShelf(idBook, idShelf);
    }

    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    public String EntityNotFoundException(javax.persistence.EntityNotFoundException ex){
        return ex.getMessage();
    }
}
