# RocketMQ

## 架构设计图
![images](https://github.com/zhao-staff-officer/Spring-Cloud/blob/master/Cloud-RocketMQ/rocketmq_architecture.jpg)  
### 架构说明
- Producer
  > 消息发布的角色，支持分布式集群方式部署。Producer通过MQ的负载均衡模块选择相应的Broker集群队列进行消息投递，投递的过程支持快速失败并且低延迟
- Consume
  > 消息消费的角色，支持分布式集群方式部署。支持以push推，pull拉两种模式对消息进行消费。同时也支持集群方式和广播方式的消费，它提供实时消息订阅机制，可以满足大多数用户的需求
- NameServer
  > NameServer是一个非常简单的Topic路由注册中心，其角色类似Dubbo中的zookeeper，支持Broker的动态注册与发现。主要包括两个功能：Broker管理，NameServer接受Broker集群的注册信息并且保存下来作为路由信息的基本数据。然后提供心跳检测机制，检查Broker是否还存活；路由信息管理，每个NameServer将保存关于Broker集群的整个路由信息和用于客户端查询的队列信息。然后Producer和Conumser通过NameServer就可以知道整个Broker集群的路由信息，从而进行消息的投递和消费。NameServer通常也是集群的方式部署，各实例间相互不进行信息通讯。Broker是向每一台NameServer注册自己的路由信息，所以每一个NameServer实例上面都保存一份完整的路由信息。当某个NameServer因某种原因下线了，Broker仍然可以向其它NameServer同步其路由信息，Producer,Consumer仍然可以动态感知Broker的路由的信息
- BrokerServer
  > Broker主要负责消息的存储、投递和查询以及服务高可用保证，为了实现这些功能，Broker包含了以下几个重要子模块
  - Remoting Module：整个Broker的实体，负责处理来自clients端的请求。
  - Client Manager：负责管理客户端(Producer/Consumer)和维护Consumer的Topic订阅信息
  - Store Service：提供方便简单的API接口处理消息存储到物理硬盘和查询功能
  - HA Service：高可用服务，提供Master Broker 和 Slave Broker之间的数据同步功能
### 消息类型
 - 同步消息
 - 异步消息
 - 单向消息
### 消费方式
 - BROADCUSTING 广播
 - CLUSTERING 集群消费 
 
