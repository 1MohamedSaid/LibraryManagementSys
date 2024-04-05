package com.example.librarymanagementsys.Service;

import com.example.librarymanagementsys.Entity.Book;
import com.example.librarymanagementsys.Repo.BookRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookService bookService;
    @Mock
    private BookRepo bookRepo;

    private Book book1 = new Book(1L, "Title1", "Author1", new Date(), "ISBN1", false);

    private Book book2 = new Book(2L, "Title2", "Author2", new Date(), "ISBN2", false);


    @Test
    public void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        when(bookService.getAllBooks()).thenReturn(new ResponseEntity<>(books, HttpStatus.OK));
        ResponseEntity<List<Book>> response = bookService.getAllBooks();

        assertEquals("ssss",HttpStatus.OK, response.getStatusCode());
        assertEquals("Success",books, response.getBody());
    }

    @Test
    public void testGetBookById() {

        when(bookService.getBookById(ArgumentMatchers.anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> response = bookService.getBookById(1L);

        assertEquals("",HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetBookByIdNotFound() {


        when(bookService.getBookById(ArgumentMatchers.anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        ResponseEntity<?> response = bookService.getBookById(1L);

        assertEquals("", HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testAddBook() {
        Book book = new Book(1L, "Title1", "Author1", new Date(), "ISBN1", false);

        when(bookService.addBook(ArgumentMatchers.any())).thenReturn(new ResponseEntity<>("Book added successfully",HttpStatus.CREATED));

        ResponseEntity<String> response = bookService.addBook(book);

        assertEquals("" ,HttpStatus.CREATED, response.getStatusCode());
        assertEquals("","Book added successfully", response.getBody());
    }

    @Test
    public void testUpdateBookData() {
        Book existingBook = new Book(1L, "Title1", "Author1", new Date(), "ISBN1", false);
        Book updatedBook = new Book(1L, "UpdatedTitle", "UpdatedAuthor", new Date(), "UpdatedISBN", true);

        when(bookService.updateBookData(ArgumentMatchers.anyLong(), any())).thenReturn(new ResponseEntity<>("Book updated successfully",HttpStatus.CREATED));

        ResponseEntity<String> response = bookService.updateBookData(1L, updatedBook);

        assertEquals("",HttpStatus.CREATED, response.getStatusCode());
        assertEquals("","Book updated successfully", response.getBody());
    }

    @Test
    public void testDeleteBook() {
        when(bookService.deleteBook(ArgumentMatchers.anyLong())).thenReturn(new ResponseEntity<>("Book deleted successfully",HttpStatus.OK));

        ResponseEntity<String> response = bookService.deleteBook(1L);

        assertEquals("", HttpStatus.OK, response.getStatusCode());
        assertEquals("","Book deleted successfully", response.getBody());
    }
}
