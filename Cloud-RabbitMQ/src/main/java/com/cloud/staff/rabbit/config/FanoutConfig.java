//package com.cloud.staff.rabbit.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FanoutConfig {
//
//    //定义Queue
//    @Bean
//    public Queue queueA(){
//        return new Queue("Payment_2");
//    }
//
//    //定义exchange
//    @Bean
//    public FanoutExchange  fountExchange(){
//        return new FanoutExchange("Payment_exchange");
//    }
//
//    @Bean
//    public Queue queueB(){
//        return new Queue("fanout.B");
//    }
//
//    @Bean
//    public Queue queueC(){
//        return new Queue("fanout.C");
//    }
//
//
//    //queque与exchange绑定
//    @Bean
//    public Binding bindexchangeA(Queue queueA,FanoutExchange fountExchange){
//        return BindingBuilder.bind(queueA).to(fountExchange);
//    }
//
//    @Bean
//    public Binding bindexchangeB(Queue queueB,FanoutExchange fanoutExchange){
//        return BindingBuilder.bind(queueB).to(fanoutExchange);
//    }
//
//    @Bean
//    public Binding bindeexchangeC(Queue queueC,FanoutExchange fanoutExchange){
//        return BindingBuilder.bind(queueC).to(fanoutExchange);
//    }
//
//
//
//   //ip地址
//   @Value("${mq.ipAddress}")
//   private  String localIP;
//    //端口
//    @Value("${server.port}")
//    private String port;
//    //队列名称
//    @Value("${mq.paymentTopic}")
//    private String paymentTopic;
//
//
//    //定义queue消息队列,保证queue唯一性
//    @Bean
//    public Queue queue(){
//        return new Queue(paymentTopic +"_"+ localIP + "_"+port);
//    }
//
//    //定义exchange
//    @Bean
//    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange(paymentTopic);
//    }
//
//    //绑定交换机
//    @Bean
//    public Binding bindingExchange(Queue queue, FanoutExchange fanoutExchange){
//        return BindingBuilder.bind(queue).to(fanoutExchange);
//    }
//}
