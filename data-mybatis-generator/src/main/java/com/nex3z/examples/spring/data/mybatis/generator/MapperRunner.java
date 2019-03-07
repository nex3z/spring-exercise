package com.nex3z.examples.spring.data.mybatis.generator;

import com.nex3z.examples.spring.data.mybatis.generator.mapper.BookMapper;
import com.nex3z.examples.spring.data.mybatis.generator.model.Book;
import com.nex3z.examples.spring.data.mybatis.generator.model.BookExample;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component @Slf4j
@MapperScan("com.nex3z.examples.data.mybatisgenerator.mapper")
public class MapperRunner implements ApplicationRunner {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Book book1 = new Book()
                .withTitle("The Three-Body Problem")
                .withPrice(Money.of(CurrencyUnit.of("USD"), 16.99))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        bookMapper.insert(book1);

        Book book2 = new Book()
                .withTitle("The Dark Forest")
                .withPrice(Money.of(CurrencyUnit.of("USD"), 17.99))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        bookMapper.insert(book2);

        Book book3 = new Book()
                .withTitle("Death's End")
                .withPrice(Money.of(CurrencyUnit.of("USD"), 17.99))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        bookMapper.insert(book3);

        Book book = bookMapper.selectByPrimaryKey(1L);
        log.info("run(): book = {}", book);

        BookExample example = new BookExample();
        example.createCriteria().andTitleEqualTo("The Dark Forest");
        List<Book> list1 = bookMapper.selectByExample(example);
        log.info("run(): list1 = {}", list1);

        BookExample example2 = new BookExample();
        example2.createCriteria().andPriceBetween(Money.of(CurrencyUnit.of("USD"), 17),
                Money.of(CurrencyUnit.of("USD"), 18));
        List<Book> list2 = bookMapper.selectByExample(example2);
        log.info("run(): list2 = {}", list2);
    }
}
