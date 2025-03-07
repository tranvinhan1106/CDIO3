package com.example.e_book.service.home;

import com.example.e_book.entity.Book;

import java.util.List;

public interface HomeService {
//    Page<Book> findAllBook(Pageable pageable);
    List<Book> findAllBook();
}
