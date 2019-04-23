package com.nex3z.examples.spring.web.ssl.book.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOrder {

    private Long id;
    private String customer;
    private List<Book> items;
    private OrderState state;
    private Date createTime;
    private Date updateTime;

}
