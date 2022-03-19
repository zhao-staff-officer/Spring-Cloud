package com.cloud.staff.netty_messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * MessagePack 解码器
 * 从数据报arg1中获取需要解码的byte数组，然后调用messagepack的read将其反序列化为Object对象，
 * 将解码后的对象加入到解码列表arg2中，这样完成了MessagePack的解码操作
 * */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> out) throws Exception {
        final int length = msg.readableBytes();
        byte[] array = new byte[length];
        msg.getBytes(msg.readerIndex(),array,0,length);
        MessagePack msgPack = new MessagePack();
        out.add(msgPack.read(array));
    }
}
