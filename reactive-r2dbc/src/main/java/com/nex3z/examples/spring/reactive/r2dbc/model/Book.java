package com.nex3z.examples.spring.reactive.r2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Book {

    private Long id;
    private String title;
    private Money price;
    private Date createTime;
    private Date updateTime;

}
