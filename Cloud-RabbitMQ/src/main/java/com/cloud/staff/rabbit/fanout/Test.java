package com.cloud.staff.rabbit.fanout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @Autowired
    private Producer producer;

    @RequestMapping("/fanout")
    public void sendMsg(){
        producer.send();
    }
}
