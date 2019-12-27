//package com.cloud.staff.rabbit.ackmanual;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Producer {
//
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    public void send(){
//        this.amqpTemplate.convertAndSend("ackmanual_exchange","","ackmanual发送数据");
//    }
//}
