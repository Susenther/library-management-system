package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.BookDetails;
import com.example.librarymanagementsystem.entity.BorrowingRecord;
import com.example.librarymanagementsystem.repo.BorrowingRecordRepository;
import com.example.librarymanagementsystem.repo.LibraryManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private LibraryManagementRepo libraryManagementRepo;

    public String borrowBook(String username, String bookId) {
        BookDetails book = libraryManagementRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!"Available".equals(book.getAvailableStatus())) {
            return "Book is not available";
        }

        BorrowingRecord record = new BorrowingRecord();
        record.setBookId(bookId);
        record.setUsername(username);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(14));
        record.setStatus("BORROWED");

        borrowingRecordRepository.save(record);

        book.setAvailableStatus("Borrowed");
        libraryManagementRepo.save(book);

        return "Book borrowed successfully. Due date: " + record.getDueDate();
    }

    public String returnBook(Long recordId) {
        BorrowingRecord record = borrowingRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setReturnDate(LocalDate.now());
        record.setStatus("RETURNED");
        borrowingRecordRepository.save(record);

        BookDetails book = libraryManagementRepo.findById(record.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setAvailableStatus("Available");
        libraryManagementRepo.save(book);

        return "Book returned successfully";
    }

    public List<BorrowingRecord> getBorrowingHistory(String username) {
        return borrowingRecordRepository.findByUsername(username);
    }

    public List<BorrowingRecord> getOverdueBooks() {
        return borrowingRecordRepository.findOverdueBooks();
    }
}