package com.nex3z.examples.spring.data.mongo;

import com.mongodb.client.result.UpdateResult;
import com.nex3z.examples.spring.data.mongo.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component @Slf4j
public class Runner implements ApplicationRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Book book1 = Book.builder()
                .title("The Three-Body Problem")
                .price(Money.of(CurrencyUnit.of("USD"), 16.99))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Book saved1 = mongoTemplate.save(book1);
        log.info("run(): saved1 = {}", saved1);

        Book book2 = Book.builder()
                .title("The Dark Forest")
                .price(Money.of(CurrencyUnit.of("USD"), 17.99))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Book saved2 = mongoTemplate.save(book2);
        log.info("run(): saved2 = {}", saved2);

        List<Book> found = mongoTemplate.find(
                Query.query(Criteria.where("title").is("The Dark Forest")),
                Book.class
        );
        log.info("run(): found = {}", found);

        Thread.sleep(1000);

        UpdateResult result = mongoTemplate.updateFirst(
                Query.query(Criteria.where("title").is("The Dark Forest")),
                new Update().set("price", Money.of(CurrencyUnit.of("USD"), 10.87)).currentDate("updateTime"),
                Book.class
        );
        log.info("run(): result = {}", result);

        Book updated = mongoTemplate.findById(saved2.getId(), Book.class);
        log.info("run(): updated = {}", updated);

        if (updated == null) {
            throw new IllegalStateException("updated should not be null");
        }

        mongoTemplate.remove(updated);

        Book check = mongoTemplate.findById(saved2.getId(), Book.class);
        log.info("run(): check = {}", check);
    }

}
