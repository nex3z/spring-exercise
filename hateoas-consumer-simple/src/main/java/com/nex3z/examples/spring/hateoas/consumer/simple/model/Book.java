package com.nex3z.examples.spring.hateoas.consumer.simple.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class Book implements Serializable {

    private String title;
    private Money price;
    private Date createTime;
    private Date updateTime;

}
