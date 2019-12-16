package com.cloud.staff.rabbit.head;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  void sendMsg(){
        rabbitTemplate.convertAndSend("headExchangeA","","");
    }

}
