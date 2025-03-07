package com.example.e_book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;
    @OneToOne
    @JoinColumn(name = "customer_id" , referencedColumnName = "customer_id")
    @JsonIgnore
    private Customer customer;
    @OneToMany(mappedBy = "cart")
    @JsonIgnore
    private List<Contain> contains;
}
