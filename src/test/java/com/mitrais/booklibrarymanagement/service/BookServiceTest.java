package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.repository.BookRepository;
import com.mitrais.booklibrarymanagement.util.BookStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    @Test
    public void displayAllBooks_success() {
        System.out.println("Test displayAllBooks_success");
        // Preparation.
        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(
                new Book(3, "3210987654321", "Title C", "Author C"),
                new Book(4, "1234567890123", "Title D", "Author D")
        ));
        // Action.
        List<Book> books = bookService.displayAllBooks();
        // Verification.
        assertEquals(2, books.size());
        assertEquals(BookStatus.NOT_SHELVED, books.get(0).getStatus());
        assertEquals(BookStatus.NOT_SHELVED, books.get(1).getStatus());
    }

    @Test
    public void displayAllBooks_noData() {
        System.out.println("Test displayAllBooks_noData");
        // Action.
        List<Book> books = bookService.displayAllBooks();
        // Verification.
        assertEquals(0, books.size());
        assertEquals("[]", books.toString());
    }

    @Test
    public void displayBooksByStatus_statusFound() {
        System.out.println("Test displayBooksByStatus_statusFound");
        // Preparation.
        Mockito.when(bookRepository.findByStatus(BookStatus.NOT_SHELVED)).thenReturn(Arrays.asList(
                new Book(3, "3210987654321", "Title C", "Author C"),
                new Book(4, "1234567890123", "Title D", "Author D")
        ));
        // Action.
        List<Book> books = bookService.displayBooksByStatus(BookStatus.NOT_SHELVED);
        // Verification.
        assertEquals(2, books.size());
        assertEquals(BookStatus.NOT_SHELVED, books.get(0).getStatus());
        assertEquals(BookStatus.NOT_SHELVED, books.get(1).getStatus());
    }

    @Test
    public void displayBooksByStatus_statusNotFound() {
        System.out.println("Test displayBooksByStatus_statusNotFound");
        // Preparation.
        Mockito.when(bookRepository.findByStatus(BookStatus.NOT_SHELVED)).thenReturn(Collections.EMPTY_LIST);
        // Action.
        List<Book> books = bookService.displayBooksByStatus(BookStatus.NOT_SHELVED);
        // Verification.
        assertEquals(0, books.size());
        assertEquals("[]", books.toString());
    }

    @Test
    public void displayBooksByStatus_statusNull() {
        System.out.println("Test displayBooksByStatus_statusNull");
        // Preparation.
        Mockito.when(bookRepository.findByStatus(null)).thenReturn(Collections.EMPTY_LIST);
        // Action.
        List<Book> books = bookService.displayBooksByStatus(null);
        // Verification.
        assertEquals(0, books.size());
    }

    @Test
    public void displayBooksByTitleAndStatus_allParamFound() {
        System.out.println("Test displayBooksByTitleAndStatus_allParamFound");
        // Preparation.
        Mockito.when(bookRepository.findByTitleAndStatus("Title C", BookStatus.NOT_SHELVED))
                .thenReturn(Arrays.asList(
                new Book(3, "3210987654321", "Title C", "Author C")
        ));
        // Action.
        List<Book> books = bookService.displayBooksByTitleAndStatus("Title C", BookStatus.NOT_SHELVED);
        // Verification.
        assertEquals(1, books.size());
        assertEquals(BookStatus.NOT_SHELVED, books.get(0).getStatus());
    }

    @Test
    public void displayBooksByTitleAndStatus_oneParamNotFound() {
        System.out.println("Test displayBooksByTitleAndStatus_oneParamNotFound");
        // Preparation.
        Mockito.when(bookRepository.findByTitleAndStatus("Title X", BookStatus.NOT_SHELVED))
                .thenReturn(Collections.EMPTY_LIST);
        // Action.
        List<Book> books = bookService.displayBooksByTitleAndStatus("Title X", BookStatus.NOT_SHELVED);
        // Verification.
        assertEquals(0, books.size());
        assertEquals("[]", books.toString());
    }

    @Test
    public void displayBooksByTitleAndStatus_oneParamNull() {
        System.out.println("Test displayBooksByTitleAndStatus_oneParamNull");
        // Preparation.
        Mockito.when(bookRepository.findByTitleAndStatus(null, BookStatus.NOT_SHELVED))
                .thenReturn(Collections.EMPTY_LIST);
        // Action.
        List<Book> books = bookService.displayBooksByTitleAndStatus(null, BookStatus.NOT_SHELVED);
        // Verification.
        assertEquals(0, books.size());
    }
}
