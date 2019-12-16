//package com.cloud.staff.rabbit.topic;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Producer {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void sendMsgA(){
//        this.rabbitTemplate.convertAndSend("zhao.exchange.test","topic.message","hi, i am message 1");
//    }
//
//    public void sendMsgB(){
//        this.rabbitTemplate.convertAndSend("zhao.exchange.test","topic.messages","hi, i am message 2");
//    }
//}
