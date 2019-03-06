package com.nex3z.examples.spring.data.mybatis;

import com.github.pagehelper.PageInfo;
import com.nex3z.examples.spring.data.mybatis.mapper.BookMapper;
import com.nex3z.examples.spring.data.mybatis.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component @Slf4j
@MapperScan("com.nex3z.examples.data.mybatis.mapper")
public class Runner implements ApplicationRunner {

    @Autowired @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private BookMapper bookMMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        insert();
        paging();
    }

    private void insert() {
        Book saveBook = Book.builder()
                .title("Chapterhouse: Dune")
                .price(Money.of(CurrencyUnit.of("USD"), 9.99))
                .build();
        Long id = bookMMapper.save(saveBook);
        log.info("insert(): id = {}, saveBook = {}", id, saveBook);

        Book findBook = bookMMapper.findById(id);
        log.info("insert(): findBook = {}", findBook);
    }

    private void paging() {
        List<Book> rowBounds13 = bookMMapper.findAllWithRowBounds(new RowBounds(1, 3));
        log.info("paging(): rowBounds13 = {}", getNames(rowBounds13));
        List<Book> rowBounds23 = bookMMapper.findAllWithRowBounds(new RowBounds(2, 3));
        log.info("paging(): rowBounds23 = {}", getNames(rowBounds23));
        List<Book> rowBounds33 = bookMMapper.findAllWithRowBounds(new RowBounds(3, 3));
        log.info("paging(): rowBounds33 = {}", getNames(rowBounds33));
        List<Book> rowBounds10 = bookMMapper.findAllWithRowBounds(new RowBounds(1, 0));
        log.info("paging(): rowBounds10 = {}", getNames(rowBounds10));

        List<Book> params13 = bookMMapper.findAllWithParam(1, 3);
        log.info("paging(): params13 = {}", getNames(params13));

        List<Book> params23 = bookMMapper.findAllWithParam(2, 3);
        log.info("paging(): params23 = {}", getNames(params23));
        PageInfo pageInfo = new PageInfo<>(params23);
        log.info("paging(): pageInfo = {}", pageInfo);
    }

    private List<String> getNames(List<Book> list) {
        return list.stream()
                .map(book -> book.getId() + ": " + book.getTitle())
                .collect(Collectors.toList());
    }

}
