//package com.cloud.staff.rabbit.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TopicaConfig {
//    private  static final String topicA="topic.message";
//    private static final String topicB="topic.messages";
//
//    //定义队列
//    @Bean
//    public Queue queueA(){
//        return new Queue(TopicaConfig.topicA);
//    }
//    @Bean
//    public Queue queueB(){
//        return new Queue(TopicaConfig.topicB);
//    }
//
//    //定义交换机
//    @Bean
//    public TopicExchange exchange(){
//       return new TopicExchange("zhao.exchange.test");
//    }
//
//    //将队列与交换机绑定
//    @Bean
//    public Binding bindingExchangeA(Queue queueA,TopicExchange exchange){
//           return BindingBuilder.bind(queueA).to(exchange).with("topic.message");
//    }
//    @Bean
//    public Binding bindingExchhangeB(Queue queueB,TopicExchange exchange){
//        return BindingBuilder.bind(queueB).to(exchange).with("topic.#");
//    }
//
//}
