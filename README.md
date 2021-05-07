#  Spring-Cloud
## 项目说明
spring-cloud技术孵化实验
<a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg" alt="996.icu"></a>
  
## 微服务组件
### 注册中心
 - [√] Eureka
 - [×] Consul
### 配置中心
 - [√] Config
 - [√] Nacos
 - [√] Apollo
### 网关路由
 - [√] ZUUL
 - [×] Gateway
 > rabbion：负载均衡策略  routes：路由规则
### 熔断
 - [√] Hystrix
    - 隔离策略: 线程池隔离，信号量隔离
    - 熔断: 熔断策略配置
    - 服务监控：Hystrix-Dashboard
 - [√] Sentinel
   - Sentinel与apollo：数据持久化
### 服务调用
 - [√] Feign
### 链路追踪
 - [√] Zipkin
   -  zipkin+mysql
   -  zipkin+rabbitmq
 - [×] skyWalking
 - [×] pinpoint

## 常用技术栈
### 系统鉴权认证
 - jwt+security
 - shiro
### 限流 
 - ratelimit 
### 日志收集系统
 - [√] ELK 
   -  filebeat日志文件读取  
   -  kafka数据缓存  
   -  logstash数据清洗转换    
   -  elasticsearch数据存储  
   -  kibana数据展示  
### 接口调试工具
 - [√] Swagger
### 文件存储
   -  FastDFS
   -  Minio
### ORM
  - Mybatis-Plus：
    - 插件与代码生成器
  - JPA
### 数据库连接池
  - druid
### 缓存
  - redis√/
    - redisManager
    - codis
  - > **持久化**：rdb,aof,rdb+aof
  - > **复制**：Master+Cluter
  - > **过期策略**：volatile-lru，volatile-lfu，volatile-random，volatile-ttl，allkeys-lru，allkeys-lfu，allkeys-random，noeviction
  - > **集群去中心化/数据转移**：redis-cluter
  - > **缓存信息**：info
### JMS
  - kafka √
  - RabbitMQ
  - RocketMQ
  - flumn
### 数据库
  - Mysql
  - MongoDB
  - TiDB
### 数据库中间件
  - mycat
  - sharding：
### elasticsearch:搜索引擎 √
- **elasticsearch-head**：搜索可视化
- **es_ik**：中文分词器
- **go-mysql-elasticsearch**：数据同步 
## docker+jenkins：敏捷部署√
## gitlabCICD：持续集成√
## 其余
 - xxjob 定时任务√

需要文档邮件15196332744@163.com


