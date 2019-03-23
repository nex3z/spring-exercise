package com.nex3z.examples.spring.aop.aspect.performance.service;

import com.nex3z.examples.spring.aop.aspect.performance.model.Book;
import com.nex3z.examples.spring.aop.aspect.performance.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@CacheConfig(cacheNames = "BookCache")
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> findOneBook(String title) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withMatcher("title", exact().ignoreCase());
        Optional<Book> book = bookRepository.findOne(Example.of(Book.builder().title(title).build(), matcher));
        log.info("findOneBook(): book = {}", book);
        return book;
    }

}
