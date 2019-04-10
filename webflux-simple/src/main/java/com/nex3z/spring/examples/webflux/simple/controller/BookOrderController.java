package com.nex3z.spring.examples.webflux.simple.controller;

import com.nex3z.spring.examples.webflux.simple.controller.request.NewOrderRequest;
import com.nex3z.spring.examples.webflux.simple.model.BookOrder;
import com.nex3z.spring.examples.webflux.simple.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
@Slf4j
public class BookOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Mono<BookOrder> getOrder(@PathVariable("id") Long id) {
        return orderService.getById(id);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookOrder> create(@RequestBody NewOrderRequest newOrder) {
        log.info("create(): newOrder = {}", newOrder);
        return orderService.create(newOrder.getCustomer(), newOrder.getItems())
                .flatMap(id -> orderService.getById(id));
    }
}
