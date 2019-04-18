#  Spring-Cloud
## 项目说明
`Spring-Cloud`是spring-cloud技术孵化实验
## <a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg" alt="996.icu"></a>
   
## Eureka√/consul：注册中心
## Config√/nacos√/apollo√：配置中心
## ZUUL√/gateway：网关
- **rabbion**：负载均衡策略×
- **routes**：路由规则√
- **ratelimit**：服务限流√
- **jwt+security**：权限验证√
## Hystrix√/Sentinel√：熔断
- **服务调用**：feign√
- **隔离策略**：线程池隔离，信号量隔离√
- **熔断**：熔断策略配置√
- **降级**：服务降级√
- **服务监控**：Hystrix-Dashboard√
- **Sentinel与apollo**：数据持久化√
## Sleuth√/skyWalking/pinpoint：链路追踪
- **zipkin+mysql**:数据库链路追踪√
- **zipkin+rabbitmq**:分布式数据聚合汇总√
## ELK日志收集系统
- **filebeat+kafka+logstash+es+kibana**：
    filebeat日志文件读取，kafka数据缓存，logstash数据清洗，es数据存储，kibana数据展示
## Swagger:接口调试工具√
## FastDFS：分布式文件存储系统√
## Mybatis-Plus：插件与代码生成器√
## druid：数据库连接池√
## redis√/redisManager√/codis×：缓存
- **持久化**：rdb,aof,rdb+aof
- **复制**：Master+Cluter
- **过期策略**：volatile-lru，volatile-lfu，volatile-random，volatile-ttl，allkeys-lru，allkeys-lfu，allkeys-random，noeviction
- **集群去中心化/数据转移**：redis-cluter
- **缓存信息**：info
## kafka √/RabbitMQ/RocketMQ/flumn：消息中间件
- **kafka重平衡**
## Mysql/MongoDB/TiDB：数据库
## mycat/sharding：数据库中间件
## elasticsearch:搜索引擎 √
- **elasticsearch-head**：搜索可视化
- **es_ik**：中文分词器
- **go-mysql-elasticsearch**：数据同步 
## docker+jenkins：敏捷部署√
## xxjob 定时任务√

#打√滴是系统已有集成，打×滴等我慢慢补吧！有些是文档与系统结合一起才有用：如中间件、数据库、搜索引擎、开源项目等。
需要文档邮件15196332744@163.com


