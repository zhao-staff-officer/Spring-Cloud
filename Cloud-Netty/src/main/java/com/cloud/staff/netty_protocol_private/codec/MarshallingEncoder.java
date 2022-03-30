package com.cloud.staff.netty_protocol_private.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Marshaller;

import java.io.IOException;

/**
 * 编码器
 */
public class MarshallingEncoder {

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    Marshaller marshaller;

    public MarshallingEncoder() throws IOException {
        marshaller = MarshallingCodeCFactory.buildMarshalling();
    }

    public void encode(Object msg, ByteBuf out) throws IOException {
        try {
            int lengthPos = out.writerIndex();
            out.writeBytes(LENGTH_PLACEHOLDER);
            ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);
            marshaller.start(output);
            marshaller.writeObject(msg);
            marshaller.finish();
            out.setInt(lengthPos, out.writerIndex() - lengthPos - 4);
        } finally {
            marshaller.close();
        }
    }
}

