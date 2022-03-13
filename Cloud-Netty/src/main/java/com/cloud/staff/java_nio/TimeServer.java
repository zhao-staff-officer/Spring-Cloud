package com.cloud.staff.java_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);
        new Thread(multiplexerTimeServer,"NIO-Server-001").start();
    }
}

class MultiplexerTimeServer implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile boolean stop;

    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("the time server is start in port:" + port);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            try{
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                SelectionKey key = null;
                for(Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext();){
                    key = it.next();it.remove();
                    //处理
                    try{
                        handleInput(key);
                    }catch (Exception exception){
                        if (key !=null){
                            key.cancel();
                            if(key.channel() !=null) key.channel().close();
                        }
                    }
                }
            }catch (Exception exception){

            }
        }

        if(selector !=null){
            try{
                selector.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    /**
     * 处理数据
     * @param key
     */
    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            //处理新接入的请求消息
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc =ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector,SelectionKey.OP_ACCEPT);
            }

            if(key.isReadable()){

            }
        }
    }
}
