package com.mitrais.booklibrarymanagement.repository;

import com.mitrais.booklibrarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByStatus(String status);
    List<Book> findByTitleAndStatus(String title, String status);
    void deleteById(String idBook);
}
