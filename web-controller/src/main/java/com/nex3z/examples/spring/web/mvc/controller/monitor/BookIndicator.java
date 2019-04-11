package com.nex3z.examples.spring.web.mvc.controller.monitor;

import com.nex3z.examples.spring.web.mvc.controller.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class BookIndicator implements HealthIndicator {

    @Autowired
    private BookService bookService;

    @Override
    public Health health() {
        long count = bookService.getBookCount();
        Health.Builder builder = Health.up()
                .withDetail("count", count);
        if (count > 0) {
            builder.withDetail("message", "on sale");
        } else {
            builder.withDetail("message", "sold out");
        }
        return builder.build();
    }
}
