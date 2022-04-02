package com.cloud.staff.netty_protocol_private.work_client;

import com.cloud.staff.netty_protocol_private.codec.NettyMessageDecoder;
import com.cloud.staff.netty_protocol_private.codec.NettyMessageEncoder;
import com.cloud.staff.netty_protocol_private.constans.NettyConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：客户端
 * @date 2022/4/2 10:42
 **/
public class WorkClient {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    EventLoopGroup group = new NioEventLoopGroup();

    public static void main(String[] args) throws Exception {
        new WorkClient().connect(NettyConstant.PORT,NettyConstant.REMOTEIP);
    }


    public void connect(int port,String host) throws Exception {
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyMessageDecoder(1024*1024,4,4));
                            ch.pipeline().addLast(new NettyMessageEncoder());
                            ch.pipeline().addLast(new ReadTimeoutHandler(50));
                            ch.pipeline().addLast(new LoginAuthReqHandler());
                            ch.pipeline().addLast(new HeartBeatReqHandler());
                        }
                    });
            ChannelFuture future = b.connect("127.0.0.1",8080).sync();
            future.channel().closeFuture().sync();
        }finally {
            executor.execute(()->{
                try{
                    TimeUnit.SECONDS.sleep(5);
                    connect(NettyConstant.PORT,NettyConstant.REMOTEIP);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
    }
}
