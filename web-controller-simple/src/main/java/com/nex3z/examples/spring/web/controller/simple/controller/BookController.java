package com.nex3z.examples.spring.web.controller.simple.controller;

import com.nex3z.examples.spring.web.controller.simple.controller.request.NewBookRequest;
import com.nex3z.examples.spring.web.controller.simple.service.BookService;
import com.nex3z.examples.spring.web.controller.simple.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody @ResponseStatus(HttpStatus.CREATED)
    public List<Book> batchAddBooks(@RequestParam("file") MultipartFile file) {
        List<Book> books = new ArrayList<>();
        if (!file.isEmpty()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = StringUtils.split(line, " ");
                    if (fields != null && fields.length == 2) {
                        books.add(bookService.saveBook(fields[0],
                                Money.of(CurrencyUnit.USD, NumberUtils.createBigDecimal(fields[1]))));
                    }
                }
            } catch (IOException e) {
                log.error("batchAddBooks(): ", e);
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }
        return books;
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
