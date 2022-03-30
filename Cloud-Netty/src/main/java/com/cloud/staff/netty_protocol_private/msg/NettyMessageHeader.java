package com.cloud.staff.netty_protocol_private.msg;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class NettyMessageHeader {

    /**
     * 消息验证码
     */
    private int crcCode = 0xadaf0105;

    /**
     * 消息长度
     */
    private int length;

    /**
     * 会话ID
     */
    private long sessionId;

    /**
     * 消息类型
     */
    private byte type;

    /**
     * 消息优先级
     */
    private byte priority;

    /**
     * 扩展字段
     */
    private Map<String,Object> attach = new HashMap<>();



}
