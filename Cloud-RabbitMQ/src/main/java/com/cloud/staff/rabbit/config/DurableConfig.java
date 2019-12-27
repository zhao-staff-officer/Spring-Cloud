package com.cloud.staff.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据持久化
 */
@Configuration
public class DurableConfig {
     //定义持久化队列queue

//    /**
//     * The queue is durable, non-exclusive and non auto-delete.
//     *  默认申明一个持久化，非排他，非自动删除队列
//     * @param name the name of the queue.
//     */
//    public Queue(String name) {
//        this(name, true, false, false);
//    }
//
//    /**
//     * Construct a new queue, given a name and durability flag. The queue is non-exclusive and non auto-delete.
//     *
//     * @param name the name of the queue.
//     * @param durable true if we are declaring a durable queue (the queue will survive a server restart)
//                持久化：如果设置为ture则重启不会丢失数据
//
//     */
//    public Queue(String name, boolean durable) {
//        this(name, durable, false, false, null);
//    }
//
//    /**
//     * Construct a new queue, given a name, durability, exclusive and auto-delete flags.
//     * @param name the name of the queue.
//     * @param durable true if we are declaring a durable queue (the queue will survive a server restart)
//               持久化：如果设置为ture则重启不会丢失数据
//     * @param exclusive true if we are declaring an exclusive queue (the queue will only be used by the declarer's
//     * connection)
//              排他队列：如果是true，该queue只能被首次连接使用
//              排他队列注释：1，首次连接申明服务可用即Listner首次监听服务
//                           2，同一个服务可以申明多个Listner排他队列监听服务
//                           3：不同服务监听同一个排他队列，只有首次监听能访问数据，其他listner会被加锁
//                              ESOURCE_LOCKED - cannot obtain exclusive access to locked queue 'UserLogin2'
//     * @param autoDelete true if the server should delete the queue when it is no longer in use
//              自动删除，如果该队列没有任何订阅的消费者的话，该队列会被自动删除。这种队列适用于临时队列
//     */
//    public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete) {
//        this(name, durable, exclusive, autoDelete, null);
//    }


    //定义Queue
    @Bean
    public Queue queue(){
        return new Queue("durable-manual");
    }

//    /**
//     * Construct a new durable, non-auto-delete Exchange with the provided name.
//     * @param name the name of the exchange.
//     *             默认创建持久化，非删除exchange
//     */
//    public AbstractExchange(String name) {
//        this(name, true, false);
//    }


//    /**
//     * Construct a new Exchange, given a name, durability flag, and auto-delete flag, and
//     * arguments.
//     * @param name the name of the exchange.
//     * @param durable true if we are declaring a durable exchange (the exchange will
//     * survive a server restart)
//     * @param autoDelete true if the server should delete the exchange when it is no
//     * longer in use
//     * @param arguments the arguments used to declare the exchange
//     */
//    public AbstractExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
//        super();
//        this.name = name;
//        this.durable = durable;
//        this.autoDelete = autoDelete;
//        if (arguments != null) {
//            this.arguments = arguments;
//        }
//        else {
//            this.arguments = new HashMap<String, Object>();
//        }
//    }

    //定义exchange
    @Bean
    public FanoutExchange fountExchange(){
        return new FanoutExchange("durable_exchange");
    }

    //queque与exchange绑定
    @Bean
    public Binding bindexchangeA(Queue queue, FanoutExchange fountExchange){
        return BindingBuilder.bind(queue).to(fountExchange);
    }

}
