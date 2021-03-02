package com.cloud.staff.rocketmq.clustering;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @ClassName
 * @Description : 集群消费1
 * @Author : 赵参谋
 * @Date : 2021/3/1 17:21
*/
public class ConsumerNodeMaster {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer ("rocketMqGroup1");
        consumer.setNamesrvAddr("192.168.33.21:9876");
        consumer.subscribe("TopicTest", "*");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs,context);
                for(MessageExt msg:msgs){
                    System.out.println("消费者消费数据1:"+new String(msg.getBody()));
                }
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者实例
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
