package com.nex3z.examples.spring.mvc.view.jackson.controller;

import com.nex3z.examples.spring.mvc.view.jackson.controller.request.NewBookRequest;
import com.nex3z.examples.spring.mvc.view.jackson.model.Book;
import com.nex3z.examples.spring.mvc.view.jackson.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
    public Book addBookWithoutBindingResult(@Valid NewBookRequest newBook) {
        return bookService.saveBook(newBook.getTitle(), newBook.getPrice());
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody @ResponseStatus(HttpStatus.CREATED)
    public Book addJsonBookWithoutBindingResult(@Valid @RequestBody NewBookRequest newBook) {
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

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Book getById(@PathVariable Long id) {
        // hibernate 会延迟加载，先获取并输出查找到的 book
        Book book = bookService.getBookById(id);
        log.info("getById(): book = {}", book);
        return book;
    }

}
