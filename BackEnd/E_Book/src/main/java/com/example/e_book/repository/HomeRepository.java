package com.example.e_book.repository;

import com.example.e_book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface HomeRepository extends JpaRepository<Book,String> {
//    @Query(value = "select * from book", nativeQuery = true)
//    Page<Book> findAllBook(Pageable pageable);
    @Query(value = "select * from book" , nativeQuery = true)
    List<Book> findAllBook();


}
