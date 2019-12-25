//package com.cloud.staff.rabbit.ackmanual;
//
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * 接收消息-确认
// */
//@Component
//@RabbitListener(queues = {"ack-manual"})
//public class ConsumerBasicAck {
//    @RabbitHandler
//    public void process(String str, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){
//        try {
//            /**
//             * deliveryTag：（唯一标识 ID）：当一个消费者向 RabbitMQ 注册后，会建立起一个 Channel ，
//             *               RabbitMQ 会用 basic.deliver 方法向消费者推送消息，这个方法携带了一个 delivery tag，
//             *               它代表了 RabbitMQ 向该 Channel 投递的这条消息的唯一标识 ID，是一个单调递增的正整数，delivery tag 的范围仅限于 Channel
//             * multiple：为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
//             */
//            /**
//             * Acknowledge one or several received
//             * messages. Supply the deliveryTag from the {@link com.rabbitmq.client.AMQP.Basic.GetOk}
//             * or {@link com.rabbitmq.client.AMQP.Basic.Deliver} method
//             * containing the received message being acknowledged.
//             * @see com.rabbitmq.client.AMQP.Basic.Ack
//             * @param deliveryTag the tag from the received {@link com.rabbitmq.client.AMQP.Basic.GetOk} or {@link com.rabbitmq.client.AMQP.Basic.Deliver}
//             * @param multiple true to acknowledge all messages up to and
//             *                 ture时候会把小于deliveryTag的数据一次提交，false只提交当前deliveryTag
//             * including the supplied delivery tag; false to acknowledge just
//             * the supplied delivery tag.
//             * @throws java.io.IOException if an error is encountered
//             */
//            System.out.println("BasicAck接收消息"+str);
//            channel.basicAck(tag,false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
