package com.nex3z.examples.spring.data.redis.service;

import com.nex3z.examples.spring.data.redis.model.Book;
import com.nex3z.examples.spring.data.redis.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service @Slf4j
public class BookService {
    private static final String CACHE = "data-redis-books";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RedisTemplate<String, Book> redisTemplate;

    public Optional<Book> findOneBook(String title) {
        HashOperations<String, String, Book> hashOperations = redisTemplate.opsForHash();
        if (redisTemplate.hasKey(CACHE) && hashOperations.hasKey(CACHE, title)) {
            log.info("findOneBook(): reading {} from Redis", title);
            return Optional.of(hashOperations.get(CACHE, title));
        }

        log.info("findOneBook(): reading {} from DB", title);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("title", exact().ignoreCase());
        Optional<Book> optBook = bookRepository.findOne(Example.of(Book.builder().title(title).build(), matcher));

        if (optBook.isPresent()) {
            log.info("findOneBook(): saving {} to Redis", title);
            hashOperations.put(CACHE, title, optBook.get());
            redisTemplate.expire(CACHE, 1, TimeUnit.MINUTES);
        }

        return optBook;
    }

}
