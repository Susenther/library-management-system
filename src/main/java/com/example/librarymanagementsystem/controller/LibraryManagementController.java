package com.example.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.librarymanagementsystem.entity.BookDetails;
import com.example.librarymanagementsystem.service.LibraryManagementService;

@RestController
@RequestMapping("/api/books")
public class LibraryManagementController {

    @Autowired
    private LibraryManagementService libraryManagementService;

    @PostMapping
    public ResponseEntity<String> addBookList(@RequestBody List<BookDetails> bookDetailsList) {
        return ResponseEntity.ok(libraryManagementService.addBookList(bookDetailsList));
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<String> deleteByTitle(@PathVariable String title) {
        BookDetails bookDetails = new BookDetails();
        bookDetails.setTitle(title);
        libraryManagementService.deleteBytitle(bookDetails);
        return ResponseEntity.ok("Book removed successfully");
    }

    @PutMapping("/{title}/status")
    public ResponseEntity<String> updateBookStatus(@PathVariable String title,
                                                   @RequestBody BookDetails bookDetails) {
        bookDetails.setTitle(title);
        libraryManagementService.updateBookStatus(bookDetails);
        return ResponseEntity.ok("Book updated successfully");
    }

    @GetMapping("/{title}")
    public ResponseEntity<BookDetails> findByTitle(@PathVariable String title) {
        BookDetails bookDetails = new BookDetails();
        bookDetails.setTitle(title);
        return ResponseEntity.ok(libraryManagementService.findByTitle(bookDetails));
    }

}