package com.cloud.staff.netty_messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * MessagePack 编码器
 * 继承MessageToByteEncoder,它负责将Object类型的POJO对象编码为byte
 * 然后写入ByteBuf中
 *
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf out) throws Exception {
        MessagePack msgPack = new MessagePack();
        out.writeBytes(msgPack.write(msg));
    }
}
