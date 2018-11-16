package com.mitrais.booklibrarymanagement.service;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.model.Shelf;
import com.mitrais.booklibrarymanagement.repository.BookRepository;
import com.mitrais.booklibrarymanagement.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class LibraryService implements IntfLibraryService {

    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public String displayShelfById(int id) {
        Shelf shelf = libraryRepository.getOne(id);
        return shelf.toString();
    }

    @Override
    public String addBookIntoShelf(Book book, int idShelf) {
        try {
            bookRepository.save(book);
            Shelf shelf = libraryRepository.getOne(idShelf);
            if(shelf.getCurrent_capacity() < shelf.getMax_capacity()){
                shelf.getBooks().add(book.getId());
                shelf.setCurrent_capacity(shelf.getCurrent_capacity() + 1);
                libraryRepository.setShelfCurrentCapacity(shelf.getCurrent_capacity(), shelf.getShelf_id());
                return "Adding process successfully.";
            }else{
                return "Shelf in max capacity !";
            }
        }catch (Exception ex){
            return "Adding process failed !";
        }
    }

    @Override
    public String removeBookFromShelf(int idBook, int idShelf) {
        try {
            Shelf shelf = libraryRepository.getOne(idShelf);
            if(shelf.getCurrent_capacity() > 0){
                Iterator itr = shelf.getBooks().iterator();
                while (itr.hasNext()) {
                    if ((Integer) itr.next() == idBook) itr.remove();
                }
                shelf.setCurrent_capacity(shelf.getCurrent_capacity() - 1);
                bookRepository.deleteById(idBook);
                libraryRepository.setShelfCurrentCapacity(shelf.getCurrent_capacity(), shelf.getShelf_id());
                return "Removing process successfully.";
            }else{
                return "Shelf is empty !";
            }
        }catch (Exception ex){
            return "Removing process failed !";
        }
    }
}
