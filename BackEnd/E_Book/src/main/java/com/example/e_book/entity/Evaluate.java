package com.example.e_book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evaluate")
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluate_id")
    private int evaluateId;
    private String content;
    private int star;
    @OneToOne
    @JoinColumn(name = "borrow_id", referencedColumnName = "borrow_id")
    private Borrow borrow;
}
