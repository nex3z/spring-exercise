package com.nex3z.examples.spring.reactive.r2dbc.repository;

import com.nex3z.examples.spring.reactive.r2dbc.repository.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

@Component @Slf4j
public class Runner implements ApplicationRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CountDownLatch latch = new CountDownLatch(2);

        bookRepository.findAllById(Flux.just(1L, 2L))
                .map(book -> book.getTitle() + ": " + book.getPrice())
                .doFinally(s -> latch.countDown())
                .subscribe(info -> log.info("findAllById: {}", info));

        bookRepository.findByTitle("Dune")
                .doFinally(s -> latch.countDown())
                .subscribe(book -> log.info("findByTitle: {}", book));
    }

}
