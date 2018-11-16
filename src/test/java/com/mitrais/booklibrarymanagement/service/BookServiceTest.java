package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    private List<Book> books;
    private String title, status;

    @Test
    public void displayBooksByStatus_statusFound() {
        System.out.println("Test displayBooksByStatus_statusFound");
        // Preparation.
        status = "SHELVED";
        Mockito.when(bookRepository.findByStatus(status)).thenReturn(Arrays.asList(
                new Book(1, "9786021514917", "Algoritma dan Pemrograman",
                        "Rinaldi Munir", "SHELVED"),
                new Book(2, "9786021514924", "Metode Numerik",
                        "Rinaldi Munir", "SHELVED")
        ));
        // Action.
        books = bookService.displayBooksByStatus(status);
        // Verification.
        assertEquals("SHELVED", books.get(0).getStatus());
        assertEquals("SHELVED", books.get(1).getStatus());
        assertEquals(2, books.size());
    }

    @Test
    public void displayBooksByStatus_statusNotFound() {
        System.out.println("Test displayBooksByStatus_statusNotFound");
        // Preparation.
        status = "NOT-SHELVE";
        Mockito.when(bookRepository.findByStatus(status)).thenReturn(Collections.EMPTY_LIST);
        // Action.
        books = bookService.displayBooksByStatus(status);
        // Verification.
        assertEquals(0, books.size());
    }

    @Test
    public void displayBooksByStatus_statusNull() {
        System.out.println("Test displayBooksByStatus_statusNull");
        // Preparation.
        status = null;
        Mockito.when(bookRepository.findByStatus(status)).thenReturn(Collections.EMPTY_LIST);
        // Action.
        books = bookService.displayBooksByStatus(status);
        // Verification.
        assertEquals(0, books.size());
    }

    @Test
    public void displayBooksByTitleAndStatus_allParamFound() {
        System.out.println("Test displayBooksByTitleAndStatus_allParamFound");
        // Preparation.
        title = "Metode Numerik";
        status = "SHELVED";
        Mockito.when(bookRepository.findByTitleAndStatus(title, status)).thenReturn(Arrays.asList(
                new Book(2, "9786021514924", "Metode Numerik",
                        "Rinaldi Munir", "SHELVED")
        ));
        // Action.
        books = bookService.displayBooksByTitleAndStatus(title, status);
        // Verification.
        assertEquals("SHELVED", books.get(0).getStatus());
        assertEquals(1, books.size());
    }

    @Test
    public void displayBooksByTitleAndStatus_oneParamNotFound() {
        System.out.println("Test displayBooksByTitleAndStatus_oneParamNotFound");
        // Preparation.
        title = "Pemrograman Java";
        status = "SHELVED";
        Mockito.when(bookRepository.findByTitleAndStatus(title, status)).thenReturn(Collections.EMPTY_LIST);
        // Action.
        books = bookService.displayBooksByTitleAndStatus(title, status);
        // Verification.
        assertEquals(0, books.size());
    }

    @Test
    public void displayBooksByTitleAndStatus_oneParamNull() {
        System.out.println("Test displayBooksByTitleAndStatus_oneParamNull");
        // Preparation.
        title = "Pemrograman C";
        status = null;
        Mockito.when(bookRepository.findByTitleAndStatus(title, status)).thenReturn(Collections.EMPTY_LIST);
        // Action.
        books = bookService.displayBooksByTitleAndStatus(title, status);
        // Verification.
        assertEquals(0, books.size());
    }
}
