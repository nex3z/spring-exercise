package com.nex3z.examples.spring.web.ssl.book.service.controller;

import com.nex3z.examples.spring.web.ssl.book.service.controller.request.NewBookRequest;
import com.nex3z.examples.spring.web.ssl.book.service.model.Book;
import com.nex3z.examples.spring.web.ssl.book.service.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid NewBookRequest newBook, BindingResult result) {
        if (result.hasErrors()) {
            log.warn("addBook(): binding error = {}", result);
            return null;
        }
        return bookService.saveBook(newBook.getTitle(), newBook.getPrice());
    }

    @GetMapping(path = "/", params = "!title")
    @ResponseBody
    public List<Book> getAll() {
        log.info("getAll(): {}", bookService.getAllBooks());
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/", params = "title")
    @ResponseBody
    public Book getByTitle(@RequestParam String title) {
        log.info("getByTitle(): title = {}", title);
        return bookService.getBookByTitle(title);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Book getById(@PathVariable Long id) {
        log.info("getById(): id = {}", id);
        return bookService.getBookById(id);
    }

}
