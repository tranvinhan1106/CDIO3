package com.example.e_book.service.cart;

import com.example.e_book.entity.Book;
import com.example.e_book.entity.Contain;

import java.util.List;

public interface CartService {
    void addBookInToCart(Book book);

    List<Contain> findAllBookInCart(int cartId);

    void  deleteBookInCart(int cartId);

}
