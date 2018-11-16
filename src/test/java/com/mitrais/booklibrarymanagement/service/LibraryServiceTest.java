package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.model.Shelf;
import com.mitrais.booklibrarymanagement.repository.BookRepository;
import com.mitrais.booklibrarymanagement.repository.LibraryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {

    @InjectMocks
    private LibraryService libraryService;
    @Mock
    private LibraryRepository libraryRepository;
    @Mock
    private BookRepository bookRepository;

    private Shelf shelf;
    private Book book;
    private int idShelf, idBook;
    private String result;

    @Test
    public void displayShelfById_idFound(){
        System.out.println("Tes displayShelfById_idFound");
        // Preparation.
        idShelf = 1;
        when(libraryRepository.getOne(idShelf)).thenReturn(
                new Shelf(1, 5, 2, Arrays.asList(1, 2))
        );
        // Action.
        result = libraryService.displayShelfById(idShelf);
        // Verification.
        assertEquals("Shelf{shelf_id=1, max_capacity=5, current_capacity=2, books=[1, 2]}", result);
    }

//    @Test | JIKA ID TIDAK DITEMUKAN
//    public void displayShelfById_idNotFound(){
//        System.out.println("Tes displayShelfById_idNotFound");
//        // Preparation.
//        idShelf = 3;
//        Mockito.when(libraryRepository.getOne(idShelf)).thenReturn(null);
//        // Action.
//        result = libraryService.displayShelfById(idShelf);
//        // Verification.
//        assertEquals("[]", result);
//    }

    @Test
    public void addBookIntoShelf_addSuccess(){
        System.out.println("Tes addBookIntoShelf_addSuccess");
        // Preparation.
        List<Integer> books = new ArrayList<>();
        books.add(1);
        books.add(2);
        shelf = new Shelf(1, 5, 2, books);
        book = new Book(6, "9999999999999", "Testing Add Book", "Testing", "SHELVED");
        Mockito.when(libraryRepository.getOne(any())).thenReturn(shelf);
        // Action.
        result = libraryService.addBookIntoShelf(book, shelf.getShelf_id());
        // Verification.
        Mockito.verify(bookRepository, times(1)).save(any());
        Mockito.verify(libraryRepository, times(1)).getOne(any());
        assertEquals("Adding process successfully.", result);
        assertEquals(3, shelf.getCurrent_capacity());
    }

    @Test
    public void addBookIntoShelf_shelfMaxCapacity(){
        System.out.println("Tes addBookIntoShelf_shelfMaxCapacity");
        // Preparation.
        List<Integer> books = new ArrayList<>();
        books.add(1);
        books.add(2);
        shelf = new Shelf(1, 2, 2, books);
        book = new Book(6, "9999999999999", "Testing Add Book", "Testing", "SHELVED");
        Mockito.when(libraryRepository.getOne(any())).thenReturn(shelf);
        // Action.
        result = libraryService.addBookIntoShelf(book, shelf.getShelf_id());
        // Verification.
        Mockito.verify(bookRepository, times(1)).save(any());
        Mockito.verify(libraryRepository, times(1)).getOne(any());
        assertEquals("Shelf in max capacity !", result);
        assertEquals(2, shelf.getCurrent_capacity());
    }

    @Test
    public void removeBookFromShelf_removeSuccess(){
        System.out.println("Tes removeBookFromShelf_removeSuccess");
        // Preparation.
        List<Integer> books = new ArrayList<>();
        books.add(1);
        books.add(2);
        books.add(6);
        idBook = 6;
        shelf = new Shelf(1, 5, 3, books);
        Mockito.when(libraryRepository.getOne(any())).thenReturn(shelf);
        // Action.
        result = libraryService.removeBookFromShelf(idBook, shelf.getShelf_id());
        // Verification.
        Mockito.verify(libraryRepository, times(1)).getOne(any());
        Mockito.verify(bookRepository, times(1)).deleteById(idBook);
        assertEquals("Removing process successfully.", result);
        assertEquals(2, shelf.getCurrent_capacity());
    }

    @Test
    public void removeBookFromShelf_shelfEmpty(){
        System.out.println("Tes removeBookFromShelf_removeSuccess");
        // Preparation.
        List<Integer> books = new ArrayList<>();
        idBook = 1;
        shelf = new Shelf(1, 5, 0, books);
        Mockito.when(libraryRepository.getOne(any())).thenReturn(shelf);
        // Action.
        result = libraryService.removeBookFromShelf(idBook, shelf.getShelf_id());
        // Verification.
        Mockito.verify(libraryRepository, times(1)).getOne(any());
        Mockito.verify(bookRepository, never()).deleteById(idBook);
        assertEquals("Shelf is empty !", result);
        assertEquals(0, shelf.getCurrent_capacity());
    }
}
