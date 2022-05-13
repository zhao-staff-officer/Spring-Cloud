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
 * @description：异步创建节点
 * @date 2022/5/13 13:28
 **/
public class CreateNodeAsync implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",50000,new CreateNodeAsync());
            countDownLatch.await();
            zooKeeper.create("/zk-test-node-1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,new IStringCallback(),"i am context1");
            zooKeeper.create("/zk-test-node-1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,new IStringCallback(),"i am context2");
            zooKeeper.create("/zk-test-node-1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,new IStringCallback(),"i am context3");
            Thread.sleep(10000);
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getState()== Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }

    static class IStringCallback implements AsyncCallback.StringCallback{
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("Create path result:["+rc+","+path+","+ctx+",real path name:"+name);
        }
    }
}
