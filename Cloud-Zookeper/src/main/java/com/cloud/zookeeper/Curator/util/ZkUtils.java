package com.cloud.zookeeper.Curator.util;

import lombok.SneakyThrows;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：Zookeeper工具包
 * @date 2022/5/16 16:48
 **/
@Component
public class ZkUtils {

    @Autowired
    private CuratorFramework curatorFramework;

    /**
     * 创建节点
     * @param path 节点路径
     */
    @SneakyThrows
    public void createNode(String path){
        curatorFramework.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path);
    }

    /**
     * 创建节点
     * @param path 节点路径
     * @param data 节点数据
     */
    @SneakyThrows
    public void createNode(String path,byte[] data){
        curatorFramework.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path,data);
    }


}
