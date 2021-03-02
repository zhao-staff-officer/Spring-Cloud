package com.cloud.staff.rocketmq.onway;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @ClassName OnewayProducer
 * @Description : 单向发送消息
 * @Author : 赵参谋
 * @Date : 2021/3/1 16:34
*/
public class OnewayProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("oneway");
        producer.setNamesrvAddr("192.168.33.21:9876");
        producer.start();
        Message message = new Message("TopicTest","测试单向发送".getBytes());
        producer.sendOneway(message);
    }
}
