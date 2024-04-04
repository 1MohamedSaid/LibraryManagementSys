package com.example.librarymanagementsys.Service.Impl;

import com.example.librarymanagementsys.Entity.Book;
import com.example.librarymanagementsys.Entity.BorrowingRecord;
import com.example.librarymanagementsys.Entity.Patron;
import com.example.librarymanagementsys.Repo.BookRepo;
import com.example.librarymanagementsys.Repo.BorrowRepo;
import com.example.librarymanagementsys.Repo.PatronRepo;
import com.example.librarymanagementsys.Service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BorrowRepo borrowRepo;

    @Autowired
    private PatronRepo patronRepo;

    @Override
    public ResponseEntity<String> borrowBook(Long bookId, Long patronId) {
        Patron patron = patronRepo.findById(patronId).orElse(null);
        Book book = bookRepo.findById(bookId).orElse(null);
        if (patron != null && book != null) {
            if (!book.getBorrowed()) { //false
                BorrowingRecord borrowingRecord = new BorrowingRecord();
                borrowingRecord.setPatron(patron);
                borrowingRecord.setBook(book);
                borrowingRecord.setBorrowingDate(LocalDateTime.now());
                borrowRepo.save(borrowingRecord);
                book.setBorrowed(true);
                bookRepo.save(book);
                return new ResponseEntity<>("Patron " + patron.getName() + " just borrowed the book: " + book.getTitle(), HttpStatus.OK);
            } else { //true
                return new ResponseEntity<>("Sorry this book is already borrowed", HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> returnBook(Long bookId,Long patronId) {
        Patron patron = patronRepo.findById(patronId).orElse(null);
        Book book = bookRepo.findById(bookId).orElse(null);
        BorrowingRecord borrowingRecord = borrowRepo.findByPatronAndReturnDateIsNull(patron);
        if(book!=null && patron !=null){
            if (borrowingRecord!=null && book.getBorrowed()) {
                book.setBorrowed(false);
                borrowingRecord.setReturnDate(LocalDateTime.now());
                borrowRepo.save(borrowingRecord);
                return new ResponseEntity<>("The book ' " + book.getTitle() + " '" + " has been returned successfully", HttpStatus.OK);
            }else if(!book.getBorrowed()) {
                return new ResponseEntity<>("Book already in library", HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("Error, check your information and try again", HttpStatus.BAD_REQUEST);
    }
}
