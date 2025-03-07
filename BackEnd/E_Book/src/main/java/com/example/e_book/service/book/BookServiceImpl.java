package com.example.e_book.service.book;


import com.example.e_book.entity.Book;
import com.example.e_book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book findBookById(String bookId) {
        return bookRepository.findBookById(bookId);
    }
}
