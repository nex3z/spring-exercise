package com.nex3z.examples.spring.web.ssl.book.service.controller;

import com.nex3z.examples.spring.web.ssl.book.service.controller.request.NewOrderRequest;
import com.nex3z.examples.spring.web.ssl.book.service.model.Book;
import com.nex3z.examples.spring.web.ssl.book.service.model.BookOrder;
import com.nex3z.examples.spring.web.ssl.book.service.service.BookOrderService;
import com.nex3z.examples.spring.web.ssl.book.service.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class BookOrderController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookOrderService orderService;

    @GetMapping("/{id}")
    public BookOrder getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public BookOrder create(@RequestBody NewOrderRequest request) {
        log.info("create(): request = {}", request);
        Book[] books = bookService.getBookByTitle(request.getItems()).toArray(new Book[] {});
        return orderService.createOrder(request.getCustomer(), books);
    }
}
