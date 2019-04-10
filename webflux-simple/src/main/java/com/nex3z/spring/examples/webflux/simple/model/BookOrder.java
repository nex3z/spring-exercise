package com.nex3z.spring.examples.webflux.simple.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;

@Table("T_ORDER")
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class BookOrder {

    @Id
    private Long id;
    private String customer;
    private OrderState state;
    private List<Book> items;
    private Date createTime;
    private Date updateTime;

}
