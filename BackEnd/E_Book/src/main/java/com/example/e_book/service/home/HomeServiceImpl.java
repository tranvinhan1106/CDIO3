package com.example.e_book.service.home;

import com.example.e_book.entity.Book;
import com.example.e_book.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeRepository homeRepository;


    //    public Page<Book> findAllBook(Pageable pageable) {
//        return homeRepository.findAllBook(pageable);
//    }
    @Override
    public List<Book> findAllBook() {
        return homeRepository.findAllBook();
    }
}
