package com.example.librarymanagementsys.Service;

import com.example.librarymanagementsys.Entity.Patron;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatronService {
    ResponseEntity<List<Patron>> getAllPatrons();
    ResponseEntity<?> getPatronById(Long id);
    ResponseEntity<String> addPatron(Patron patron);
    ResponseEntity<String> updatePatronData(Long patronId,Patron patron);
    ResponseEntity<String> deletePatron(Long id);
}
