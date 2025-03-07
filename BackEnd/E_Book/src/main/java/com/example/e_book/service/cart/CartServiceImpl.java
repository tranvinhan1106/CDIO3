package com.example.e_book.service.cart;


import com.example.e_book.entity.Book;
import com.example.e_book.entity.Cart;
import com.example.e_book.entity.Contain;
import com.example.e_book.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public void addBookInToCart(Book book) {
        int defaultCartId = 1;
        int quantity = 1;
        int total = quantity * book.getRent();

        cartRepository.addBookToCart(defaultCartId, book.getBookId(), quantity, total);
    }

    @Override
    public List<Contain> findAllBookInCart(int cartId) {
        return  cartRepository.findBooksInContainByCartId(cartId);
    }

    @Override
    public void deleteBookInCart(int cartId) {
        cartRepository.deleteBookFromCartByContainId(cartId);
    }
}
