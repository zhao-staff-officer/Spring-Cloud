package com.cloud.staff.netty_protocol_private.work_client;

import com.cloud.staff.netty_protocol_private.constans.MessageType;
import com.cloud.staff.netty_protocol_private.msg.NettyMessage;
import com.cloud.staff.netty_protocol_private.msg.NettyMessageHeader;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 客户端-心跳发送
 */
public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {

    private volatile ScheduledFuture<?> heartBeat;

    /**
     * 数据读取
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        NettyMessage message = (NettyMessage) msg;
        //认证完成,创建定时任务
        if(message.getHeader() !=null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()){
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx),0,5000, TimeUnit.MILLISECONDS);
        //服务器心跳信息
        }else if(message.getHeader() !=null && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()){
            System.out.println("Client receive server heart beat message :------>"+message);
        //传递下个处理
        }else{
            ctx.fireChannelRead(msg);
        }
    }

    /**
     * 异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        if(heartBeat !=null){
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }

    /**
     * 心跳
     */
    private class HeartBeatTask implements Runnable{
        private final ChannelHandlerContext ctx;
        public HeartBeatTask(ChannelHandlerContext ctx){
            this.ctx =ctx;
        }
        @Override
        public void run() {
            NettyMessage message = buildHeart();
            ctx.writeAndFlush(message);
        }
    }

    /**
     * 构建心跳信息
     */
    private NettyMessage buildHeart(){
        NettyMessage message = new NettyMessage();
        NettyMessageHeader header = new NettyMessageHeader();
        header.setType(MessageType.HEARTBEAT_REQ.value());
        message.setHeader(header);
        return message;
    }
}
