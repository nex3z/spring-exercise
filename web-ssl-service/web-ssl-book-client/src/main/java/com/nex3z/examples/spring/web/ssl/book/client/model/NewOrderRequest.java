package com.nex3z.examples.spring.web.ssl.book.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter @Setter
public class NewOrderRequest {

    private String customer;
    private List<String> items;

}
