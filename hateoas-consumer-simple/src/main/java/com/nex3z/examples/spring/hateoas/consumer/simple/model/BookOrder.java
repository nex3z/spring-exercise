package com.nex3z.examples.spring.hateoas.consumer.simple.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class BookOrder {

    private Long id;
    private String customer;
    private OrderState state;
    private Date createTime;
    private Date updateTime;

}
