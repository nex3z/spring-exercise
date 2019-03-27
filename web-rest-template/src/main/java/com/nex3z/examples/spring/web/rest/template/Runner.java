package com.nex3z.examples.spring.web.rest.template;

import com.nex3z.examples.spring.web.rest.template.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.attribute.standard.Media;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Component @Slf4j
public class Runner implements ApplicationRunner {
    private static final String URL = "http://localhost:8080/book/";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        get();
        post();
        parameterizedType();
    }

    private void get() {
        URI uri1 = UriComponentsBuilder
                .fromUriString(URL + "{id}")
                .build(1);
        ResponseEntity<Book> resp1 = restTemplate.getForEntity(uri1, Book.class);
        log.info("get(): response status = {}", resp1.getStatusCode());
        log.info("get(): response header = {}", resp1.getHeaders());
        log.info("get(): book = {}", resp1.getBody());

        URI uri2 = UriComponentsBuilder
                .fromUriString(URL + "?title={title}")
                .build("Dune");
        RequestEntity<Void> req = RequestEntity.get(uri2)
                .accept(MediaType.APPLICATION_XML)
                .build();
        ResponseEntity<String> resp2 = restTemplate.exchange(req, String.class);
        log.info("get(): response status = {}", resp2.getStatusCode());
        log.info("get(): response header = {}", resp2.getHeaders());
        log.info("get(): book xml = \n{}", resp2.getBody());

        String json = restTemplate.getForObject(URL, String.class);
        log.info("get(): all books = {}", json);
    }

    private void post() {
        Book newBook = Book.builder()
                .title("The Three-Body Problem")
                .price(Money.of(CurrencyUnit.of("CNY"), 16.99))
                .build();
        Book addedBook = restTemplate.postForObject(URL, newBook, Book.class);
        log.info("run(): addedBook = {}", addedBook);
    }

    private void parameterizedType() {
        ParameterizedTypeReference<List<Book>> ptr = new ParameterizedTypeReference<List<Book>>() {};
        ResponseEntity<List<Book>> resp = restTemplate
                .exchange(URL, HttpMethod.GET, null, ptr);
        List<Book> books = resp.getBody();
        log.info("parameterizedType(): books = {}", books);
    }

}
