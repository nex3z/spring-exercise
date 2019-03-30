package com.nex3z.examples.spring.staticresourcecache.controller;

import com.nex3z.examples.spring.staticresourcecache.controller.request.NewOrderRequest;
import com.nex3z.examples.spring.staticresourcecache.model.Book;
import com.nex3z.examples.spring.staticresourcecache.model.BookOrder;
import com.nex3z.examples.spring.staticresourcecache.service.BookOrderService;
import com.nex3z.examples.spring.staticresourcecache.service.BookService;
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
