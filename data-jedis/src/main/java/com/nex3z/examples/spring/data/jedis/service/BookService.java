package com.nex3z.examples.spring.data.jedis.service;

import com.nex3z.examples.spring.data.jedis.model.Book;
import com.nex3z.examples.spring.data.jedis.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

}
