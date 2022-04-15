# Netty源码解读





[TOC]



## 1.0  服务端启动项

```java
import com.cloud.staff.netty_protocol_private.codec.NettyMessageDecoder;
import com.cloud.staff.netty_protocol_private.codec.NettyMessageEncoder;
import com.cloud.staff.netty_protocol_private.work_server.HeartBeatRespHandler;
import com.cloud.staff.netty_protocol_private.work_server.LoginAuthRespHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyService {
	
    public static void main(String[] args) {
        //这里采用Reactor主从策略配置
		//创建acceptGroup池,用来处理channel链接关闭注册功能
        EventLoopGroup acceptGroup = new NioEventLoopGroup();
        //创建workGroup池，用来处理handler事务处理，编解码……
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建辅助启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(acceptGroup,workerGroup)
                    //指定channel类型
                    .channel(NioServerSocketChannel.class) //这里得到channelFactory<NioServerSocketChannel>
                    //非延迟发送
                    .option(ChannelOption.TCP_NODELAY,true)
                    //等待链接队列大小
                    .option(ChannelOption.SO_BACKLOG,1024)
                    //添加日志输出
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //添加channel-pinple
                    .childHandler(new ChannelInitializer<SocketChannel>() { 
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyMessageDecoder(1024*1024,4,4,-8,0));
                            ch.pipeline().addLast(new NettyMessageEncoder());
                            ch.pipeline().addLast(new LoginAuthRespHandler());
                            ch.pipeline().addLast(new HeartBeatRespHandler());
                        }
                    })
            ;
            ChannelFuture f  = bootstrap.bind(8080).sync();//bind()方法
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
```

### 1.1  bind()方法

```java
private ChannelFuture doBind(final SocketAddress localAddress) {
    	//初始化注册，将channnel注册到Evenloop上
        final ChannelFuture regFuture = initAndRegister();
        final Channel channel = regFuture.channel();
        if (regFuture.cause() != null) {
            return regFuture;
        }

        if (regFuture.isDone()) {
            // At this point we know that the registration was complete and successful.
            ChannelPromise promise = channel.newPromise();
            doBind0(regFuture, channel, localAddress, promise);
            return promise;
        } else {
            // Registration future is almost always fulfilled already, but just in case it's not.
            final PendingRegistrationPromise promise = new PendingRegistrationPromise(channel);
            regFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    Throwable cause = future.cause();
                    if (cause != null) {
                        // Registration on the EventLoop failed so fail the ChannelPromise directly to not cause an
                        // IllegalStateException once we try to access the EventLoop of the Channel.
                        promise.setFailure(cause);
                    } else {
                        // Registration was successful, so set the correct executor to use.
                        // See https://github.com/netty/netty/issues/2586
                        promise.registered();

                        doBind0(regFuture, channel, localAddress, promise);
                    }
                }
            });
            return promise;
        }
    }

```

#### 1.1.1 initAndRegister()方法

```java
final ChannelFuture initAndRegister() {
        Channel channel = null;
        try {
            //实例化channel对象，得到NioServerSocketChannel对象,这里面构造函数事情做多呀!
            channel = channelFactory.newChannel(); 
            //初始化channel配置参数
            init(channel);
        } catch (Throwable t) {
            if (channel != null) {
                // channel can be null if newChannel crashed (eg SocketException("too many open files"))
                channel.unsafe().closeForcibly();
                // as the Channel is not registered yet we need to force the usage of the GlobalEventExecutor
                return new DefaultChannelPromise(channel, GlobalEventExecutor.INSTANCE).setFailure(t);
            }
            // as the Channel is not registered yet we need to force the usage of the GlobalEventExecutor
            return new DefaultChannelPromise(new FailedChannel(), GlobalEventExecutor.INSTANCE).setFailure(t);
        }

        ChannelFuture regFuture = config().group().register(channel);
        if (regFuture.cause() != null) {
            if (channel.isRegistered()) {
                channel.close();
            } else {
                channel.unsafe().closeForcibly();
            }
        }

        // If we are here and the promise is not failed, it's one of the following cases:
        // 1) If we attempted registration from the event loop, the registration has been completed at this point.
        //    i.e. It's safe to attempt bind() or connect() now because the channel has been registered.
        // 2) If we attempted registration from the other thread, the registration request has been successfully
        //    added to the event loop's task queue for later execution.
        //    i.e. It's safe to attempt bind() or connect() now:
        //         because bind() or connect() will be executed *after* the scheduled registration task is executed
        //         because register(), bind(), and connect() are all bound to the same thread.

        return regFuture;
    }
```

##### 1.1.1.1 newChannel()

```java
public class NioServerSocketChannel extends AbstractNioMessageChannel implements io.netty.channel.socket.ServerSocketChannel {

    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
    //默认Selector循环器
    private static final SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(NioServerSocketChannel.class);
	
    private static ServerSocketChannel newSocket(SelectorProvider provider) {
        try {
            /**
             *  Use the {@link SelectorProvider} to open {@link SocketChannel} and so remove condition in
             *  {@link SelectorProvider#provider()} which is called by each ServerSocketChannel.open() otherwise.
             *
             *  See <a href="https://github.com/netty/netty/issues/2308">#2308</a>.
             */
            //打开服务端循环器
            return provider.openServerSocketChannel();
        } catch (IOException e) {
            throw new ChannelException(
                    "Failed to open a server socket.", e);
        }
    }

    private final ServerSocketChannelConfig config;

    /**
     * Create a new instance
     * 这里都是初始化数据，有点多
     * L 1.获取默认selector轮询器，返回NioServerSocketChannelImpl
     * L 2.初始化 NioServerSocket
     *   L 2.1 初始化 Id = newId();unsafe = newUnsafe(); Pipeline = newChannelPipeline();
     *	 L 2.2 设置 当前channelReadInterstOps 操作位 1>>4=16 接收链接
     *	 L 2.3 设置 selectableChannle阻塞
     * L 3.设置 NioServerSocketChannel.config配置参数
     */	
    public NioServerSocketChannel() {
        this(newSocket(DEFAULT_SELECTOR_PROVIDER));
    }
}
    
/**
 * 默认selector循环器
 * sun.nio.ch.DefaultSelectorProvider
 */
public abstract class SelectorProvider{
        public static SelectorProvider provider() {
        synchronized (lock) {
            if (provider != null)
                return provider;
            return AccessController.doPrivileged(
                new PrivilegedAction<SelectorProvider>() {
                    public SelectorProvider run() {
                            if (loadProviderFromProperty())
                                return provider;
                            if (loadProviderAsService())
                                return provider;
                            provider = sun.nio.ch.DefaultSelectorProvider.create();
                            return provider;
                        }
                    });
        }
    }
}
    

```



##### 1.1.1.2 channel.init()方法

```java
@Override
    void init(Channel channel) {
        //将Options参数设置到channnel里面
        setChannelOptions(channel, newOptionsArray(), logger);
        //将attribute参数设置到channel里面
        setAttributes(channel, attrs0().entrySet().toArray(EMPTY_ATTRIBUTE_ARRAY));

        ChannelPipeline p = channel.pipeline();

        final EventLoopGroup currentChildGroup = childGroup;
        final ChannelHandler currentChildHandler = childHandler;
        final Entry<ChannelOption<?>, Object>[] currentChildOptions;
        synchronized (childOptions) {
            currentChildOptions = childOptions.entrySet().toArray(EMPTY_OPTION_ARRAY);
        }
        final Entry<AttributeKey<?>, Object>[] currentChildAttrs = childAttrs.entrySet().toArray(EMPTY_ATTRIBUTE_ARRAY);

        p.addLast(new ChannelInitializer<Channel>() {
            @Override
            public void initChannel(final Channel ch) {
                final ChannelPipeline pipeline = ch.pipeline();
                ChannelHandler handler = config.handler();
                if (handler != null) {
                    pipeline.addLast(handler);
                }

                ch.eventLoop().execute(new Runnable() {
                    @Override
                    public void run() {
                        pipeline.addLast(new ServerBootstrapAcceptor(
                                ch, currentChildGroup, currentChildHandler, currentChildOptions, currentChildAttrs));
                    }
                });
            }
        });
    }
```

