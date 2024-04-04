package com.example.librarymanagementsys.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patrons")
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min =3, message = "Name field must be at least 3 characters")
    private String name;
    @NotBlank
    @Size(min = 4, max = 10, message = "Password length must be between 4 and 10 characters")
    private String password;
    @NotEmpty
    private String address;
    private String authority;
    private Long phoneNumber;
    @NotEmpty
    @Size(min = 14,max = 14, message = "Social security number must be exactly 14 digits")
    private Long socialSecurityNumber;
}
