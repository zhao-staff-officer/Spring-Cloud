//package com.cloud.staff.rabbit.fanout;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RabbitListener(queues = "Payment")
//public class ConsumerB {
//
//    @RabbitHandler
//    public void process(String fanout){
//        System.out.println("B"+fanout);
//    }
//}
