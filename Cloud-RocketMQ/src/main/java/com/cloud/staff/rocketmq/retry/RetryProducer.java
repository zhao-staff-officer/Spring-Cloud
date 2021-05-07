package com.cloud.staff.rocketmq.retry;

import org.apache.rocketmq.client.Validators;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.CommunicationMode;
import org.apache.rocketmq.client.impl.producer.TopicPublishInfo;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @ClassName RetryProducer
 * @Description : 推送重试
 * @Author : 赵参谋
 * @Date : 2021/3/5 9:55
*/
public class RetryProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("mqGroup");
        producer.setNamesrvAddr("192.168.33.21:9876");
        //设置重试次数
        producer.setRetryTimesWhenSendFailed(2);
        //设置超时时间，5ms
        producer.setSendMsgTimeout(500000);
        producer.start();
        Message message =new Message("TopicTest","retryProducer","重试推送".getBytes());
        producer.send(message);
    }
}

//源码分析
//private SendResult sendDefaultImpl( Message msg, final CommunicationMode communicationMode,final SendCallback sendCallback,final long timeout) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
//    this.makeSureStateOK();
//    Validators.checkMessage(msg, this.defaultMQProducer);
//
//    final long invokeID = random.nextLong();
//      记录开始时间
//    long beginTimestampFirst = System.currentTimeMillis();
//    long beginTimestampPrev = beginTimestampFirst;
//    long endTimestamp = beginTimestampFirst;
//    查找推送topic
//    TopicPublishInfo topicPublishInfo = this.tryToFindTopicPublishInfo(msg.getTopic());
//    if (topicPublishInfo != null && topicPublishInfo.ok()) {
//        boolean callTimeout = false;
//        MessageQueue mq = null;
//        Exception exception = null;
//        SendResult sendResult = null;
//          如果是同步消息重试1+N 次,异步及单向消息推送一次
//        int timesTotal = communicationMode == CommunicationMode.SYNC ? 1 + this.defaultMQProducer.getRetryTimesWhenSendFailed() : 1;
//        int times = 0;
//        String[] brokersSent = new String[timesTotal];
//        for (; times < timesTotal; times++) {
//             分配推送队列，重试推送到同一队列
//            String lastBrokerName = null == mq ? null : mq.getBrokerName();
//            MessageQueue mqSelected = this.selectOneMessageQueue(topicPublishInfo, lastBrokerName);
//            if (mqSelected != null) {
//                mq = mqSelected;
//                  记录服务推送地址
//                brokersSent[times] = mq.getBrokerName();
//                try {
//                    beginTimestampPrev = System.currentTimeMillis();
//                      如果寻找推送服务地址超时，不推送
//                    long costTime = beginTimestampPrev - beginTimestampFirst;
//                    if (timeout < costTime) {
//                        callTimeout = true;
//                        break;
//                    }
//
//                    sendResult = this.sendKernelImpl(msg, mq, communicationMode, sendCallback, topicPublishInfo, timeout - costTime);
//                    endTimestamp = System.currentTimeMillis();
//                    this.updateFaultItem(mq.getBrokerName(), endTimestamp - beginTimestampPrev, false);
//                    switch (communicationMode) {
//                        case ASYNC:
//                            return null;
//                        case ONEWAY:
//                            return null;
//                        case SYNC:
//                            if (sendResult.getSendStatus() != SendStatus.SEND_OK) {
//                                if (this.defaultMQProducer.isRetryAnotherBrokerWhenNotStoreOK()) {
//                                    continue;
//                                }
//                            }
//
//                            return sendResult;
//                        default:
//                            break;
//                    }
//                } catch (RemotingException e) {}
