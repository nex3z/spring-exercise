package com.nex3z.examples.spring.web.view.thymeleaf.controller;

import com.nex3z.examples.spring.web.view.thymeleaf.controller.request.NewOrderRequest;
import com.nex3z.examples.spring.web.view.thymeleaf.model.Book;
import com.nex3z.examples.spring.web.view.thymeleaf.model.BookOrder;
import com.nex3z.examples.spring.web.view.thymeleaf.service.BookOrderService;
import com.nex3z.examples.spring.web.view.thymeleaf.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/order")
@Slf4j
public class BookOrderController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookOrderService orderService;

    @GetMapping("/{id}")
    @ResponseBody
    public BookOrder getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/")
    @ResponseBody @ResponseStatus(HttpStatus.CREATED)
    public BookOrder create(@RequestBody NewOrderRequest request) {
        log.info("create(): request = {}", request);
        Book[] books = bookService.getBookByTitle(request.getItems()).toArray(new Book[] {});
        return orderService.createOrder(request.getCustomer(), books);
    }

    @ModelAttribute
    public List<Book> bookList() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/")
    public ModelAndView showCreateForm() {
        return new ModelAndView("create-order-form");
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createOrder(@Valid NewOrderRequest request, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            log.error("createOrder(): result = {}", result);
            map.addAttribute("message", result.toString());
            return "create-order-form";
        }
        log.info("createOrder(): request = {}", request);
        Book[] bookList = bookService.getBookByTitle(request.getItems()).toArray(new Book[] {});
        BookOrder order = orderService.createOrder(request.getCustomer(), bookList);
        return "redirect:/order/" + order.getId();
    }

}
