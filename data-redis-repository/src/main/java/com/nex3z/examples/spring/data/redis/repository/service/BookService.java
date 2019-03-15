package com.nex3z.examples.spring.data.redis.repository.service;

import com.nex3z.examples.spring.data.redis.repository.model.Book;
import com.nex3z.examples.spring.data.redis.repository.model.BookCache;
import com.nex3z.examples.spring.data.redis.repository.repository.BookCacheRepository;
import com.nex3z.examples.spring.data.redis.repository.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service @Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCacheRepository bookCacheRepository;

    public Optional<Book> findSimpleBookFromCache(String title) {
        Optional<BookCache> optionalCache = bookCacheRepository.findOneByTitle(title);
        if (optionalCache.isPresent()) {
            BookCache bookCache = optionalCache.get();
            Book book = Book.builder()
                    .title(bookCache.getTitle())
                    .price(bookCache.getPrice())
                    .build();
            log.info("findSimpleBookFromCache(): read {} from cache", title);
            return Optional.of(book);
        } else {
            Optional<Book> optBook = findOneBook(title);
            if (optBook.isPresent()) {
                Book book = optBook.get();
                BookCache bookCache = BookCache.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .price(book.getPrice())
                        .build();
                log.info("findSimpleBookFromCache(): saving {} to cache", title);
                bookCacheRepository.save(bookCache);
            }
            return optBook;
        }
    }

    public Optional<Book> findOneBook(String title) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("title", exact().ignoreCase());
        Optional<Book> book = bookRepository.findOne(
                Example.of(Book.builder().title(title).build(), matcher));
        log.info("findOneBook(): book = {}", book);
        return book;
    }

}
