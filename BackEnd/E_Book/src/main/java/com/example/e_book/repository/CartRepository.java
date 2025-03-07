package com.example.e_book.repository;

import com.example.e_book.entity.Book;
import com.example.e_book.entity.Cart;
import com.example.e_book.entity.Contain;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<Contain, Integer> {

        @Transactional
        @Modifying
        @Query(value = "INSERT INTO contain (cart_id, book_id, quantity, total) VALUES (:cartId, :bookId, :quantity, :total)",
                nativeQuery = true)
        void addBookToCart(@Param("cartId") int cartId, @Param("bookId") String bookId, @Param("quantity") int quantity, @Param("total") int total);


        @Query("SELECT c FROM Contain c JOIN FETCH c.book WHERE c.cart.cartId = :cartId")
        List<Contain> findBooksInContainByCartId(@Param("cartId") int cartId);

        @Transactional
        @Modifying
        @Query("DELETE FROM Contain c WHERE c.containId = :containId")
        void deleteBookFromCartByContainId(@Param("containId") int containId);
}
