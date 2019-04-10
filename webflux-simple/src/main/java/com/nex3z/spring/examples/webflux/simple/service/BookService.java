package com.nex3z.spring.examples.webflux.simple.service;

import com.nex3z.spring.examples.webflux.simple.model.Book;
import com.nex3z.spring.examples.webflux.simple.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Mono<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    public Mono<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
