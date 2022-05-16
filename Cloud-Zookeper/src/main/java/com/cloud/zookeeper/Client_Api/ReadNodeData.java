package com.cloud.zookeeper.Client_Api;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

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
 * @description：获取节点数据
 * @date 2022/5/16 13:39
 **/
public class ReadNodeData implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) {
        try {
            String path = "/zk-readnode";
            zk = new ZooKeeper("127.0.0.1:2181", 50000, new ReadNodeData());
            countDownLatch.await();

            zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println(new String(zk.getData(path,true,stat)));
            System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
            zk.setData(path,"123".getBytes(),-1);
            Thread.sleep(Integer.MAX_VALUE);

        } catch (IOException | InterruptedException | KeeperException exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (event.getType() == Event.EventType.None && event.getPath() == null) {
                countDownLatch.countDown();
            } else if (event.getType() == Event.EventType.NodeDataChanged) {
                try {
                    System.out.println(new String(zk.getData(event.getPath(),true,stat)));
                    System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
