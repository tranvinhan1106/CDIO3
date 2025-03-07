package com.example.e_book.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Book {
    @Id
    @Column(name = "book_id")
    private String bookId;
    private String bookName;
    private String author;
    private String publisher;
    private String yearOfPublication;
    private String content;
    private int rent;

    private String img ;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Borrow> borrows;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Contain> contain;


}
