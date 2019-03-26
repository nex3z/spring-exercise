package com.nex3z.examples.spring.web.view.thymeleaf.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter @Setter @ToString
public class NewOrderRequest {
    @NotEmpty
    private String customer;
    @NotEmpty
    private List<String> items;

}
