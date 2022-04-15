package com.cloud.staff.netty_source;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：服务端
 * @date 2022/4/14 16:23
 **/
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
            ;

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
