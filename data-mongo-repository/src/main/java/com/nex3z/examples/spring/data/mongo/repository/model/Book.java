package com.nex3z.examples.spring.data.mongo.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Book {

    @Id
    private String id;

    private String title;

    private Money price;

    private Date createTime;

    private Date updateTime;

}
