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
 * @description：检查节点是否存在
 * @date 2022/5/16 14:40
 **/
public class CheckNode implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) {
        try {
            String path = "/zk-checkNode";
            zooKeeper = new ZooKeeper("127.0.0.1:2181",50000,new CheckNode());
            countDownLatch.await();
            zooKeeper.exists(path,true);
            zooKeeper.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            zooKeeper.setData(path,"123".getBytes(),-1);
            zooKeeper.create(path+"/c1","123".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            zooKeeper.delete(path+"/c1",-1);
            zooKeeper.delete(path,-1);
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException | InterruptedException | KeeperException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        try{
            if(event.getState()== Event.KeeperState.SyncConnected){
                if(event.getType() == Event.EventType.None && event.getPath()==null){
                    countDownLatch.countDown();
                }else if(event.getType()== Event.EventType.NodeCreated){
                    System.out.println("Node("+event.getPath()+")Created");
                    zooKeeper.exists(event.getPath(),true);
                }else if(event.getType()==Event.EventType.NodeDeleted){
                    System.out.println("Node("+event.getPath()+")Deleted");
                    zooKeeper.exists(event.getPath(),true);
                }else if(event.getType()==Event.EventType.NodeDataChanged){
                    System.out.println("Node("+event.getPath()+")DataChanged");
                    zooKeeper.exists(event.getPath(),true);
                }
            }
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }

    }
}
