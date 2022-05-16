package com.cloud.zookeeper.Client_Api;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：权限控制
 * @date 2022/5/16 14:58
 **/
public class AclNode {

    public static void main(String[] args) {
        try {
            String path = "/zk-AclNode";
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",50000,null);
            zooKeeper.addAuthInfo("digest","foot:true".getBytes());
            zooKeeper.create(path,"init".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }
}
