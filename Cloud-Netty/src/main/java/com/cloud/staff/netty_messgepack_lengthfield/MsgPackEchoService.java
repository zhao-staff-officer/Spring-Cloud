package com.cloud.staff.netty_messgepack_lengthfield;

import com.cloud.staff.netty_messagepack.MsgpackDecoder;
import com.cloud.staff.netty_messagepack.MsgpackEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 在MessagePack-解码-之前增加LengthFileBasedDecode,用于处理半包消息，
 * 这样后面的MsgpackDecoder接收到的永远是整包消息。
 *
 *  +--------+---------------+      +--------------+
 *  |  0x00c | 'HELLO-WORLD' | ->   |‘HELLO-WORLD’ |
 *  +--------+---------------+      +--------------+
 *
 *
 * 在MessagePack-编码-之前增加LengthFieldPrepender,它将在ByteBuf 之前
 * 增加2个字节的消息长度字段
 * +--------------+    +--------+---------------+
 * |‘HELLO-WORLD’ | -> |  0x00c | 'HELLO-WORLD' |
 * +--------------+    +--------+---------------+
 *
 *
 */
public class MsgPackEchoService {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new MsgPackEchoService().bind(port);
    }

    public void bind(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                            socketChannel.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
                            socketChannel.pipeline().addLast(new LengthFieldPrepender(2));
                            socketChannel.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                            socketChannel.pipeline().addLast(new MsgEchoServerHandler());
                        }
                    });
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public class MsgEchoServerHandler extends ChannelInboundHandlerAdapter {

        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("注入链接" + ctx.channel().id().toString());
            ctx.fireChannelRegistered();
        }


        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            String body =  msg.toString();
            System.out.println("service receive the msgpack message" + msg);
            ctx.writeAndFlush(body);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
            cause.printStackTrace();
        }


    }
}
