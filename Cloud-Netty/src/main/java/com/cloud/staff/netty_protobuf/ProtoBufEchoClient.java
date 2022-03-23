package com.cloud.staff.netty_protobuf;

import com.cloud.staff.netty_encode.UserInfo;
import com.cloud.staff.netty_messagepack.MsgpackDecoder;
import com.cloud.staff.netty_messagepack.MsgpackEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class ProtoBufEchoClient {

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new ProtoBufEchoClient().connet(port, "127.0.0.1");
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

                            socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            socketChannel.pipeline().addLast(new ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()));

                            socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            socketChannel.pipeline().addLast(new ProtobufEncoder());

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
            for (int i=0,j=10;i<j;i++) {
                ctx.write(subReq(i));
            }
            ctx.flush();
            System.out.println("------------------send-over---------------------");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
            cause.printStackTrace();
        }

        private SubscribeReqProto.SubscribeReq subReq(int i){
            SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
            builder.setSubReqId(i);
            builder.setUserName("Lilinfeng");
            builder.setProductName("Netty book fro protobuf");

            List<String> address = new ArrayList<>();
            address.add("Nanjing YuHuaTai");
            address.add("Beijing LiuLiChang");
            builder.addAllAddress(address);
            return builder.build();
        }
    }

}
