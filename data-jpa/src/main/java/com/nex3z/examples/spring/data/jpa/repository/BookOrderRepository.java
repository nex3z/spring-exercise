package com.nex3z.examples.spring.data.jpa.repository;

import com.nex3z.examples.spring.data.jpa.model.BookOrder;

import java.util.List;

public interface BookOrderRepository extends BaseRepository<BookOrder, Long>{

    List<BookOrder> findByCustomerOrderById(String customer);

    List<BookOrder> findByItems_Title(String title);

}
