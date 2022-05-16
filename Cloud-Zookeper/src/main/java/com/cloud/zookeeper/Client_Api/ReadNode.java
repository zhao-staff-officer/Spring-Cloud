package com.cloud.zookeeper.Client_Api;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
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
 * @description：读取节点
 * @date 2022/5/16 13:17
 **/
public class ReadNode implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zk =null;

    public static void main(String[] args) {
        try {
            String path = "/zk-aook";
            zk =new ZooKeeper("127.0.0.1:2181",50000,new ReadNode());
            countDownLatch.await();
            zk.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            zk.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);

            List<String> childrenList = zk.getChildren(path,true);
            System.out.println(childrenList);

            zk.create(path+"/c2","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);

            Thread.sleep(Integer.MAX_VALUE);

        } catch (IOException | InterruptedException | KeeperException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void process(WatchedEvent event) {
        if(Event.KeeperState.SyncConnected == event.getState()){
            if(event.getType() == Event.EventType.None && event.getPath()==null){
                countDownLatch.countDown();
            }else if(event.getType() == Event.EventType.NodeChildrenChanged){
                try {
                    System.out.println("ReGet child :"+zk.getChildren(event.getPath(),true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
