package com.nex3z.spring.examples.webflux.simple.service;

import com.nex3z.spring.examples.webflux.simple.model.BookOrder;
import com.nex3z.spring.examples.webflux.simple.model.OrderState;
import com.nex3z.spring.examples.webflux.simple.repository.BookOrderRepository;
import com.nex3z.spring.examples.webflux.simple.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private BookOrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    public Mono<BookOrder> getById(Long id) {
        return orderRepository.get(id);
    }

    public Mono<Long> create(String customer, List<String> items) {
        return Flux.fromStream(items.stream())
                .flatMap(n -> bookRepository.findByTitle(n))
                .collectList()
                .flatMap(l -> orderRepository.save(
                        BookOrder.builder()
                                .customer(customer)
                                .items(l)
                                .state(OrderState.INIT)
                                .createTime(new Date())
                                .updateTime(new Date())
                                .build())
                );
    }
}
