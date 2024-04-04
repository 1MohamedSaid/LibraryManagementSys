package com.example.librarymanagementsys.Service;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface BorrowingRecordService {
    @Transactional
    ResponseEntity<String> borrowBook(Long bookId, Long patronId);
    @Transactional
    ResponseEntity<String> returnBook(Long bookId, Long patronId);
}
