package com.mitrais.booklibrarymanagement.controller;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.service.ShelfService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ShelfController.class)
public class ShelfControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShelfService libraryService;

    private RequestBuilder requestBuilder;
    private int id, idShelf;
    private String result;
    private Book book;

    @Test
    public void displayShelfById_idFound() {
//        System.out.println("Test displayShelfById_idFound");
//        // Preparation.
//        id = 1;
//        Mockito.when(libraryService.displayShelfById(id)).thenReturn(
//                "Shelf{shelf_id=1, max_capacity=5, current_capacity=2, books=[1, 2]}"
//        );
//        // Action.
//        result = libraryService.displayShelfById(id);
//        // Verification.
//        assertEquals(true, result.contains("[1, 2]"));
    }

    @Test
    public void displayShelfById_idNotFound() {
//        System.out.println("Tes displayShelfById_idNotFound");
//        // Preparation.
//        id = 3;
//        when(libraryService.displayShelfById(id)).thenReturn("" +
//                "Unable to find com.mitrais.booklibrarymanagement.model.Shelf with id 3"
//        );
//        // Action.
//        result = libraryService.displayShelfById(id);
//        // Verification.
//        assertEquals(true, result.contains("Unable to find"));
    }

    @Test
    public void displayShelfById_idNull() throws Exception {
        System.out.println("Tes displayShelfById_idNull");
        // Action.
        requestBuilder = MockMvcRequestBuilders
                .get("/getById");
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    @Test
    public void addBookIntoShelf_allParamFound() {
//        System.out.println("Tes addBookIntoShelf_allParamFound");
//        // Preparation.
//        idShelf = 1;
//        book = new Book(6, "9999999999999", "Testing Add Book",
//                "Testing", "SHELVED");
//        Mockito.when(libraryService.addBookIntoShelf(book, idShelf)).thenReturn(
//                "Adding process successfully."
//        );
//        // Action.
//        result = libraryService.addBookIntoShelf(book, idShelf);
//        // Verification.
//        assertEquals(true, result.contains("Adding process successfully."));
    }

    @Test
    public void addBookIntoShelf_oneParamNotFound() {
//        System.out.println("Tes addBookIntoShelf_oneParamNotFound");
//        // Preparation.
//        idShelf = 3;
//        book = new Book(6, "9999999999999", "Testing Add Book",
//                "Testing", "SHELVED");
//        Mockito.when(libraryService.addBookIntoShelf(book, idShelf)).thenReturn(
//                "Adding process failed !"
//        );
//        // Action.
//        result = libraryService.addBookIntoShelf(book, idShelf);
//        // Verification.
//        assertEquals(true, result.contains("Adding process failed !"));
    }

    @Test
    public void addBookIntoShelf_oneParamNull() throws Exception {
        System.out.println("Tes addBookIntoShelf_oneParamNotFound");
        // Preparation.
        idShelf = 1;
        // Action.
        requestBuilder = MockMvcRequestBuilders
                .get("/removeBook")
                .param("shelf-id", String.valueOf(idShelf));
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    @Test
    public void removeBookFromShelf_allParamFound() {
        System.out.println("Tes removeBookFromShelf_allParamFound");
        // Preparation.
        idShelf = 1;
        id = 6;
        Mockito.when(libraryService.removeBookFromShelf(id, idShelf)).thenReturn(
                "Removing process successfully."
        );
        // Action.
        result = libraryService.removeBookFromShelf(id, idShelf);
        // Verification.
        assertEquals(true, result.contains("Removing process successfully."));
    }

    @Test
    public void removeBookFromShelf_oneParamNotFound() {
        System.out.println("Tes removeBookFromShelf_oneParamNotFound");
        // Preparation.
        idShelf = 1;
        id = 8;
        Mockito.when(libraryService.addBookIntoShelf(book, idShelf)).thenReturn(
                "Removing process failed !"
        );
        // Action.
        result = libraryService.addBookIntoShelf(book, idShelf);
        // Verification.
        assertEquals(true, result.contains("Removing process failed !"));
    }

    @Test
    public void removeBookFromShelf_oneParamNull() throws Exception {
        System.out.println("Tes removeBookFromShelf_oneParamNotFound");
        // Preparation.
        idShelf = 1;
        // Action.
        requestBuilder = MockMvcRequestBuilders
                .get("/removeBook").param("shelf-id", String.valueOf(idShelf));
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }
}
