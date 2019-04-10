package com.nex3z.spring.examples.webflux.simple.controller;

import com.nex3z.spring.examples.webflux.simple.model.Book;
import com.nex3z.spring.examples.webflux.simple.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/", params = "!title")
    @ResponseBody
    public Flux<Book> getAll() {
        return bookService.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Mono<Book> getById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping(path = "/", params = "title")
    @ResponseBody
    public Mono<Book> getByTitle(@RequestParam String name) {
        return bookService.findByTitle(name);
    }
}
