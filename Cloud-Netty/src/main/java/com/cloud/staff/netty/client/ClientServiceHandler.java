package com.cloud.staff.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

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
public class ClientServiceHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("client send msg : hello !",  CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
      System.out.println("client received:"+msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
