package com.mitrais.booklibrarymanagement.repository;

import com.mitrais.booklibrarymanagement.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Integer> {


    @Transactional
    @Modifying
    @Query("UPDATE Shelf s SET s.curCapacity = ?1 WHERE s.id = ?2")
    void setShelfCurrentCapacity(int curCapacity, int idShelf);

    @Query("SELECT COUNT(b.id) FROM Book b WHERE b.id = ?1")
    int checkIsBookExist(int id);

    @Query(value = "SELECT COUNT(books) FROM shelf_books WHERE books = ?1 AND shelf_id = ?2", nativeQuery = true)
    int checkIsBookAddedAtShelf(int idBook, int idShelf);
}
