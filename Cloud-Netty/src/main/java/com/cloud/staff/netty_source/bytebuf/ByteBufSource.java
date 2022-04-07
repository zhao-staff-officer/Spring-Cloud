package com.cloud.staff.netty_source.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：bytebuf底层
 * @date 2022/4/2 16:39
 **/
public class ByteBufSource {

    public static void main(String[] args) {
        //默认堆缓存
        ByteBuf heapByteBuf = Unpooled.buffer();
        System.out.println(heapByteBuf.capacity());
        System.out.println(heapByteBuf.maxCapacity());
        heapByteBuf.writeInt(1);
        byte[] b = heapByteBuf.array();
        System.out.println(String.valueOf(b[3]));
        //非堆缓存
        ByteBuf directByteBuf = Unpooled.directBuffer();
        //
        PooledByteBufAllocator pooledByteBuf = PooledByteBufAllocator.DEFAULT;
    }
}
