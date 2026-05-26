package com.example.librarymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagementsystem.entity.BookDetails;
import com.example.librarymanagementsystem.helper.RedisHelper;
import com.example.librarymanagementsystem.repo.LibraryManagementRepo;

@Service
public class LibraryManagementService {

    @Autowired
    private LibraryManagementRepo libraryManagementRepo;

    @Autowired
    private RedisHelper redisHelper;

    public String addBookList(List<BookDetails> bookDetailsList) {
        libraryManagementRepo.saveAll(bookDetailsList);
        return "Booklist added successfully";
    }

    public String deleteBytitle(BookDetails bookDetails) {
        redisHelper.deleteBytitle(bookDetails.getTitle());
        return "Book removed successfully";
    }

    public String updateBookStatus(BookDetails bookDetails) {
        redisHelper.updateBookStatus(bookDetails);
        return "Book updated successfully";
    }

    public BookDetails findByTitle(BookDetails bookDetails) {
        return redisHelper.findByTitle(bookDetails.getTitle());
    }

}
