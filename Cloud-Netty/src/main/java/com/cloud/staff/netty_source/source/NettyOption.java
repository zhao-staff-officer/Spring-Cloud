package com.cloud.staff.netty_source.source;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.util.internal.PlatformDependent;

import java.net.SocketAddress;

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
 * @date 2022/4/14 16:45
 **/
public class NettyOption {

//    ChannelOption.SO_BACKLOG, 1024
//    BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。

//    ChannelOption.SO_KEEPALIVE, true
//    是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。

//    ChannelOption.TCP_NODELAY, true
//    在TCP/IP协议中，无论发送多少数据，总是要在数据前面加上协议头，同时，对方接收到数据，也需要发送ACK表示确认。为了尽可能的利用网络带宽，TCP总是希望尽可能的发送足够大的数据。
//    这里就涉及到一个名为Nagle的算法，该算法的目的就是为了尽可能发送大块数据，避免网络中充斥着许多小数据块。

//　　ChannelOption.SO_REUSEADDR
//   这个参数表示允许重复使用本地地址和端口，比如，某个服务器进程占用了TCP的80端口进行监听，此时再次监听该端口就会返回错误，使用该参数就可以解决问题，
//   该参数允许共用该端口，这个在服务器程序中比较常使用，比如某个进程非正常退出，该程序占用的端口可能要被占用一段时间才能允许其他进程使用，而且程序死掉以后，内核一需要一定的时间才能够释放此端口，
//   不设置SO_REUSEADDR就无法正常使用该端口。
//
//    ChannelOption.SO_KEEPALIVE
//    参数对应于套接字选项中的SO_KEEPALIVE，该参数用于设置TCP连接，当设置该选项以后，连接会测试链接的状态，这个选项用于可能长时间没有数据交流的连接。
//    当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文。
//
//    ChannelOption.SO_SNDBUF和ChannelOption.SO_RCVBUF
//    ChannelOption.SO_SNDBUF参数对应于套接字选项中的SO_SNDBUF，ChannelOption.SO_RCVBUF参数对应于套接字选项中的SO_RCVBUF
//    这两个参数用于操作接收缓冲区和发送缓冲区的大小，接收缓冲区用于保存网络协议站内收到的数据，直到应用程序读取成功，发送缓冲区用于保存发送数据，直到发送成功。
//
//    ChannelOption.SO_LINGER
//    ChannelOption.SO_LINGER参数对应于套接字选项中的SO_LINGER,Linux内核默认的处理方式是当用户调用close（）方法的时候，
//    函数返回，在可能的情况下，尽量发送数据，不一定保证会发生剩余的数据，造成了数据的不确定性，使用SO_LINGER可以阻塞close()的调用时间，直到数据完全发送
}
class SO_BACKLOG{
//    Step:1====>  NioServerSocketChannle.dobind()处理绑定
//    protected void doBind(SocketAddress localAddress) throws Exception {
//          版本大于7，获取config.getBackLog
//        if (PlatformDependent.javaVersion() >= 7) {
//            javaChannel().bind(localAddress, config.getBacklog());
//        } else {
//            javaChannel().socket().bind(localAddress, config.getBacklog());
//        }
//    }
//    Step:2===>win默认200,其余128
//    /**
//     * The SOMAXCONN value of the current machine.  If failed to get the value,  {@code 200}  is used as a
//     * default value for Windows or {@code 128} for others.
//     */
//    public static final int SOMAXCONN;


}


