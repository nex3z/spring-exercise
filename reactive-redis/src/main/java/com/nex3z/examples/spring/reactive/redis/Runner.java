package com.nex3z.examples.spring.reactive.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component @Slf4j
public class Runner implements ApplicationRunner {
    private static final String KEY = "reactive-redis-books";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ReactiveStringRedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ReactiveHashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        CountDownLatch latch = new CountDownLatch(1);

        List<Book> books = jdbcTemplate.query(
                "SELECT * FROM t_book",
                (rs, i) -> Book.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("title"))
                        .price(rs.getLong("price"))
                        .build());

        Flux.fromIterable(books)
                .publishOn(Schedulers.single())
                .doOnComplete(() -> log.info("doOnComplete() 1"))
                .flatMap(book -> {
                    log.info("flatMap(): book = {}", book);
                    return hashOps.put(KEY, book.getTitle(), book.getPrice().toString());
                })
                .doOnComplete(() -> log.info("doOnComplete() 2"))
                .concatWith(redisTemplate.expire(KEY, Duration.ofMinutes(1)))
                .doOnComplete(() -> log.info("doOnComplete() 3"))
                .onErrorResume(e -> {
                    log.error("onErrorResume(): ", e);
                    return Mono.just(false);
                })
                .subscribe(
                        result -> log.info("onNext(): result = {}", result),
                        e -> log.error("onError(): e = {}", e.toString()),
                        () -> {
                            log.info("onComplete");
                            latch.countDown();
                        }
                );

        latch.await();
    }

}
