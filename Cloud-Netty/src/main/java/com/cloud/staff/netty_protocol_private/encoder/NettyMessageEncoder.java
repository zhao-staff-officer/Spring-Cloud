package com.cloud.staff.netty_protocol_private.encoder;

import com.cloud.staff.netty_protocol_private.msg.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * 编码器
 */
public class NettyMessageEncoder extends MessageToMessageDecoder<NettyMessage> {


    @Override
    protected void decode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {

    }
}
