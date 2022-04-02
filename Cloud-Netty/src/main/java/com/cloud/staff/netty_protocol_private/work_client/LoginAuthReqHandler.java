package com.cloud.staff.netty_protocol_private.work_client;

import com.cloud.staff.netty_protocol_private.constans.MessageType;
import com.cloud.staff.netty_protocol_private.msg.NettyMessage;
import com.cloud.staff.netty_protocol_private.msg.NettyMessageHeader;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 握手请求
 */
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        NettyMessage message = (NettyMessage) msg;
        if(message.getHeader() !=null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()){
            byte loginResult = (byte) message.getBody();
            if(loginResult !=(byte) 0){
                ctx.close();
            }else {
                System.out.println("认证通过");
                ctx.fireChannelRead(msg);
            }
        }else {
            ctx.fireChannelRead(msg);
        }
    }


    private NettyMessage buildLoginReq(){
        NettyMessage message = new NettyMessage();
        NettyMessageHeader header = new NettyMessageHeader();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }
}
