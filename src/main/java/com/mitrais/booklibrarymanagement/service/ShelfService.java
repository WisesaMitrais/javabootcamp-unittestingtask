package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.model.Shelf;
import com.mitrais.booklibrarymanagement.repository.BookRepository;
import com.mitrais.booklibrarymanagement.repository.ShelfRepository;
import com.mitrais.booklibrarymanagement.util.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShelfService implements IntfShelfService {

    @Autowired
    private ShelfRepository shelfRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Shelf> displayAllShelves() {
        return shelfRepository.findAll();
    }

    @Override
    public Optional<Shelf> displayShelfById(int idShelf) {
        return shelfRepository.findById(idShelf);
    }

    @Override
    public int checkIsExistBook(int idBook) {
        return shelfRepository.checkIsBookExist(idBook);
    }

    @Override
    public int checkIsBookAddedAtShelf(int idBook, int idShelf) {
        return shelfRepository.checkIsBookAddedAtShelf(idBook, idShelf);
    }

    @Override
    public String addBookIntoShelf(Book book, int idShelf) {
        try {
            int isExistBook = checkIsExistBook(book.getId());
            int isBookAddedAtShelf = checkIsBookAddedAtShelf(book.getId(), idShelf);
            if(isExistBook == 1){
                if(isBookAddedAtShelf == 1) return "Book already added into Shelf.";
                else return addBookIntoShelf(book, idShelf, 2);
            }else{
                return addBookIntoShelf(book, idShelf, 1);
            }
        }catch (Exception ex){
            return "Adding process failed !";
        }
    }

    public String addBookIntoShelf(Book book, int idShelf, int time){
        if(time == 1) bookRepository.save(book);
        else book.setStatus(BookStatus.NOT_SHELVED);
        Shelf shelf = shelfRepository.getOne(idShelf);
        if(shelf.getCurCapacity() < shelf.getMaxCapacity()){
            shelf.getBooks().add(book.getId());
            shelf.setCurCapacity(shelf.getCurCapacity() + 1);
            bookRepository.updateBookStatus(BookStatus.NOT_SHELVED, book.getId());
            shelfRepository.setShelfCurrentCapacity(shelf.getCurCapacity(), idShelf);
            return "Adding process successfully.";
        }else{
            return "Shelf in max capacity !";
        }
    }

    @Override
    public String removeBookFromShelf(int idBook, int idShelf) {
        try {
            int isBookAddedAtShelf = checkIsBookAddedAtShelf(idBook, idShelf);
            if(isBookAddedAtShelf == 1){
                Shelf shelf = shelfRepository.getOne(idShelf);
                if(shelf.getCurCapacity() > 0){
                    Iterator itr = shelf.getBooks().iterator();
                    while (itr.hasNext()) {
                        if ((Integer) itr.next() == idBook) itr.remove();
                    }
                    shelf.setCurCapacity(shelf.getCurCapacity() - 1);
                    bookRepository.updateBookStatus(BookStatus.SHELVED, idBook);
                    shelfRepository.setShelfCurrentCapacity(shelf.getCurCapacity(), shelf.getId());
                    return "Removing process successfully.";
                }else{
                    return "Shelf is empty !";
                }
            }else{
                return "This book isn't exist in the shelf !";
            }
        }catch (Exception ex){
            return "Removing process failed !";
        }
    }
}
