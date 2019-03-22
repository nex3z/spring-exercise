package com.nex3z.examples.spring.reactive.r2dbc.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("t_book")
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Book {

    @Id
    private Long id;
    private String title;
    private Money price;
    private Date createTime;
    private Date updateTime;

}
