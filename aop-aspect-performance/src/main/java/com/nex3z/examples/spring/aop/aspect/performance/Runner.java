package com.nex3z.examples.spring.aop.aspect.performance;

import com.nex3z.examples.spring.aop.aspect.performance.repository.BookRepository;
import com.nex3z.examples.spring.aop.aspect.performance.service.BookOrderService;
import com.nex3z.examples.spring.aop.aspect.performance.service.BookService;
import com.nex3z.examples.spring.aop.aspect.performance.model.Book;
import com.nex3z.examples.spring.aop.aspect.performance.model.BookOrder;
import com.nex3z.examples.spring.aop.aspect.performance.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class Runner implements ApplicationRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookOrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Book> allBooks = bookRepository.findAll();
        log.info("run(): allBooks = {}", allBooks);

        Optional<Book> dune = bookService.findOneBook("Dune");
        log.info("run(): dune = {}", dune);

        if (dune.isPresent()) {
            BookOrder order = orderService.createOrder("Li Lei", dune.get());
            log.info("run(): INIT -> PAID {}", orderService.updateState(order, OrderState.PAID));
            log.info("run(): PAID -> INIT {}", orderService.updateState(order, OrderState.INIT));
        }
    }

}
