package com.nex3z.examples.spring.data.cache;

import com.nex3z.examples.spring.data.cache.model.Book;
import com.nex3z.examples.spring.data.cache.service.BookService;
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
        int size = bookService.findAllBooks().size();
        log.info("run(): first read, size = {}", size);

        for (int i = 0; i < 10; i++) {
            log.info("run(): reading from cache");
            bookService.findAllBooks();
        }

        bookService.reloadBook();

        log.info("run(): reading after refresh");
        List<Book> list = bookService.findAllBooks();
        log.info("run(): list = {}", list);
    }

}
