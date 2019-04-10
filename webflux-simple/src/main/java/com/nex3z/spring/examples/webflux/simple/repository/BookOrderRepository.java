package com.nex3z.spring.examples.webflux.simple.repository;

import com.nex3z.spring.examples.webflux.simple.model.Book;
import com.nex3z.spring.examples.webflux.simple.model.BookOrder;
import com.nex3z.spring.examples.webflux.simple.model.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class BookOrderRepository {

    @Autowired
    private DatabaseClient databaseClient;

    public Mono<BookOrder> get(Long id) {
        return databaseClient.execute()
                .sql("select * from t_order where id = " + id)
                .map((r, rm) ->
                        BookOrder.builder()
                                .id(id)
                                .customer(r.get("customer", String.class))
                                .state(OrderState.values()[r.get("state", Integer.class)])
                                .createTime(r.get("create_time", Date.class))
                                .updateTime(r.get("update_time", Date.class))
                                .items(new ArrayList<>())
                                .build()
                )
                .first()
                .flatMap(o ->
                        databaseClient.execute()
                                .sql("select b.* from t_book b, t_order_book ob " +
                                        "where b.id = ob.items_id and ob.book_order_id = " + id)
                                .as(Book.class)
                                .fetch()
                                .all()
                                .collectList()
                                .flatMap(l -> {
                                    o.getItems().addAll(l);
                                    return Mono.just(o);
                                })
                );
    }

    public Mono<Long> save(BookOrder order) {
        return databaseClient.insert().into("t_order")
                .value("customer", order.getCustomer())
                .value("state", order.getState().ordinal())
                .value("create_time", new Timestamp(order.getCreateTime().getTime()))
                .value("update_time", new Timestamp(order.getUpdateTime().getTime()))
                .fetch()
                .first()
                .flatMap(m -> Mono.just((Long) m.get("ID")))
                .flatMap(id -> Flux.fromIterable(order.getItems())
                        .flatMap(c -> databaseClient.insert().into("t_order_book")
                                .value("book_order_id", id)
                                .value("items_id", c.getId())
                                .then()).then(Mono.just(id)));
    }
}
