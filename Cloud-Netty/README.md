# Netty
Netty 是一款异步的事件驱动的网络应用程序框架，支持快速地开发可维护的高性能的面向协议的服务器
和客户端


## linux网络I/O模型
  - 阻塞IO  
    在进程空间调用recvform系统调用，等待数据到达内核，等待数据从内核拷贝到用户空间。
    整个等待过程进程都是阻塞的
    ![image](./static/阻塞IO.png)
  - 非阻塞IO  
    执行非阻塞I/O系统调用时，如果内核中的数据还没有准备好，会直接返回，不会阻塞。
    通过进程不断查询，直到数据在内核中就绪，便开始拷贝到用户空间。
    拷贝的过程中，进程还是被阻塞了，所以非阻塞IO也是同步IO。
    ![image](./static/非阻塞IO.png)
  - 多路复用IO
    单个进程处理多个网络连接IO，使用select\poll\epoll三种系统调用，
    不断轮询所有的连接，如果有数据到达内核则通知进程，进行数据拷贝到用户内存  
    - select/epoll区别  
        1:select单进程打开FD有限制，默认1024.epoll无限制  
        2:select随着FD增加而线性下降。epoll是根据每个fd上面callback函数实现，
          只有活跃的socket才会触发。  
        3:epoll通过内核和用户空间mmap同一块存储避免不要的内存复制。  
        4:epoll-API更加简单
    ![image](./static/多路复用IO.png)
  - 信号驱动IO
    开启套接口信号驱动I/O功能，调用sigaction执行一个信号处理函数(此调用是非阻塞的)；
    当数据准备就绪时，就为该进程生成一个SIGIO信号，通过信号回调通知应用程序调用recvform来读取数据，并通知主循环函数处理数据。
    ![image](./static/信号驱动IO.png)
  - 异步IO
    发起read操作后进程立马返回，整个IO过程不会产生任何block。
    kernel会等等数据准备完成，然后将数据拷贝到用户内存。
    当这一切都完成后，kernel会给用户进程发送一个signal，告诉它read操作完成了。
    ![image](./static/异步IO.png)
    

    

    
    
