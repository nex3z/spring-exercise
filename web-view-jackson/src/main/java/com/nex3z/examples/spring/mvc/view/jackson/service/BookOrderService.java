package com.nex3z.examples.spring.mvc.view.jackson.service;

import com.nex3z.examples.spring.mvc.view.jackson.model.Book;
import com.nex3z.examples.spring.mvc.view.jackson.model.BookOrder;
import com.nex3z.examples.spring.mvc.view.jackson.model.OrderState;
import com.nex3z.examples.spring.mvc.view.jackson.repository.BookOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@Transactional
@Slf4j
public class BookOrderService {

    @Autowired
    private BookOrderRepository orderRepository;

    public BookOrder getOrderById(Long id) {
        return orderRepository.getOne(id);
    }

    public BookOrder createOrder(String customer, Book... books) {
        BookOrder order = BookOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(books)))
                .state(OrderState.INIT)
                .build();
        BookOrder saved = orderRepository.save(order);
        log.info("createOrder(): saved = {}", saved);
        return saved;
    }

    public boolean updateState(BookOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.error("updateState(): cannot update state from {} to {}", order.getState(), state);
            return false;
        }
        order.setState(state);
        BookOrder saved = orderRepository.save(order);
        log.info("updateState(): saved = {}", saved);
        return true;
    }
}
