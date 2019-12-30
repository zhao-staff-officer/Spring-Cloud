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
### 过期策略TTL
 - 消息过期策略
    - 管道：channel.queueDeclare 方法中加入x-message -ttl 参数
    - 单条：channel.basicPublish 方法中加入 expiration的属性参数
 - 队列过期策略
    - channel queueDeclare 方法中加入 expires 参数
### 优先级
  - queueDeclare 方法添加 x-max-priority 参数
  
### 持久化durable
 - exchange持久化
 - queue持久化

### 消息确认机制
 - 消费者消息确认
   - ack 确认接收
   - nack 拒绝接收
   
 - 发送者消息确认
   - spring.rabbbit.publisher-confirms: true 确认消息到达exchange 
   - spring.rabbbit.publisher-returns: true  确认消息到达queue
### 死信队列
   - channel.queueDeclare 方法中设置 x-dead-letter-exchange 参数来为这
     个队列添加 DLX 
## 存储机制
  - 进程架构
   ![image](https://github.com/zhao-staff-officer/Spring-Cloud/blob/master/Cloud-RabbitMQ/rabbitmq_msg_store.jpg)
  - 存储过程
  ![image](https://github.com/zhao-staff-officer/Spring-Cloud/blob/master/Cloud-RabbitMQ/rabbitmq_msg_process.jpg)
  - 队列结构
    - alpha: 消息内容(包括消息体、属性和 headers) 和消息索引都存储在内存中
    - beta: 消息内容保存在磁盘中，消息索引保存在内存中。
    - gamma: 消息内容保存在磁盘中，消息索引在磁盘和内存中都有
    - delta: 消息内容和索引都在磁盘中

