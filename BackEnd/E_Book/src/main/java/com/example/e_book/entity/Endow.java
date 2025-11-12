package com.example.e_book.entity;

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
@Table(name = "endow")
public class Endow {
    @Id
    @Column(name = "endow_id")
    private String endowId;
    private String endowName;
    private String content;
    private Date time ;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name = "Apply" , joinColumns = @JoinColumn(name = "endow_id",referencedColumnName = "endow_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"))
    private List<Book> bookList;

}
