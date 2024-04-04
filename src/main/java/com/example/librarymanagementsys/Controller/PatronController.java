package com.example.librarymanagementsys.Controller;

import com.example.librarymanagementsys.Entity.Patron;
import com.example.librarymanagementsys.Service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PatronController {
    @Autowired
    PatronService patronService;

    @GetMapping("/patrons")
    public ResponseEntity<List<Patron>> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/patrons/{id}")
    public ResponseEntity<?> getPatronById(@PathVariable Long id){
        return patronService.getPatronById(id);
    }

    @PostMapping("/patrons")
    public ResponseEntity<String> addPatron(@RequestBody Patron patron){
        return patronService.addPatron(patron);
    }

    @PutMapping("/patrons/{id}")
    public ResponseEntity<String> updatePatronInfo(@PathVariable Long id,@RequestBody Patron patron){
        return patronService.updatePatronData(id, patron);
    }
    @DeleteMapping("/patrons/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable Long id){
        return patronService.deletePatron(id);
    }
}
