package com.nex3z.examples.spring.data.jedis;

import com.nex3z.examples.spring.data.jedis.model.Book;
import com.nex3z.examples.spring.data.jedis.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

@Component @Slf4j
public class Runner implements ApplicationRunner {
    private static final String KEY_BOOK_PRICE = "books";

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private BookService bookService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("run(): jedisPoolConfig = {}", jedisPoolConfig);

        try (Jedis jedis = jedisPool.getResource()) {
            bookService.findAllBooks().forEach(book -> writeJedis(jedis, book));

            Map<String, String> prices = jedis.hgetAll(KEY_BOOK_PRICE);
            log.info("run(): prices = {}", prices);

            String price = jedis.hget(KEY_BOOK_PRICE, "Dune");
            log.info("run(): price = {}", Money.ofMinor(CurrencyUnit.of("USD"), Long.parseLong(price)));
        }
    }

    private void writeJedis(Jedis jedis, Book book) {
        jedis.hset(KEY_BOOK_PRICE, book.getTitle(), Long.toString(book.getPrice().getAmountMinorLong()));
    }

}
