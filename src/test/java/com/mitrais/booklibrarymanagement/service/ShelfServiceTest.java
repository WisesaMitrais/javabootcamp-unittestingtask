package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.model.Shelf;
import com.mitrais.booklibrarymanagement.repository.BookRepository;
import com.mitrais.booklibrarymanagement.repository.ShelfRepository;
import com.mitrais.booklibrarymanagement.util.BookStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShelfServiceTest {

    @InjectMocks
    private ShelfService shelfService;
    @Mock
    private ShelfRepository shelfRepository;
    @Mock
    private BookRepository bookRepository;

    @Test
    public void displayAllShelves_success(){
        System.out.println("Tes displayAllShelves_success");
        // Preparation.
        Mockito.when(shelfRepository.findAll()).thenReturn(Arrays.asList(
                new Shelf(1, 5, 2, Arrays.asList(1, 2)),
                new Shelf(2, 5, 2, Arrays.asList(3, 4))
        ));
        // Action.
        List<Shelf> shelves = shelfService.displayAllShelves();
        // Verification.
        assertEquals(2, shelves.size());
    }

    @Test
    public void displayAllShelves_noData(){
        System.out.println("Tes displayAllShelves_noData");
        // Preparation.
        Mockito.when(shelfRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        // Action.
        List<Shelf> shelves = shelfService.displayAllShelves();
        // Verification.
        assertEquals(0, shelves.size());
    }

    @Test
    public void displayShelfById_idFound(){
        System.out.println("Tes displayShelfById_idFound");
        // Preparation.
        when(shelfRepository.findById(1)).thenReturn(
                Optional.of(new Shelf(1, 5, 2, Arrays.asList(1, 2)))
        );
        // Action.
        Optional<Shelf> shelf = shelfService.displayShelfById(1);
        // Verification.
        assertEquals("Optional[Shelf{id=1, maxCapacity=5, curCapacity=2, books=[1, 2]}]",
                shelf.toString());
    }

    @Test
    public void displayShelfById_idNotFound(){
        System.out.println("Tes displayShelfById_idNotFound");
        // Preparation.
        Mockito.when(shelfRepository.findById(1)).thenReturn(null);
        // Action.
        Optional<Shelf> shelf = shelfService.displayShelfById(1);
        // Verification.
        assertEquals(null, shelf);
    }

    @Test
    public void addBookIntoShelf_addSuccess(){
        System.out.println("Tes addBookIntoShelf_addSuccess");
        // Preparation.
        List<Integer> books = new ArrayList<>();
        books.add(1);
        books.add(2);
        Shelf shelf = new Shelf(1, 5, 2, books);
        Book book = new Book(6, "1234567890123", "Title F", "Author F");
        Mockito.when(shelfRepository.getOne(any())).thenReturn(shelf);
        // Action.
        String result = shelfService.addBookIntoShelf(book, shelf.getId());
        // Verification.
        Mockito.verify(bookRepository, times(1)).save(any());
        Mockito.verify(shelfRepository, times(1)).getOne(any());
        assertEquals(3, shelf.getCurCapacity());
        assertEquals("Adding process successfully.", result);
    }

    @Test
    public void addBookIntoShelf_shelfMaxCapacity(){
        System.out.println("Tes addBookIntoShelf_shelfMaxCapacity");
        // Preparation.
        List<Integer> books = new ArrayList<>();
        books.add(1);
        books.add(2);
        Shelf shelf = new Shelf(1, 5, 5, books);
        Book book = new Book(6, "1234567890123", "Title F", "Author F");
        Mockito.when(shelfRepository.getOne(any())).thenReturn(shelf);
        // Action.
        String result = shelfService.addBookIntoShelf(book, shelf.getId());
        // Verification.
        Mockito.verify(bookRepository, times(1)).save(any());
        Mockito.verify(shelfRepository, times(1)).getOne(any());
        assertEquals(5, shelf.getCurCapacity());
        assertEquals("Shelf in max capacity !", result);
    }

    @Test
    public void removeBookFromShelf_removeSuccess(){
        System.out.println("Tes removeBookFromShelf_removeSuccess");
        // Preparation.
        List<Integer> books = new ArrayList<>();
        books.add(1);
        books.add(2);
        books.add(6);
        Shelf shelf = new Shelf(1, 5, 3, books);
        Mockito.when(shelfService.checkIsBookAddedAtShelf(6, 1)).thenReturn(1);
        Mockito.when(shelfRepository.getOne(any())).thenReturn(shelf);
        // Action.
        String result = shelfService.removeBookFromShelf(6, shelf.getId());
        // Verification.
        Mockito.verify(shelfRepository, times(1)).getOne(any());
        assertEquals("Removing process successfully.", result);
        assertEquals(2, shelf.getCurCapacity());
    }

    @Test
    public void removeBookFromShelf_shelfEmpty(){
        System.out.println("Tes removeBookFromShelf_shelfEmpty");
        // Preparation.
        Shelf shelf = new Shelf(1, 5, 0, Collections.EMPTY_LIST);
        Mockito.when(shelfService.checkIsBookAddedAtShelf(6, 1)).thenReturn(1);
        Mockito.when(shelfRepository.getOne(any())).thenReturn(shelf);
        // Action.
        String result = shelfService.removeBookFromShelf(6, shelf.getId());
        // Verification.
        Mockito.verify(shelfRepository, times(1)).getOne(any());
        assertEquals("Shelf is empty !", result);
        assertEquals(0, shelf.getCurCapacity());
    }
}
