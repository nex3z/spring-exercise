package com.nex3z.spring.examples.reactive.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Book {

    private String id;
    private String title;
    private Money price;
    private Date createTime;
    private Date updateTime;

}
