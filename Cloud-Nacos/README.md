## Nacos技术文档
###1.0 Nacos-Server 部署
#### 1.1单机部署
 ##### 1.1.1下载jar包,上传至服务器
  https://github.com/alibaba/nacos/releases
 ##### 1.1.2 解压
  tar -xvf nacos-server-2.0.0.tar.gz
 ##### 1.1.3 启动
  cd ../bin/
  sh startup.sh -m standalone
 ##### 1.1.4 登录
  http://domain/naocs nacos/nacos

#### 1.2 集群部署
 #####1.2.1 服务器	 服务应用
    192.168.33.21	Nacos-serve1,Nginx
    192.168.33.22	Nacos-serve2
    192.168.33.23	Nacos-serve3
 #####1.2.2 上传jar 包
 #####1.2.3 修改配置信息
     vim cluster.conf
     192.168.33.21:8848
     192.168.33.22:8848
     192.168.33.23:8848
 #####1.2.4 启动服务
   - 使用内置数据库：sh startup.sh -p embedded
   - 使用外置数据库：sh startup.sh
   - 注：多网卡需设置网卡
 #####1.2.5 设置SLB直连模式
  修改配置nginx配置



####1.3 部署变量
  #### 1.3.1 指定数据库
  Nacos单机模式时nacos使用嵌入式数据库实现数据的存储，不方便观察数据存储的基本情况，0.7版本增加了支持mysql数据源能力

  ####1.3.1.1 初始化sql脚本
  文件地址：https://github.com/alibaba/nacos/blob/master/distribution/conf/nacos-mysql.sql

  ####1.3.1.2 修改配置文件
    vi cd conf/application.properties
    spring.datasource.platform=mysql
    db.num=1
    db.url.0=jdbc:mysql://11.162.196.16:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
    db.user=nacos
    db.password=nacos


  ####1.3.2 指定端口
  ./startup.sh -p 8848

  ####1.3.3 网卡设置
    指定nacos-ip
    vi nacos.inetutils.ip-address=10.11.105.155

    use-only-site-local-interfaces参数可以让nacos使用局域网ip，这个在nacos部署的机器有多网卡时很有用，可以让nacos选择局域网网
    vi nacos.inetutils.use-only-site-local-interfaces=true

    ignored-interfaces支持网卡数组，可以让nacos忽略多个网卡
    nacos.inetutils.ignored-interfaces[0]=eth0
    nacos.inetutils.ignored-interfaces[1]=eth1

    preferred-networks参数可以让nacos优先选择匹配的ip，支持正则匹配和前缀匹配
    nacos.inetutils.preferred-networks[0]=30.5.124.
    nacos.inetutils.preferred-networks[0]=30.5.124.(25[0-5]|2[0-4]\\d|((1d{2})|([1-9]?\\d))),30.5.124.(25[0-5]|2[0-4]\\d|((1d{2})|([1-9]?\\d)))




### 2.0 Nacos-Discovery服务注册

### 3.0 Nacos-config 配置中心





