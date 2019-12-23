//package com.cloud.staff.rabbit.head;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class Producer {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public  void sendMsg(Map<String, Object> head, String msg){
//        rabbitTemplate.convertAndSend("headExchangeA","","");
//    }
//
//    private Message getMessage(Map<String, Object> head, Object msg){
//        MessageProperties messageProperties = new MessageProperties();
//        for (Map.Entry<String, Object> entry : head.entrySet()) {
//            messageProperties.setHeader(entry.getKey(), entry.getValue());
//        }
//        MessageConverter messageConverter = new SimpleMessageConverter();
//        return messageConverter.toMessage(msg, messageProperties);
//    }
//
//
//}
