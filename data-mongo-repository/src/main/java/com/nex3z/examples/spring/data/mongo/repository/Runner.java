package com.nex3z.examples.spring.data.mongo.repository;

import com.nex3z.examples.spring.data.mongo.repository.model.Book;
import com.nex3z.examples.spring.data.mongo.repository.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component @Slf4j
public class Runner implements ApplicationRunner {

    @Autowired
    private BookRepository coffeeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Book book1 = Book.builder()
                .title("The Three-Body Problem")
                .price(Money.of(CurrencyUnit.of("USD"), 16.99))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Book book2 = Book.builder()
                .title("The Dark Forest")
                .price(Money.of(CurrencyUnit.of("USD"), 17.99))
                .createTime(new Date())
                .updateTime(new Date()).build();
        coffeeRepository.insert(Arrays.asList(book1, book2));

        List<Book> findAll = coffeeRepository.findAll(Sort.by("title").descending());
        log.info("run(): findAll = {}", findAll);

        Thread.sleep(1000);

        book2.setPrice(Money.of(CurrencyUnit.of("USD"), 10.87));
        book2.setUpdateTime(new Date());
        coffeeRepository.save(book2);

        List<Book> findByTitle = coffeeRepository.findByTitle("The Dark Forest");
        log.info("run(): findByTitle = {}", findByTitle);

        coffeeRepository.deleteAll();
    }
}
