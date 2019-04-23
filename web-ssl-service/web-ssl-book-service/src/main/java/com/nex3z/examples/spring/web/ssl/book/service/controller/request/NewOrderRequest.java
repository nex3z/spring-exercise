package com.nex3z.examples.spring.web.ssl.book.service.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class NewOrderRequest {

    private String customer;

    private List<String> items;

}
