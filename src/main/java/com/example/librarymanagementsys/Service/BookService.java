package com.example.librarymanagementsys.Service;

import com.example.librarymanagementsys.Entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface BookService {
    ResponseEntity<List<Book>> getAllBooks();
    ResponseEntity<?> getBookById(Long bookId);
    ResponseEntity<String> addBook(Book book);
    ResponseEntity<String> updateBookData(Long bookId,Book book);
    ResponseEntity<String> deleteBook(Long bookId);
}
