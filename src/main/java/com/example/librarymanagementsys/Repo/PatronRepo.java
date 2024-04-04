package com.example.librarymanagementsys.Repo;

import com.example.librarymanagementsys.Entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronRepo extends JpaRepository<Patron,Long> {
    Optional<Patron> findByName(String username);
}
