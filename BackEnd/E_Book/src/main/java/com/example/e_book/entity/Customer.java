package com.example.e_book.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String customerId;
    private String customerName;
    private boolean gender;
    private Date birthday;
    private String address ;
    private String phoneNumber ;
    private String email ;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @JsonBackReference
    private Account account;

    @OneToMany(mappedBy = "customer")
    private List<Borrow> borrows;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Cart cart;
}
