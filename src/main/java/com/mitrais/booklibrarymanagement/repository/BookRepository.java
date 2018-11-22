package com.mitrais.booklibrarymanagement.repository;

import com.mitrais.booklibrarymanagement.model.Book;
import com.mitrais.booklibrarymanagement.util.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAll();

    List<Book> findByStatus(BookStatus status);

    List<Book> findByTitleAndStatus(String title, BookStatus status);

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.status = ?1 WHERE b.id = ?2")
    void updateBookStatus(BookStatus status, int idBook);
}
