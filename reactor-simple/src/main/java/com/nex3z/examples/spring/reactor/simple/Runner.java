package com.nex3z.examples.spring.reactor.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component @Slf4j
public class Runner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flux.range(1, 5)
                .doOnRequest(n -> log.info("doOnRequest(): {}", n))
                .doOnComplete(() -> log.info("doOnComplete() 1"))
                .map(n -> {
                    int value = n * 2;
                    log.info("map(): {} -> {}", n, value);
                    return value;
                })
                .doOnComplete(() -> log.info("doOnComplete() 2"))
                .subscribe(
                        n -> log.info("onNext(): {} {}", Thread.currentThread(), n),
                        e -> log.error("onError(): {}", e.toString()),
                        () -> log.info("onComplete()"),
						s -> s.request(4)
                );

        Thread.sleep(1000);
    }

}
