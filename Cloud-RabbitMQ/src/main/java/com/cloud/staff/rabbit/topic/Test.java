package com.cloud.staff.rabbit.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Autowired
    private Producer producer;

    @RequestMapping("/topic")
    public void sendMsg(){
        System.out.println("A发生消息");
        producer.sendMsgA();
        System.out.println("B发生消息");
        producer.sendMsgB();
    }
}
