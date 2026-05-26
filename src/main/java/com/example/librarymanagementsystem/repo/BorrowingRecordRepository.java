package com.example.librarymanagementsystem.repo;

import com.example.librarymanagementsystem.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    List<BorrowingRecord> findByUsername(String username);

    List<BorrowingRecord> findByBookId(String bookId);

    List<BorrowingRecord> findByStatus(String status);

    @Query("SELECT b FROM BorrowingRecord b WHERE b.dueDate < CURRENT_DATE AND b.status = 'BORROWED'")
    List<BorrowingRecord> findOverdueBooks();
}