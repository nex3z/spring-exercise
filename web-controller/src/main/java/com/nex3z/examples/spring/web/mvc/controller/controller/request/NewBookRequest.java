package com.nex3z.examples.spring.web.mvc.controller.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter @ToString
public class NewBookRequest {

    @NotEmpty
    private String title;

    @NotNull
    private Money price;

}
