package com.nex3z.examples.spring.web.ssl.book.client;

import com.nex3z.examples.spring.web.ssl.book.client.model.Book;
import com.nex3z.examples.spring.web.ssl.book.client.model.BookOrder;
import com.nex3z.examples.spring.web.ssl.book.client.model.NewOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class Runner implements ApplicationRunner {
    @Value("${service.base-url}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        readBooks();
        Long id = orderBook();
        queryOrder(id);
    }

    private void readBooks() {
        ParameterizedTypeReference<List<Book>> ptr =
                new ParameterizedTypeReference<List<Book>>() {};
        ResponseEntity<List<Book>> list = restTemplate
                .exchange(baseUrl + "/book/", HttpMethod.GET, null, ptr);
        log.info("readBooks(): books = {}", list.getBody());
    }

    private Long orderBook() {
        NewOrderRequest orderRequest = NewOrderRequest.builder()
                .customer("Li Lei")
                .items(Arrays.asList("Dune"))
                .build();
        RequestEntity<NewOrderRequest> request = RequestEntity
                .post(UriComponentsBuilder.fromUriString(baseUrl + "/order/").build().toUri())
                .body(orderRequest);
        ResponseEntity<BookOrder> response = restTemplate.exchange(request, BookOrder.class);
        log.info("orderBook(): status code = {}", response.getStatusCode());
        Long id = response.getBody().getId();
        log.info("orderBook(): order id = {}", id);
        return id;
    }

    private void queryOrder(Long id) {
        BookOrder order = restTemplate
                .getForObject(baseUrl + "/order/{id}", BookOrder.class, id);
        log.info("queryOrder(): order = {}", order);
    }
}
