package com.cloud.staff.rabbit.direct;//package com.cloud.staff.rabbit.fanout;
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
//        this.amqpTemplate.convertAndSend("direct","direct发生数据");
//    }
//}
