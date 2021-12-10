package com.cloud.staff.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @ClassName EchoService
 * @Description : 引导服务器
 *
 *   绑定到服务器将在其上监听并接受传入连接请求的端口
 *
 *   配置 Channel，以将有关的入站消息通知给 EchoServerHandler 实例
 *
 * @Author : 赵参谋
 * @Date : 2020/8/3 0:53
*/
public class EchoService {

    private final int port;

    public EchoService(int port){
        this.port=port;
    }

    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Usage:"+EchoService.class.getName()+"<port>");
        }
        int port = Integer.parseInt(args[0]);
        new EchoService(port).satrt();
    }

    public void satrt() {
       final EchoServiceHandler echoServiceHandler = new EchoServiceHandler();
       //创建EvenLoopGroup
       EventLoopGroup group =new NioEventLoopGroup();
       try{
         //创建ServerBootStrap
           ServerBootstrap bootstrap = new ServerBootstrap();
           bootstrap.group(group)
                   //指定NIO传输Channel
                   .channel(NioServerSocketChannel.class)
                   //使用指定的端口设置套接地址
                   .localAddress(new InetSocketAddress(port))
                   //添加EchoServiceHandle到子channel的ChannelPipeline
                   .childHandler(new ChannelInitializer<SocketChannel>() {
                       @Override
                       public void initChannel(SocketChannel channel) throws Exception {
                             //EchoService被标注为@Shareable,所以我们可以总是使用同样的实例
                             channel.pipeline().addLast(echoServiceHandler);
                       }
                   });
           //异步地绑定服务器，调用sync()方法柱塞等待直到绑定完成
           ChannelFuture  future = bootstrap.bind().sync();
           //获取Channel的closeFuture(),并柱塞当前线程知道它完成
           future.channel().closeFuture().sync();

       } catch (InterruptedException e) {
           e.printStackTrace();
       } finally {
           //关闭EventLoopGroup释放所有资源
           try {
               group.shutdownGracefully().sync();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }

}
