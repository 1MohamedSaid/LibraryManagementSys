package com.example.librarymanagementsys.Repo;

import com.example.librarymanagementsys.Entity.Book;
import com.example.librarymanagementsys.Entity.BorrowingRecord;
import com.example.librarymanagementsys.Entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepo extends JpaRepository<BorrowingRecord,Long> {
    BorrowingRecord findByBookId(Long bookId);
    void deleteByBookId(Long bookId);
    BorrowingRecord findByBook(Book book);
    BorrowingRecord findByPatronId(Long patronId);
    BorrowingRecord findByPatronAndReturnDateIsNull(Patron patron);


}
