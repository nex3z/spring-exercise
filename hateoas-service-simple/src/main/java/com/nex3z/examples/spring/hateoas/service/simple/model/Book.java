package com.nex3z.examples.spring.hateoas.service.simple.model;

import lombok.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity @Table(name = "T_BOOK")
@Data @EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
@Builder @NoArgsConstructor @AllArgsConstructor
public class Book extends BaseEntity implements Serializable {

    private String title;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@Parameter(name = "currencyCode", value = "USD")})
    private Money price;

}
