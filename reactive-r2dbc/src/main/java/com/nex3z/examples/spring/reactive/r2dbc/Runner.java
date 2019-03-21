package com.nex3z.examples.spring.reactive.r2dbc;

import com.nex3z.examples.spring.reactive.r2dbc.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;

@Component @Slf4j
public class Runner implements ApplicationRunner {

    @Autowired
    private DatabaseClient client;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CountDownLatch latch = new CountDownLatch(2);

        client.execute()
                .sql("SELECT * FROM T_BOOK")
                .as(Book.class)
                .fetch()
                .first()
                .doFinally(s -> latch.countDown())
                .subscribe(book -> log.info("subscribe() 1: book = {}", book));

        client.select()
                .from("t_book")
                .orderBy(Sort.by(Sort.Direction.DESC, "id"))
                .page(PageRequest.of(0, 3))
                .as(Book.class)
                .fetch()
                .all()
                .doFinally(s -> latch.countDown())
                .subscribeOn(Schedulers.elastic())
                .subscribe(book -> log.info("subscribe() 2: book = {}", book));

        latch.await();
    }

}
