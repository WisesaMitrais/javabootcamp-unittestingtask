package com.mitrais.booklibrarymanagement.repository;

import com.mitrais.booklibrarymanagement.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LibraryRepository extends JpaRepository<Shelf, Integer> {

    @Transactional
    @Modifying
    void setShelfCurrentCapacity(int current_capacity, int shelf_id);
}
