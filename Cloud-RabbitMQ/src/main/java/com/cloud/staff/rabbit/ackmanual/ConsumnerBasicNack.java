package com.cloud.staff.rabbit.ackmanual;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 接收消息-拒绝
 */
@Component
@RabbitListener(queues = {"ack-manual"})
public class ConsumnerBasicNack {

    @RabbitHandler
    public void process(String str, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){

        /**
         * Reject one or several received messages.
         *
         * Supply the <code>deliveryTag</code> from the {@link com.rabbitmq.client.AMQP.Basic.GetOk}
         * or {@link com.rabbitmq.client.AMQP.Basic.GetOk} method containing the message to be rejected.
         * @see com.rabbitmq.client.AMQP.Basic.Nack
         * @param deliveryTag the tag from the received {@link com.rabbitmq.client.AMQP.Basic.GetOk} or {@link com.rabbitmq.client.AMQP.Basic.Deliver}
         * @param multiple true to reject all messages up to and including
         * the supplied delivery tag; false to reject just the supplied
         * delivery tag.
         * @param requeue true if the rejected message(s) should be requeued rather
         * than discarded/dead-lettered
         *                被拒绝的是否重新入队列,true重入队列，false不入队列
         *                channel.basicNack 与 channel.basicReject 的区别在于basicNack可以拒绝多条消息，而basicReject一次只能拒绝一条消息
         * @throws java.io.IOException if an error is encountered
         */
        try {
            System.out.println("BasicNack接收消息:"+str);
            channel.basicNack(tag,false,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
