package com.nex3z.examples.spring.web.controller.simple.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity @Table(name = "T_ORDER")
@Data @EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
@Builder @NoArgsConstructor @AllArgsConstructor
public class BookOrder extends BaseEntity {

    private String customer;

    @ManyToMany @JoinTable(name = "T_ORDER_BOOK") @OrderBy("id")
    private List<Book> items;

    @Enumerated @Column(nullable = false)
    private OrderState state;

}
