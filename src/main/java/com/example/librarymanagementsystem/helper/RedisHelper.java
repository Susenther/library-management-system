package com.example.librarymanagementsystem.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.librarymanagementsystem.entity.BookDetails;
import com.example.librarymanagementsystem.repo.LibraryManagementRepo;

@Component
public class RedisHelper {

    @Autowired
    LibraryManagementRepo libraryManagementRepo;

    @CacheEvict(value = "BookDetails", key = "#title")
    public void deleteBytitle(String title) {
        libraryManagementRepo.deleteByTitle(title);
    }

    @CachePut(value = "BookDetails", key = "#bookDetails.title")
    public BookDetails updateBookStatus(BookDetails bookDetails) {
        BookDetails updateBookDetails = libraryManagementRepo.findByTitle(bookDetails.getTitle());
        updateBookDetails.setAvailableStatus(bookDetails.getAvailableStatus());
        return libraryManagementRepo.save(updateBookDetails);
    }

    @Cacheable(value = "BookDetails", key = "#title", unless = "#result == null")
    public BookDetails findByTitle(String title) {
        return libraryManagementRepo.findByTitle(title);
    }
}
