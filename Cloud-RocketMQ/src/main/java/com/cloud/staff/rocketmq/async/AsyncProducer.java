package com.cloud.staff.rocketmq.async;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @ClassName AsyncProducer
 * @Description : 异步消息
 * @Author : 赵参谋
 * @Date : 2021/3/1 16:18
*/
public class AsyncProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer =new DefaultMQProducer("async");
        producer.setNamesrvAddr("192.168.33.21:9876");
        producer.start();
        Message message = new Message("TopicTest","TageA","123456orderno","测试异步消息".getBytes());
        producer.send(message, new SendCallback() {

            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf(sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable e) {
                e.printStackTrace();
            }
        });
    }
}
