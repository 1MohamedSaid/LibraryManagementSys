package com.example.librarymanagementsys.Service;

import com.example.librarymanagementsys.Entity.Book;
import com.example.librarymanagementsys.Entity.Patron;
import com.example.librarymanagementsys.Repo.BookRepo;
import com.example.librarymanagementsys.Repo.BorrowRepo;
import com.example.librarymanagementsys.Repo.PatronRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BorrowingRecordServiceTest {
    @Mock
    private BorrowingRecordService borrowingRecordService;

    Patron patron = new Patron(1L, "John", "password", "Address", "USER", 1234567890L, 12345678901234L);
    Book book = new Book(1L, "Title1", "Author1", new Date(), "ISBN1", false);

    @Test
    public void testBorrowBookSuccess() {

        when(borrowingRecordService.borrowBook(anyLong(), anyLong())).thenReturn(new ResponseEntity<>("Patron " + patron.getName() + " just borrowed the book: " + book.getTitle(), HttpStatus.OK));

        ResponseEntity<String> response = borrowingRecordService.borrowBook(1L, 1L);

        assertEquals("", HttpStatus.OK, response.getStatusCode());
        assertEquals("","Patron John just borrowed the book: Title1", response.getBody());
    }

    @Test
    public void testBorrowBookBookAlreadyBorrowed() {

        when(borrowingRecordService.borrowBook(anyLong(), anyLong())).thenReturn(new ResponseEntity<>("Sorry this book is already borrowed", HttpStatus.CONFLICT));

        ResponseEntity<String> response = borrowingRecordService.borrowBook(1L, 1L);

        assertEquals("", HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("", "Sorry this book is already borrowed", response.getBody());
    }

    @Test
    public void testBorrowBookInvalidPatronOrBook() {
        when(borrowingRecordService.borrowBook(anyLong(), anyLong())).thenReturn(new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST));

        ResponseEntity<String> response = borrowingRecordService.borrowBook(1L, 1L);

        assertEquals("", HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("", "ERROR", response.getBody());
    }

    @Test
    public void testReturnBookSuccess() {
        when(borrowingRecordService.returnBook(anyLong(), anyLong())).thenReturn(new ResponseEntity<>("The book ' " + book.getTitle() + " '" + " has been returned successfully", HttpStatus.OK));

        ResponseEntity<String> response = borrowingRecordService.returnBook(1L, 1L);

        assertEquals("", HttpStatus.OK, response.getStatusCode());
        assertEquals("", "The book ' Title1 ' has been returned successfully", response.getBody());
        assertFalse(book.getBorrowed());
    }

    @Test
    public void testReturnBookBookAlreadyInLibrary() {
        when(borrowingRecordService.returnBook(anyLong(), anyLong())).thenReturn(new ResponseEntity<>("Book already in library", HttpStatus.CONFLICT));

        ResponseEntity<String> response = borrowingRecordService.returnBook(1L, 1L);

        assertEquals("", HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("","Book already in library", response.getBody());
        assertFalse(book.getBorrowed());
    }

    @Test
    public void testReturnBookInvalidPatronOrBook() {

        when(borrowingRecordService.returnBook(anyLong(), anyLong())).thenReturn(        new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST));

        ResponseEntity<String> response = borrowingRecordService.returnBook(1L, 1L);

        assertEquals("", HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("" ,"Error", response.getBody());
    }
}
