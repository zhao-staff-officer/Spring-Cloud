package com.cloud.staff.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.message")
public class ConsumnerA {

    @RabbitHandler
    public void process(String v){
         System.out.println("接收消息"+v);
    }

}
