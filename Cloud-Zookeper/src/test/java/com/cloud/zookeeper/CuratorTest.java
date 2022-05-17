package com.cloud.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：测试类
 * @date 2022/5/17 9:21
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CuratorTest {

    @Autowired
    private CuratorFramework client;

    private static Stat stat = new Stat();

    /**
     * 新增节点
     *
     * @throws Exception
     */
    @Test
    public void createNode() throws Exception {
        client.create()
                .creatingParentContainersIfNeeded()
//                .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                .forPath("/zk-book/c3");
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 新增节点
     *
     * @throws Exception
     */
    @Test
    public void createNodeData() throws Exception {
        String path = client.create()
                .creatingParentContainersIfNeeded()
                .forPath("/zk-book", "测试".getBytes());
        client.getData().storingStatIn(stat).forPath(path);
    }

    @Test
    public void createNodeAsync() throws Exception {
        client.create().creatingParentContainersIfNeeded()
                .inBackground()
                .forPath("/zk-book", "测试".getBytes());
    }

    /**
     * 删除节点
     *
     * @throws Exception
     */
    @Test
    public void deleteNode() throws Exception {
        createNodeData();
        client.delete()
                .deletingChildrenIfNeeded()
                .forPath("/zk-book");
    }

    @Test
    public void getData() throws Exception {
        createNodeData();
        byte[] data = client.getData().storingStatIn(stat).forPath("/zk-book/c1");
        log.info(new String(data));
    }

    @Test
    public void updateData() throws Exception {
        client.setData().forPath("/zk-book/c1","大美女".getBytes());
    }




}
