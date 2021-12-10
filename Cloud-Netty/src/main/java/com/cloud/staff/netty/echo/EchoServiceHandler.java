package com.cloud.staff.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


/**
 * @ClassName EchoService
 * @Description : 功能说明
 *
 * ChannelRead() -对于每个传入消息都要调用
 *
 * ChannelReadComplete() -通知ChannelInboundHandler最后一次对channelRead()的调用是当前批量读取中的最后一条消息
 *
 * exceptionCaught()—在读取操作期间，有异常抛出时会调用
 *
 * @Author : 赵参谋
 * @Date : 2020/8/3 0:41
*/
@ChannelHandler.Sharable
public class EchoServiceHandler extends ChannelInboundHandlerAdapter {

    /**
     * 接收消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Service received:"+in.toString(CharsetUtil.UTF_8));
        ctx.write(msg);
    }

    /**
     * 当前批量读取中最后一条消息
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 异常消息
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
