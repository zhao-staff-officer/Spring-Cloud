package com.cloud.zookeeper.Client_Api;

import org.apache.zookeeper.*;

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
 * @description：同步创建节点
 * @date 2022/5/13 10:36
 **/
public class CreateNodeSync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",50000,new CreateNodeSync());
            System.out.println(zooKeeper.getState());
            connectedSemaphore.await();
            String path1 = zooKeeper.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("success create node:"+path1);
            String path2 = zooKeeper.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("success create node:"+path2);
        } catch (IOException | InterruptedException | KeeperException exception) {
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
