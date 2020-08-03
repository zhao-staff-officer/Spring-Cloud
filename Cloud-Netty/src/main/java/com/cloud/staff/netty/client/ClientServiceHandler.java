package com.cloud.staff.netty.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;

/**
 * @ClassName ClientHandle
 * @Description : 客户端实现
 *
 *channelActive() -在服务器的链接已建立之后将调用
 *
 *channelRead0() -当从服务器接收到一条消息时被调用
 *
 *exceptionCaught() -在处理过程中引发一场调用
 *
 * @Return :
 * @Author : 赵参谋
 * @Date : 2020/8/3 14:10
*/
@ChannelHandler.Sharable
public class ClientServiceHandler extends SimpleChannelInboundHandler<ByteBuffer> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuffer msg) throws Exception {
      System.out.println("client recived:"+msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
