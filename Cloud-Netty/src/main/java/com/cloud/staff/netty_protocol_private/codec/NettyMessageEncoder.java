package com.cloud.staff.netty_protocol_private.codec;

import com.cloud.staff.netty_protocol_private.msg.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

/**
 *  增加index
 * +---------------------------------------------------------------------+
 * + CrcCode | length | sessionId  |  type  |  priority  |  attach.size  |……
 * +    4    |  4     |    8       |    1   |     1      |      4        |……
 * +---------------------------------------------------------------------+
 */
public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {

    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException {
        marshallingEncoder = new MarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf sendBuf) throws Exception {
        if(msg ==null || msg.getHeader() ==null){
            throw  new Exception("The encode message is null");
        }

//        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt(msg.getHeader().getCrcCode());
        sendBuf.writeInt(msg.getHeader().getLength());
        sendBuf.writeLong(msg.getHeader().getSessionId());
        sendBuf.writeByte(msg.getHeader().getType());
        sendBuf.writeByte(msg.getHeader().getPriority());

        //附件大小
        sendBuf.writeInt(msg.getHeader().getAttach().size());

        for(Iterator<Map.Entry<String,Object>> iterator = msg.getHeader().getAttach().entrySet().iterator();iterator.hasNext();){
            Map.Entry<String,Object> entry = iterator.next();
            String k = entry.getKey();Object v = entry.getValue();
            byte[] key = k.getBytes(StandardCharsets.UTF_8);
            sendBuf.writeInt(key.length);
            sendBuf.writeBytes(key);
            marshallingEncoder.encode(v,sendBuf);
        }

        if(msg.getBody() !=null){
            marshallingEncoder.encode(msg.getBody(),sendBuf);
        }else{
            sendBuf.writeInt(0);
        }
        //写入消息长度
        sendBuf.setInt(4,sendBuf.readableBytes());
    }
}
