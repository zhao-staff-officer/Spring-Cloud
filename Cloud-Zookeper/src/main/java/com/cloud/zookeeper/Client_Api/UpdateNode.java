package com.cloud.zookeeper.Client_Api;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Stack;
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
 * @description：更新数据
 * @date 2022/5/16 13:36
 **/
public class UpdateNode implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) {
        try {
            String path = "/zk-updateNode";
            zk = new ZooKeeper("127.0.0.1:2181",50000,new UpdateNode());
            countDownLatch.await();
            zk.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            zk.getData(path,true,null);
            Stat stat = zk.setData(path,"456".getBytes(),-1);
            System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
            Stat stat2 = zk.setData(path,"456".getBytes(),stat.getVersion());
            System.out.println(stat2.getCzxid()+","+stat2.getMzxid()+","+stat2.getVersion());
            zk.setData(path,"456".getBytes(),stat.getVersion());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException | InterruptedException | KeeperException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getState()== Event.KeeperState.SyncConnected){
            if(event.getType() == Event.EventType.None && event.getPath()==null){
                countDownLatch.countDown();
            }
        }
    }
}
