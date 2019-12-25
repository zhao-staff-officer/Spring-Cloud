package com.cloud.staff.rabbit.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 发送失败回调
 * 通过实现 ReturnCallback 接口，启动消息失败返回，比如路由不到队列时触发回调
 */
@Component
public class RabbitmqReturnCallbackConfig implements RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setReturnCallback(this);             //指定 ReturnCallback
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息到达queue:"+"消息主体 message : "+message+"消息主体 message : "+replyCode+"描述："+replyText
        +"消息使用的交换器 exchange : "+exchange+"消息使用的路由键 routing : "+routingKey);
    }
}
