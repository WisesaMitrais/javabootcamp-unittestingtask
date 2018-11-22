package com.mitrais.booklibrarymanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.model.Shelf;
import com.mitrais.booklibrarymanagement.service.ShelfService;
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
@WebMvcTest(ShelfController.class)
public class ShelfControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShelfService shelfService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void displayAllShelfs_success() throws Exception {
        System.out.println("Test displayAllShelfs_success");
        // Preparation.
        Mockito.when(shelfService.displayAllShelves()).thenReturn(Arrays.asList(
                new Shelf(1, 5, 2, Arrays.asList(1, 2)),
                new Shelf(2, 5, 2, Arrays.asList(3, 4))
        ));
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/shelf/get-all")
                .contentType(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(content().json("[{\"id\":1,\"maxCapacity\":5,\"curCapacity\":2," +
                        "\"books\":[1,2]},{\"id\":2,\"maxCapacity\":5,\"curCapacity\":2,\"books\":[3,4]}]"));
    }

    @Test
    public void displayAllShelfs_noData() throws Exception {
        System.out.println("Test displayAllShelfs_noData");
        // Preparation.
        Mockito.when(shelfService.displayAllShelves()).thenReturn(Collections.EMPTY_LIST);
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/shelf/get-all")
                .contentType(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(content().json("[]"));
    }

    @Test
    public void displayShelfById_idFound() throws Exception {
        System.out.println("Test displayShelfById_idFound");
        // Preparation.
        Mockito.when(shelfService.displayShelfById(1)).thenReturn(
                Optional.of(new Shelf(1, 5, 2, Arrays.asList(1, 2)
                )));
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/shelf/get-by-id")
                .param("id", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(content().json("{\"id\":1,\"maxCapacity\":5,\"curCapacity\":2," +
                        "\"books\":[1,2]}"));
    }

    @Test
    public void displayShelfById_idNotFound() throws Exception {
        System.out.println("Test displayShelfById_idNotFound");
        // Preparation.
        Mockito.when(shelfService.displayShelfById(6)).thenReturn(Optional.empty());
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/shelf/get-by-id")
                .param("id", String.valueOf(6))
                .contentType(MediaType.APPLICATION_JSON);
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void addBookIntoShelf_success() throws Exception {
        System.out.println("Test addBookIntoShelf_success");
        // Preparation.
        Book book = new Book(1, "1234567890123", "Title A", "Author A");
        Mockito.when(shelfService.addBookIntoShelf(book, 1)).thenReturn(
                "Adding process successfully."
        );
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/shelf/add")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id-shelf", String.valueOf(1))
                .content(objectMapper.writeValueAsString(book))
                .characterEncoding("utf-8");
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void addBookIntoShelf_oneParamNotFound() throws Exception {
        System.out.println("Test addBookIntoShelf_oneParamNotFound");
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/shelf/add")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id-shelf", String.valueOf(1))
                .characterEncoding("utf-8");
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void removeBookFromShelf_success() throws Exception {
        System.out.println("Test removeBookFromShelf_success");
        // Preparation.
        Mockito.when(shelfService.removeBookFromShelf(1, 1)).thenReturn(
                "Removing process successfully."
        );
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/shelf/remove")
                .param("id-book", String.valueOf(1))
                .param("id-shelf", String.valueOf(1));
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(content().string("Removing process successfully."));
    }

    @Test
    public void removeBookFromShelf_oneParamNotFound() throws Exception {
        System.out.println("Test removeBookFromShelf_oneParamNotFound");
        // Action.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/shelf/remove")
                .param("id-shelf", String.valueOf(1));
        // Verification.
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }
}
