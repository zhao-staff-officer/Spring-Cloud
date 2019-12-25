# RabbitMQ消息队列
## 模型设计
![image](https://github.com/zhao-staff-officer/Spring-Cloud/blob/master/Cloud-RabbitMQ/RabbitMQ1.png)
## Exchange交换机类型
- fanout
> 路由广播的形式,将会把消息发给绑定它的全部队列
- dirct
> RabbitMQ默认的交换机模式,也是最简单的模式.即创建消息队列的时候,指定一个BindingKey.当发送者发送消息的时候,指定对应的Key.
  当Key和消息队列的BindingKey一致的时候,消息将会被发送到该消息队列中
- topic
> 转发信息主要是依据通配符,队列和交换机的绑定主要是依据一种模式(通配符+字符串),而当发送消息的时候,
  只有指定的Key和该模式相匹配的时候,消息才会被发送到该消息队列中
- headers
> 是根据一个规则进行匹配,在消息队列和交换机绑定的时候会指定一组键值对规则,而发送消息的时候也会指定一组键值对规则,
  当两组键值对规则相匹配的时候,消息会被发送到匹配的消息队列中
## 策略配置
### durable持久化

### 消息确认机制
 - 消费者消息确认
   - ack 确认接收
   - nack 拒绝接收
   
 - 发送者消息确认
   - spring.rabbbit.publisher-confirms: true 确认消息到达exchange 
   - spring.rabbbit.publisher-returns: true  确认消息到达queue

