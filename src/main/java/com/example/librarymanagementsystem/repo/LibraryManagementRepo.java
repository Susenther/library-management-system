package com.example.librarymanagementsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementsystem.entity.BookDetails;

import jakarta.transaction.Transactional;

@Repository
public interface LibraryManagementRepo extends JpaRepository<BookDetails, String> {

    @Transactional
    void deleteByTitle(String title);

    public BookDetails findByTitle(String title);

}
