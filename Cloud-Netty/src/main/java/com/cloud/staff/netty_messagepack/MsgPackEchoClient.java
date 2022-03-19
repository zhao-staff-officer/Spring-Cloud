package com.cloud.staff.netty_messagepack;

import com.cloud.staff.netty_encode.UserInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class MsgPackEchoClient {

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new MsgPackEchoClient().connet(port, "127.0.0.1");
    }

    public void connet(int port, String host) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
                            socketChannel.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                            socketChannel.pipeline().addLast(new MsgEchoClientHandler(8));
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public class MsgEchoClientHandler extends ChannelInboundHandlerAdapter {

        private final int sendNumber;

        public MsgEchoClientHandler(int sendNumber) {
            this.sendNumber = sendNumber;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            UserInfo[] infos = userInfo();
            for (UserInfo infoE : infos) {
                ctx.write(infoE);
            }
            ctx.flush();
            System.out.println("------------------send-over---------------------");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("client receive the msgpack message:" + msg);
            ctx.write(msg);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
            cause.printStackTrace();
        }

        private UserInfo[] userInfo() {
            UserInfo[] userInfos = new UserInfo[sendNumber];
            UserInfo userInfo = null;
            for (int i = 0; i < sendNumber; i++) {
                userInfo = new UserInfo();
                userInfo.setUserId(i);
                userInfo.setUserName("ABCDEFG---->" + i);
                userInfos[i] = userInfo;
            }
            return userInfos;
        }
    }

}
