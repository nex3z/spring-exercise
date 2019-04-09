package com.nex3z.examples.spring.hateoas.consumer.simple;

import com.nex3z.examples.spring.hateoas.consumer.simple.model.Book;
import com.nex3z.examples.spring.hateoas.consumer.simple.model.BookOrder;
import com.nex3z.examples.spring.hateoas.consumer.simple.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component @Slf4j
public class Runner implements ApplicationRunner {
    private static final URI ROOT_URI = URI.create("http://localhost:8080/");

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Link bookLink = getLink(ROOT_URI,"books");
        getBooks(bookLink);
        Resource<Book> book = addBook(bookLink);

        Link orderLink = getLink(ROOT_URI, "orders");
        addOrder(orderLink, book);
        queryOrders(orderLink);
    }

    private Link getLink(URI uri, String rel) {
        ResponseEntity<Resources<Link>> rootResp = restTemplate.exchange(uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Link>>() {});
        Link link = rootResp.getBody().getLink(rel);
        log.info("getLink(): link = {}", link);
        return link;
    }

    private void getBooks(Link bookLink) {
        ResponseEntity<PagedResources<Resource<Book>>> resp =
                restTemplate.exchange(bookLink.getTemplate().expand(), HttpMethod.GET, null,
                        new ParameterizedTypeReference<PagedResources<Resource<Book>>>() {});
        log.info("getBooks(): books = {}", resp.getBody());
    }

    private Resource<Book> addBook(Link link) {
        Book book = Book.builder()
                .title("The Three-Body Problem")
                .price(Money.of(CurrencyUnit.USD, 16.99))
                .build();
        RequestEntity<Book> req =
                RequestEntity.post(link.getTemplate().expand()).body(book);
        ResponseEntity<Resource<Book>> resp =
                restTemplate.exchange(req,
                        new ParameterizedTypeReference<Resource<Book>>() {});
        log.info("addBook(): resp = {}", resp);
        return resp.getBody();
    }

    private void addOrder(Link link, Resource<Book> book) {
        BookOrder newOrder = BookOrder.builder()
                .customer("Li Lei")
                .state(OrderState.INIT)
                .build();
        RequestEntity<?> req = RequestEntity.post(link.getTemplate().expand()).body(newOrder);
        ResponseEntity<Resource<BookOrder>> addOrderResp = restTemplate.exchange(req,
                new ParameterizedTypeReference<Resource<BookOrder>>() {});
        log.info("addOrder(): addOrderResp = {}", addOrderResp);

        Resource<BookOrder> order = addOrderResp.getBody();
        Link items = order.getLink("items");
        req = RequestEntity.post(items.getTemplate().expand()).body(book.getLink("self"));
        ResponseEntity<String> addItemResp = restTemplate.exchange(req, String.class);
        log.info("addOrder(): addItemResp = {}", addItemResp);
    }

    private void queryOrders(Link link) {
        ResponseEntity<String> resp = restTemplate.getForEntity(link.getTemplate().expand(), String.class);
        log.info("queryOrders(): resp = {}", resp);
    }
}
