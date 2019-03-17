package com.nex3z.examples.spring.data.cache.redis.service;

import com.nex3z.examples.spring.data.cache.redis.model.Book;
import com.nex3z.examples.spring.data.cache.redis.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j
@CacheConfig(cacheNames = "data-cache-redis-books")
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Cacheable
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @CacheEvict
    public void reloadBooks() { }

}
