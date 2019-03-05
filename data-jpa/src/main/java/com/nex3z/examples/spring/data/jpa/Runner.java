package com.nex3z.examples.spring.data.jpa;

import com.nex3z.examples.spring.data.jpa.model.Book;
import com.nex3z.examples.spring.data.jpa.model.BookOrder;
import com.nex3z.examples.spring.data.jpa.model.OrderState;
import com.nex3z.examples.spring.data.jpa.repository.BookOrderRepository;
import com.nex3z.examples.spring.data.jpa.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component @Slf4j
public class Runner implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookOrderRepository orderRepository;

    @Override @Transactional
    public void run(String... args) throws Exception {
        initOrders();
        findOrders();
    }

    private void initOrders() throws InterruptedException {
        Book book1 = Book.builder()
                .title("The Three-Body Problem")
                .price(Money.of(CurrencyUnit.of("USD"), 16.99))
                .build();
        bookRepository.save(book1);
        log.info("initOrders(): book1 = {}", book1);

        Book book2 = Book.builder()
                .title("The Dark Forest")
                .price(Money.of(CurrencyUnit.of("USD"), 17.99))
                .build();
        bookRepository.save(book2);
        log.info("initOrders(): book2 = {}", book2);

        Book book3 = Book.builder()
                .title("Death's End")
                .price(Money.of(CurrencyUnit.of("USD"), 17.99))
                .build();
        bookRepository.save(book3);
        log.info("initOrders(): book3 = {}", book3);

        BookOrder order1 = BookOrder.builder()
                .customer("Li Lei")
                .items(Collections.singletonList(book1))
                .state(OrderState.INIT)
                .build();
        orderRepository.save(order1);
        log.info("initOrders(): order1 = {}", order1);

        Thread.sleep(100);

        BookOrder order2 = BookOrder.builder()
                .customer("Li Lei")
                .items(Arrays.asList(book2, book3))
                .state(OrderState.INIT)
                .build();
        orderRepository.save(order2);
        log.info("initOrders(): order2 = {}", order2);

        Thread.sleep(100);

        BookOrder order3 = BookOrder.builder()
                .customer("Han Meimei")
                .items(Collections.singletonList(book3))
                .state(OrderState.INIT)
                .build();
        orderRepository.save(order3);
        log.info("initOrders(): order3 = {}", order3);
    }

    private void findOrders() {
        bookRepository
                .findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(book -> log.info("findOrders(): book = {}", book));

        List<BookOrder> top2 = orderRepository.findTop2ByOrderByUpdateTimeDescIdAsc();
        log.info("findOrders(): top2 = {}", joinOrderIds(top2));

        List<BookOrder> findByCustomer = orderRepository.findByCustomerOrderById("Li Lei");
        log.info("findOrders(): findByCustomer = {}", joinOrderIds(findByCustomer));

        findByCustomer.forEach(order -> {
            log.info("findOrders(): order id = {}", order.getId());
            order.getItems().forEach(item -> log.info("findOrders(): order item = {}", item));
        });

        List<BookOrder> findByItemName = orderRepository.findByItems_Title("The Three-Body Problem");
        log.info("findOrders(): findByItemName = {}", joinOrderIds(findByItemName));
    }

    private String joinOrderIds(List<BookOrder> list) {
        return list.stream().map(o -> o.getId().toString())
                .collect(Collectors.joining(","));
    }
}
