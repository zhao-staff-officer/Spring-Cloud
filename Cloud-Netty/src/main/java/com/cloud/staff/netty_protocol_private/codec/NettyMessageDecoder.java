package com.cloud.staff.netty_protocol_private.codec;

import com.cloud.staff.netty_protocol_private.msg.NettyMessage;
import com.cloud.staff.netty_protocol_private.msg.NettyMessageHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder{

    MarshallingDecoder marshallingDecoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,int lengthAdjustment, int initialBytesToStrip) throws IOException {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength,lengthAdjustment,initialBytesToStrip);
        marshallingDecoder = new MarshallingDecoder();
    }

    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx,in);
        if(frame == null){
            return null;
        }
        NettyMessage message = new NettyMessage();
        NettyMessageHeader header = new NettyMessageHeader();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionId(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());

        //读取附件大小
        int size = frame.readInt();
        if(size>0){
            Map<String,Object> attch = new HashMap<>(size);
            for(int i=0;i<size;i++){
                int keySize = frame.readInt();
                byte[] keyByte = new byte[keySize];
                frame.readBytes(keyByte);
                String key = new String(keyByte, StandardCharsets.UTF_8);
                attch.put(key,marshallingDecoder.decode(frame));
            }
            header.setAttach(attch);
        }
        if(frame.readableBytes()>4){
            message.setBody(marshallingDecoder.decode(frame));
        }
        message.setHeader(header);
        return message;
    }
}
