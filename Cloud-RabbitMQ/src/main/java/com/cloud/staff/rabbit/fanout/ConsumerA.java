//package com.cloud.staff.rabbit.fanout;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RabbitListener(queues = {"${mq.paymentTopic}"+"_"+"${mq.ipAddress}"+"_"+"${server.port}"})
//public class ConsumerA {
//
//
//    @RabbitHandler
//    public void process(String fanout){
//        System.out.println("A"+fanout);
//    }
//}
