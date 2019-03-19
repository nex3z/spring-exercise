package com.nex3z.spring.examples.reactive.mongo;

import com.nex3z.spring.examples.reactive.mongo.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component @Slf4j
public class Runner implements ApplicationRunner {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    private CountDownLatch latch = new CountDownLatch(2);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        insert(() -> {
            log.info("run(): starting discount");
            discount();
        });

        latch.await();
    }

    private void insert(Runnable postAction) {
        List<Book> books = buildBooks();
        mongoTemplate.insertAll(books)
                .publishOn(Schedulers.elastic())
                .doOnNext(book -> log.info("insert doOnNext() 1: book = {}", book))
                .doOnComplete(postAction)
                .doFinally(signal -> {
                    latch.countDown();
                    log.info("insert doFinally(): signal = {}", signal);
                })
                .count()
                .subscribe(count -> log.info("insert onNext(): count = {}", count));
    }

    private void discount() {
        mongoTemplate.updateMulti(
                query(where("price").gte(1700L)),
                new Update().inc("price", -500L).currentDate("updateTime"), Book.class)
                .doFinally(signal -> {
                    latch.countDown();
                    log.info("discount doFinally(): signal = {}", signal);
                })
                .subscribe(updateResult -> log.info("discount onNext(): updateResult = {}", updateResult));
    }

    private List<Book> buildBooks() {
        Book book1 = Book.builder()
                .title("The Three-Body Problem")
                .price(Money.of(CurrencyUnit.of("USD"), 16.99))
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        Book book2 = Book.builder()
                .title("The Dark Forest")
                .price(Money.of(CurrencyUnit.of("USD"), 17.99))
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        return Arrays.asList(book1, book2);
    }

}
