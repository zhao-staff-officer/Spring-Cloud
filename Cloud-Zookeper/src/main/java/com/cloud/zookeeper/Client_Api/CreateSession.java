package com.cloud.zookeeper.Client_Api;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：创建会话
 * @date 2022/5/12 17:58
 **/
public class CreateSession implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,new CreateSession());
            System.out.println(zooKeeper.getState());
            connectedSemaphore.await();
        } catch (IOException |InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event"+event);
        if(event.getState() == Event.KeeperState.SyncConnected){
            connectedSemaphore.countDown();
        }
    }
}
