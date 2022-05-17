package com.cloud.zookeeper.Curator.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：Curator配置
 * @date 2022/5/16 16:27
 **/
@Slf4j
@Configuration
public class CuratorConifg {

    @Autowired
    private ZookeeperConfig zookeeperConfig;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Bean
    public CuratorFramework curatorFramework() throws InterruptedException {
        //初始化
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zookeeperConfig.getConnectString())
                .sessionTimeoutMs(zookeeperConfig.getSessionTimeoutMs())
                .retryPolicy(new ExponentialBackoffRetry(zookeeperConfig.getBaseSleepTimeMs(),zookeeperConfig.getMaxRetries()))
                .connectionTimeoutMs(zookeeperConfig.getConnectionTimeoutMs())
                .namespace(zookeeperConfig.getNameSpace())
                .build();
        curatorFramework.start();
        curatorFramework.blockUntilConnected();
        createCacheListener(curatorFramework);
        return curatorFramework;
    }

    private void createCacheListener(CuratorFramework curatorFramework){
        //构建缓存
        CuratorCache cache = CuratorCache.build(curatorFramework,"/"+curatorFramework.getNamespace(),CuratorCache.Options.SINGLE_NODE_CACHE);

        //构建监听
        CuratorCacheListener listener = CuratorCacheListener.builder()
                //初始化完成调用
                .forInitialized(()->log.info("curatorCache启动成功"))
                //添加数据调用
                .forCreates(childData -> log.info("添加数据：Path:{},Data:{}",childData.getPath(),new String(childData.getData())))
                //更新数据调用
                .forChanges(((oldNode, node) -> log.info("更新数据：Path:{},Data:{}->{}",oldNode.getPath(),
                        new String(oldNode.getData()),new String(node.getData()))))
                //删除数据
                .forDeletes(childData -> log.info("删除数据：Path:{},Data:{}",childData.getPath(),new String(childData.getData())))
                .build();
        cache.listenable().addListener(listener);
        cache.start();
    }


}
