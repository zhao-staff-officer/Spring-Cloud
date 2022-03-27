package com.cloud.staff.netty_protocol_private.msg;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NettyMessage {

    /**
     * 消息头
     */
    private NettyMessageHeader header;

    /**
     * 消息体
     */
    private Object body;
}
