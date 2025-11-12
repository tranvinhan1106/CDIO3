package com.example.e_book.entity;

import com.example.e_book.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account" )
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;
    private String userName ;
    private String password;
    @OneToOne(mappedBy = "account")
    @JsonBackReference
    private Customer customer;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
