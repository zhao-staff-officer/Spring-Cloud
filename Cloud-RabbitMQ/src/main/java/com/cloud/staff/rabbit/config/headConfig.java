package com.cloud.staff.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class headConfig {
    @Bean
    public Queue queueA(){
        return  new Queue("headA");
    }
    @Bean
    public Queue queueB(){
        return new Queue("headB");
    }

    @Bean
    public HeadersExchange headersExchangeA(){
        return new HeadersExchange("headExchangeA");
    }
    @Bean
    public HeadersExchange headersExchangeB(){
        return new HeadersExchange("headExchangeB");
    }

    @Bean
    public Binding headBindingA(Queue queueA,HeadersExchange headersExchangeA){
        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("name","zs");
        headerValues.put("age","18");
        return BindingBuilder.bind(queueA).to(headersExchangeA).whereAll(headerValues).match();
    }

    @Bean
    public Binding headBindingB(Queue queueA,HeadersExchange headersExchangeB){
        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("name","ls");
        headerValues.put("age","18");
        return BindingBuilder.bind(queueA).to(headersExchangeB).whereAll(headerValues).match();
    }
}
