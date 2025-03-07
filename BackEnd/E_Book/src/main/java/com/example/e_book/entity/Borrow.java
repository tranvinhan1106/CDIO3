package com.example.e_book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Borrow {
    @Id
    @Column(name = "borrow_id")
    private String borrowId;
    private String status;
    private Date borrowedDate;
    private Date paymentDate;

//    @OneToOne(mappedBy = "borow")
//    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer ;


    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @JsonIgnore
    private Book book;

    @OneToOne(mappedBy = "borrow")
    private Evaluate evaluate;



}
