package com.example.e_book.repository;


import com.example.e_book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    @Query(value = "SELECT * FROM book WHERE book_id = :bookId", nativeQuery = true)
    Book findBookById(@Param("bookId") String bookId);

}
