#   Apollo
## 项目说明  
Apollo是携程开源的一套配置中心管理,作为替代SpringCloud-Config方案  
具有如下优势： 
- 提供统一管理页面  
- 管理不同开发环境：dev/fat/pro……
- 提供灰度发布
- 提供版本回滚
- 集成注册中心/配置中心：apollo-eurek、apollo-config
- 配置数据持久化到本地
- 不依赖cloudbus进行更新  
参考官网地址：https://github.com/ctripcorp/apollo

## 架构设计
- Config Service提供配置的读取、推送等功能，服务对象是Apollo客户端
- Admin Service提供配置的修改、发布等功能，服务对象是Apollo Portal（管理界面）
- Config Service和Admin Service都是多实例、无状态部署，所以需要将自己注册到Eureka中并保持心跳
- 在Eureka之上我们架了一层Meta Server用于封装Eureka的服务发现接口
- Client通过域名访问Meta Server获取Config Service服务列表（IP+Port），而后直接通过IP+Port访问服务，同时在Client侧会做load balance、错误重试
- Portal通过域名访问Meta Server获取Admin Service服务列表（IP+Port），而后直接通过IP+Port访问服务，同时在Portal侧会做load balance、错误重试
- 为了简化部署，我们实际上会把Config Service、Eureka和Meta Server三个逻辑角色部署在同一个JVM进程中

## 部署文档

