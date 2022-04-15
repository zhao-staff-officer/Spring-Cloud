package com.cloud.staff.netty_source;

import com.cloud.staff.netty_protocol_private.codec.NettyMessageDecoder;
import com.cloud.staff.netty_protocol_private.codec.NettyMessageEncoder;
import com.cloud.staff.netty_protocol_private.work_server.HeartBeatRespHandler;
import com.cloud.staff.netty_protocol_private.work_server.LoginAuthRespHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyService {

    public static void main(String[] args) {
        EventLoopGroup acceptGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(acceptGroup,workerGroup)
                    //指定channel类型
                    .channel(NioServerSocketChannel.class)
                    //非延迟发送
                    .option(ChannelOption.TCP_NODELAY,true)
                    //等待链接队列大小
                    .option(ChannelOption.SO_BACKLOG,1024)
                    //添加日志输出
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //添加channel-pinple
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyMessageDecoder(1024*1024,4,4,-8,0));
                            ch.pipeline().addLast(new NettyMessageEncoder());
                            ch.pipeline().addLast(new LoginAuthRespHandler());
                            ch.pipeline().addLast(new HeartBeatRespHandler());
                        }
                    })
            ;
            ChannelFuture f  = bootstrap.bind(8080).sync();
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
