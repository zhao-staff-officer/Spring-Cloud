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
 * @description：创建复用会话
 * @date 2022/5/13 10:17
 **/
public class CreateSessionSessionPwd implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,new CreateSessionSessionPwd());
            countDownLatch.await();
            long sessionId = zooKeeper.getSessionId();
            byte[] pwd = zooKeeper.getSessionPasswd();

            //复用会话1
            ZooKeeper zooKeeper1 = new ZooKeeper("127.0.0.1:2181",5000,new CreateSessionSessionPwd(),1l,"test".getBytes());
            //复用会话2
            ZooKeeper zooKeeper2 = new ZooKeeper("127.0.0.1:2181",5000,new CreateSessionSessionPwd(), sessionId,pwd);
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event"+event);
        if(event.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }
}
