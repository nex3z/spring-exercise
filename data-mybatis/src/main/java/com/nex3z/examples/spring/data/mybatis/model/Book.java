package com.nex3z.examples.spring.data.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Book {

    private Long id;

    private String title;

    private Money price;

    private Date createTime;

    private Date updateTime;

}
