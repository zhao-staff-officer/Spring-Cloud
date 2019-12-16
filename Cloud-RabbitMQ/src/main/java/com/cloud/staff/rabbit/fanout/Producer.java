//package com.cloud.staff.rabbit.fanout;//package com.cloud.staff.rabbit.fanout;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Producer {
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    public void send(){
//        System.out.println("准备发生数据");
//        this.amqpTemplate.convertAndSend("FanoutExchange","","fanout发送数据");
//    }
//}
