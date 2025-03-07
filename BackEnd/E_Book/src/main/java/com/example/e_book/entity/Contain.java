package com.example.e_book.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contain")
public class Contain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contain_id")
    private int containId;
    private int quantity;
    private int total;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @JsonManagedReference
    private Book book;
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    @JsonManagedReference
    private Cart cart;
}
