package com.mitrais.booklibrarymanagement.controller;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.service.BookService;
import com.mitrais.booklibrarymanagement.util.BookStatus;
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

import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @Test
    public void displayAllBooks_success() throws Exception {
        System.out.println("Test displayAllBooks_success");
        // Preparation.
        Mockito.when(bookService.displayAllBooks()).thenReturn(Arrays.asList(
                new Book(1, "1234567890123", "Title A", "Author A"),
                new Book(2, "3210987654321", "Title B", "Author B")
        ));
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/book/get-all")
                .contentType(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(content().json("[{\"id\":1,\"isbn\":\"1234567890123\"," +
                        "\"title\":\"Title A\",\"author\":\"Author A\",\"status\":\"NOT_SHELVED\"}," +
                        "{\"id\":2,\"isbn\":\"3210987654321\",\"title\":\"Title B\",\"author\":\"Author B\"," +
                        "\"status\":\"NOT_SHELVED\"}]"));
    }

    @Test
    public void displayAllBooks_noData() throws Exception {
        System.out.println("Test displayAllBooks_nodata");
        // Preparation.
        Mockito.when(bookService.displayAllBooks()).thenReturn(Collections.EMPTY_LIST);
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/book/get-all")
                .contentType(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(content().json("[]"));
    }

    @Test
    public void displayBooksByStatus_statusFound() throws Exception {
        System.out.println("Test displayBooksByStatus_statusFound");
        // Preparation.
        Mockito.when(bookService.displayBooksByStatus(BookStatus.SHELVED)).thenReturn(Arrays.asList(
                new Book(1, "1234567890123", "Title A", "Author A"),
                new Book(2, "3210987654321", "Title B", "Author B")
        ));
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/book/get-by-status")
                .param("status", BookStatus.SHELVED.toString())
                .contentType(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(content().json("[{\"id\":1,\"isbn\":\"1234567890123\"," +
                        "\"title\":\"Title A\",\"author\":\"Author A\",\"status\":\"NOT_SHELVED\"}," +
                        "{\"id\":2,\"isbn\":\"3210987654321\",\"title\":\"Title B\",\"author\":\"Author B\"," +
                        "\"status\":\"NOT_SHELVED\"}]"));
    }

    @Test
    public void displayBooksByStatus_statusNotFound() throws Exception {
        System.out.println("Test displayBooksByStatus_statusNotFound");
        // Preparation.
        Mockito.when(bookService.displayBooksByStatus(null)).thenReturn(Collections.EMPTY_LIST);
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/book/get-by-status")
                .contentType(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void displayBooksByTitleAndStatus_allParamFound() throws Exception {
        System.out.println("Test displayBooksByTitleAndStatus_allParamFound");
        // Preparation.
        Mockito.when(bookService.displayBooksByTitleAndStatus("Title A", BookStatus.SHELVED))
                .thenReturn(Arrays.asList(
                new Book(1, "1234567890123", "Title A", "Author A")
        ));
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/book/get-by-title-and-status")
                .param("title", "Title A")
                .param("status", BookStatus.SHELVED.toString())
                .contentType(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(content().json("[{\"id\":1,\"isbn\":\"1234567890123\"," +
                        "\"title\":\"Title A\",\"author\":\"Author A\",\"status\":\"NOT_SHELVED\"}]"));
    }

    @Test
    public void displayBooksByTitleAndStatus_oneParamNotFound() throws Exception {
        System.out.println("Test displayBooksByTitleAndStatus_oneParamNotFound");
        // Preparation.
        Mockito.when(bookService.displayBooksByTitleAndStatus("Title A", BookStatus.SHELVED))
                .thenReturn(Collections.EMPTY_LIST);
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/book/get-by-title-and-status")
                .param("title", "Title X")
                .param("status", BookStatus.SHELVED.toString())
                .contentType(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(content().json("[]"));
    }
}
