package com.cloud.staff.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AckmanualConfig {

    //定义Queue
    @Bean
    public Queue queue(){
        return new Queue("ack-manual");
    }

    //定义exchange
    @Bean
    public FanoutExchange  fountExchange(){
        return new FanoutExchange("ackmanual_exchange");
    }

    //queque与exchange绑定
    @Bean
    public Binding bindexchangeA(Queue queue, FanoutExchange fountExchange){
        return BindingBuilder.bind(queue).to(fountExchange);
    }


}
