package com.nex3z.examples.spring.mvc.interceptor.service;

import com.nex3z.examples.spring.mvc.interceptor.model.Book;
import com.nex3z.examples.spring.mvc.interceptor.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "BookCache")
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(String title, Money price) {
        Book book = Book.builder()
                .title(title)
                .price(price)
                .build();
        return bookRepository.save(book);
    }

    @Cacheable
    public List<Book> getAllBooks() {
        return bookRepository.findAll(Sort.by("id"));
    }

    public Book getBookById(Long id) {
        return bookRepository.getOne(id);
    }

    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBookByTitle(List<String> titles) {
        return bookRepository.findByTitleInOrderById(titles);
    }

}
