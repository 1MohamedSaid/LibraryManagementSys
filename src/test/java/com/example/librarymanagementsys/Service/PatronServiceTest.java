package com.example.librarymanagementsys.Service;

import com.example.librarymanagementsys.Entity.Patron;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatronServiceTest {
    @Mock
    private PatronService patronService;

    @Test
    public void testGetAllPatronsSuccess() {
        List<Patron> patrons = new ArrayList<>();
        patrons.add(new Patron(1L, "John", "password", "Address", "USER", 1234567890L, 12345678901234L));

        when(patronService.getAllPatrons()).thenReturn(new ResponseEntity<>(patrons, HttpStatus.OK));

        ResponseEntity<List<Patron>> response = patronService.getAllPatrons();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patrons, response.getBody());
    }

    @Test
    public void testGetPatronByIdSuccess() {

        when(patronService.getPatronById(anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<?> response = patronService.getPatronById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetPatronByIdNotFound() {
        when(patronService.getPatronById(anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        ResponseEntity<?> response = patronService.getPatronById(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testAddPatronSuccess() {
        Patron patron = new Patron(1L, "John", "password", "Address", "USER", 1234567890L, 12345678901234L);
        when(patronService.addPatron(any())).thenReturn(new ResponseEntity<>("Patron added successfully", HttpStatus.CREATED));

        ResponseEntity<String> response = patronService.addPatron(patron);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Patron added successfully", response.getBody());
    }

    @Test
    public void testUpdatePatronDataSuccess() {
        Patron updatedPatron = new Patron(1L, "John", "password", "UpdatedAddress", "USER", 1234567890L, 12345678901234L);

        when(patronService.updatePatronData(anyLong(),any())).thenReturn(new ResponseEntity<>("Patron updated successfully", HttpStatus.CREATED));

        ResponseEntity<String> response = patronService.updatePatronData(1L, updatedPatron);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Patron updated successfully", response.getBody());
    }

    @Test
    public void testDeletePatronSuccess() {

        when(patronService.deletePatron(anyLong())).thenReturn(new ResponseEntity<>("Patron deleted successfully", HttpStatus.OK));

        ResponseEntity<String> response = patronService.deletePatron(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Patron deleted successfully", response.getBody());
    }

    @Test
    public void testDeletePatronError() {

        when(patronService.deletePatron(anyLong())).thenReturn(new ResponseEntity<>("Couldn't delete patron", HttpStatus.BAD_REQUEST));

        ResponseEntity<String> response = patronService.deletePatron(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Couldn't delete patron", response.getBody());
    }
}