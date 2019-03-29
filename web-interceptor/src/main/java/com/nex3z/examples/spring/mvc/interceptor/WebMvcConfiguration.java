package com.nex3z.examples.spring.mvc.interceptor;

import com.nex3z.examples.spring.mvc.interceptor.controller.PerformanceInterceptor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInterceptor())
                .addPathPatterns("/book/**")
                .addPathPatterns("/order/**");
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
        return builder -> {
            builder.indentOutput(true);
            builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        };
    }

}
