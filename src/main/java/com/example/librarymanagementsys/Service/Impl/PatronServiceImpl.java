package com.example.librarymanagementsys.Service.Impl;

import com.example.librarymanagementsys.Entity.Patron;
import com.example.librarymanagementsys.Exception.UserNotFoundException;
import com.example.librarymanagementsys.Repo.PatronRepo;
import com.example.librarymanagementsys.Service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatronServiceImpl implements PatronService {
    @Autowired
    PatronRepo patronRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<List<Patron>> getAllPatrons() {
        try {
            return new ResponseEntity<>(patronRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getPatronById (Long patronId){

        try {
            return new ResponseEntity<>(patronRepo.findById(patronId).get(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error getting patron,please check your information and try again",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> addPatron(Patron patron) {
        Patron patron1 = new Patron();
        patron1.setName(patron.getName());
        patron1.setPassword(passwordEncoder.encode(patron.getPassword()));
        patron1.setAddress(patron.getAddress());
        patron1.setPhoneNumber(patron.getPhoneNumber());
        patron1.setSocialSecurityNumber(patron.getSocialSecurityNumber());
        patron1.setAuthority(patron.getAuthority());
        patronRepo.save(patron1);
        return new ResponseEntity<>("Patron added successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> updatePatronData(Long patronId, Patron patron) {
        try {
            Patron patron1 = patronRepo.findById(patronId).get();
            patron1.setName(patron.getName());
            patron1.setAddress(patron.getAddress());
            patron1.setPhoneNumber(patron.getPhoneNumber());
            patron1.setSocialSecurityNumber(patron.getSocialSecurityNumber());
            patronRepo.save(patron1);
            return new ResponseEntity<>("Patron updated successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Couldn't update patron", HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<String> deletePatron(Long patronId) {
        try {
            patronRepo.deleteById(patronId);
            return new ResponseEntity<>("Patron deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Couldn't delete patron", HttpStatus.BAD_REQUEST);
    }
}

