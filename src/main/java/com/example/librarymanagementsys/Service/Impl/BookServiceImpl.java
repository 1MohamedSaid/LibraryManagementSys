package com.example.librarymanagementsys.Service.Impl;

import com.example.librarymanagementsys.Entity.Book;
import com.example.librarymanagementsys.Repo.BookRepo;
import com.example.librarymanagementsys.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepo bookRepo;

    @Override
    public ResponseEntity<List<Book>> getAllBooks(){
        try {
            return new ResponseEntity<>(bookRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getBookById(Long bookId){
        try {
            return new ResponseEntity<>(bookRepo.findById(bookId).get(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error getting book,please check your information and try again",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> addBook(Book book){
        try {
            Book book1 = new Book();
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setPublicationYear(book.getPublicationYear());
            book1.setISBN(book.getISBN());
            book1.setBorrowed(book.getBorrowed());
            bookRepo.save(book1);
            return new ResponseEntity<>("Book added successfully",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Couldn't add book",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> updateBookData(Long bookId,Book book){
        try {
            Book book1 = bookRepo.findById(bookId).get();
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setPublicationYear(book.getPublicationYear());
            book1.setISBN(book.getISBN());
            book1.setBorrowed(book.getBorrowed());
            bookRepo.save(book1);
            return new ResponseEntity<>("Book updated successfully",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Couldn't update book",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteBook(Long bookId){
        try {
            bookRepo.deleteById(bookId);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Couldn't delete book", HttpStatus.BAD_REQUEST);
    }
}
