package com.nex3z.examples.spring.data.cache.redis;

import com.nex3z.examples.spring.data.cache.redis.model.Book;
import com.nex3z.examples.spring.data.cache.redis.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @Slf4j
public class Runner implements ApplicationRunner {

    @Autowired
    private BookService bookService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("run(): reading for first time");
        List<Book> books = bookService.findAllBooks();
        log.info("run(): first read, books = {}", books);

        for (int i = 0; i < 10; i++) {
            books = bookService.findAllBooks();
            log.info("run(): read from cache, books = {}", books);
        }

        bookService.reloadBooks();

        log.info("run(): reading after refresh");
        books = bookService.findAllBooks();
        log.info("run(): read after refresh, books = {}", books);

        Thread.sleep(5000);

        log.info("run(): reading after timeout");
        books = bookService.findAllBooks();
        log.info("run(): read after timeout, books = {}", books);
    }

}
