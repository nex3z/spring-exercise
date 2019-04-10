package com.nex3z.spring.examples.webflux.simple.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

@Table("T_BOOK")
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class Book implements Serializable {

    @Id
    private Long id;

    private String title;

    private Money price;

    private Date createTime;

    private Date updateTime;
}
