package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.BorrowingRecord;
import com.example.librarymanagementsystem.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/{username}/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable String username,
                                             @PathVariable String bookId) {
        return ResponseEntity.ok(borrowingRecordService.borrowBook(username, bookId));
    }

    @PutMapping("/return/{recordId}")
    public ResponseEntity<String> returnBook(@PathVariable Long recordId) {
        return ResponseEntity.ok(borrowingRecordService.returnBook(recordId));
    }

    @GetMapping("/history/{username}")
    public ResponseEntity<List<BorrowingRecord>> getBorrowingHistory(
            @PathVariable String username) {
        return ResponseEntity.ok(borrowingRecordService.getBorrowingHistory(username));
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<BorrowingRecord>> getOverdueBooks() {
        return ResponseEntity.ok(borrowingRecordService.getOverdueBooks());
    }
}