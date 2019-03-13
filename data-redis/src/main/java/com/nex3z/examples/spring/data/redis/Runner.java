package com.nex3z.examples.spring.data.redis;

import com.nex3z.examples.spring.data.redis.model.Book;
import com.nex3z.examples.spring.data.redis.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component @Slf4j
public class Runner implements ApplicationRunner {

    @Autowired
    private BookService bookService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<Book> book = bookService.findOneBook("Dune");
        log.info("run(): book = {}", book);

        for (int i = 0; i < 5; i++) {
            book = bookService.findOneBook("Dune");
        }
        log.info("run(): book from Redis: {}", book);
    }

}
