package com.example.librarymanagementsys.Security;

import com.example.librarymanagementsys.Entity.Patron;
import com.example.librarymanagementsys.Repo.PatronRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatronDetailsService implements UserDetailsService {


    @Autowired
    PatronRepo patronRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Patron> userInfo = patronRepo.findByName(username);
        return userInfo.map(PatronDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}


