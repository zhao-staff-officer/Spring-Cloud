package com.cloud.staff.rocketmq.clustering;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @ClassName ClusteringProducer
 * @Description : 集群消费
 * @Author : 赵参谋
 * @Date : 2021/3/1 18:02
*/
public class ClusteringProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("Test");
        producer.setNamesrvAddr("192.168.33.21:9876");
        producer.start();
        for (int i=0;i<10;i++){
            Message message = new Message("TopicTest",("集群消费消息"+i).getBytes());
            producer.send(message);
        }
    }
}
