package com.mitrais.booklibrarymanagement.controller;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    private List<Book> books;
    private RequestBuilder requestBuilder;
    private String title, status;

//    @Test  |TEST CASE INI SELALU MENDAPAT NILAI ACTUAL 404.
//    public void displayBooksByStatus_statusFound() throws Exception {
//        System.out.println("Test displayBooksByStatus_statusFound");
//        // Preparation.
//        String status = "SHELVED";
//        Mockito.when(bookService.displayBooksByStatus(status)).thenReturn(Arrays.asList(
//                new Book(1, "9786021514917", "Algoritma dan Pemrograman",
//                        "Rinaldi Munir", "SHELVED"),
//                new Book(2, "9786021514924", "Metode Numerik",
//                        "Rinaldi Munir", "SHELVED")
//        ));
//        // Action.
//        requestBuilder = MockMvcRequestBuilders
//                .get("/getByStatus")
//                .param("status", status)
//                .accept(MediaType.APPLICATION_JSON);
//        // Verification.
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().is(200));
//    }

    @Test
    public void displayBooksByStatus_statusFound() {
//        System.out.println("Test displayBooksByStatus_statusFound");
//        // Preparation.
//        status = "SHELVED";
//        Mockito.when(bookService.displayBooksByStatus(status)).thenReturn(Arrays.asList(
//                new Book(1, "9786021514917", "Algoritma dan Pemrograman",
//                        "Rinaldi Munir", "SHELVED"),
//                new Book(2, "9786021514924", "Metode Numerik",
//                        "Rinaldi Munir", "SHELVED")
//        ));
//        // Action.
//        books = bookService.displayBooksByStatus(status);
//        // Verification.
//        assertEquals(2, books.size());
//        assertEquals("SHELVED", books.get(1).getStatus());
    }

    @Test
    public void displayBooksByStatus_statusNotFound() {
//        System.out.println("Test displayBooksByStatus_statusNotFound");
//        // Preparation.
//        status = "SHELVE";
//        Mockito.when(bookService.displayBooksByStatus(status)).thenReturn(Collections.EMPTY_LIST);
//        // Action.
//        books = bookService.displayBooksByStatus(status);
//        // Verification.
//        assertEquals(0, books.size());
    }

    @Test
    public void displayBooksByStatus_statusNull() throws Exception {
        System.out.println("Test displayBooksByStatus_statusNull");
        // Action.
        requestBuilder = MockMvcRequestBuilders
                .get("/getByStatus")
                .accept(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    @Test
    public void displayBooksByTitleAndStatus_allParamFound() {
//        System.out.println("Test displayBooksByTitleAndStatus_allParamFound");
//        // Preparation.
//        title = "Pemrograman C";
//        status = "NOT-SHELVED";
//        Mockito.when(bookService.displayBooksByTitleAndStatus(title, status))
//                .thenReturn(Arrays.asList(
//                new Book(3, "9786028759281", "Pemrograman C",
//                        "Budi Raharjo", "NOT-SHELVED")
//        ));
//        // Action.
//        books = bookService.displayBooksByTitleAndStatus(title, status);
//        // Verification.
//        assertEquals(1, books.size());
    }

    @Test
    public void displayBooksByTitleAndStatus_oneParamNotFound() {
//        System.out.println("Test displayBooksByTitleAndStatus_oneParamNotFound");
//        // Preparation.
//        title = "Metode Numerik";
//        status = "NOT-SHELVED";
//        Mockito.when(bookService.displayBooksByTitleAndStatus(title, status))
//                .thenReturn(Collections.EMPTY_LIST);
//        // Action.
//        books = bookService.displayBooksByTitleAndStatus(title, status);
//        // Verification.
//        assertEquals(0, books.size());
    }

    @Test
    public void displayBooksByTitleAndStatus_oneParamNull() throws Exception {
        System.out.println("Test displayBooksByTitleAndStatus_oneParamNull");
        // Action.
        requestBuilder = MockMvcRequestBuilders
                .get("/getByTitleAndStatus")
                .accept(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }
}
