//package com.cloud.staff.rabbit.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FanoutConfig {
//
//    //定义exchange
//    @Bean
//    public FanoutExchange  fountExchange(){
//        return new FanoutExchange("FanoutExchange");
//    }
//
//    //定义Queue
//    @Bean
//    public Queue queueA(){
//        return new Queue("fanout.A");
//    }
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
//}
