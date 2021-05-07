package com.cloud.staff.rocketmq.retry;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @ClassName RetryConsumer
 * @Description : 重试消费
 * @Author : 赵参谋
 * @Date : 2021/3/5 15:56
*/
public class RetryConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test2Group");
        consumer.setNamesrvAddr("192.168.33.21:9876");
        consumer.subscribe("TopicTest","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for(MessageExt msg:msgs){
                    if(msg.getReconsumeTimes()>3){
                        System.out.println("超过重试次数,人工处理");
                    }else{
                        System.out.println("消费次数:"+msg.getReconsumeTimes()+","+(new String(msg.getBody()))+","+System.currentTimeMillis());
                    }
                }
                return  ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        consumer.start();
    }
}
