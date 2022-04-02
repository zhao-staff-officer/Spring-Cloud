package com.cloud.staff.netty_protocol_private.work_server;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：
 * @date 2022/4/2 10:17
 **/

import com.cloud.staff.netty_protocol_private.constans.MessageType;
import com.cloud.staff.netty_protocol_private.msg.NettyMessage;
import com.cloud.staff.netty_protocol_private.msg.NettyMessageHeader;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 服务端-心跳回复
 */
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        //如果是心跳数据-构建响应数据
        if(message.getHeader() !=null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()){
            System.out.println("Server receive client heart beat message :------>"+message);
            NettyMessage heartBeat = buildHeartBeat();
            ctx.writeAndFlush(heartBeat);
        }else{
            ctx.fireChannelRead(msg);
        }
    }

    /**
     * 构建心跳回复
     */
    private NettyMessage buildHeartBeat(){
        NettyMessage message = new NettyMessage();
        NettyMessageHeader header = new NettyMessageHeader();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }
}
