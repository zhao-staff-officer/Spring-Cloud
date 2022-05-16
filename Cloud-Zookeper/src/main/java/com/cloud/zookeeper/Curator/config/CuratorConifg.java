package com.cloud.zookeeper.Curator.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public CuratorFramework curatorFramework(){
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zookeeperConfig.getConnectString())
                .sessionTimeoutMs(zookeeperConfig.getSessionTimeoutMs())
                .retryPolicy(new ExponentialBackoffRetry(zookeeperConfig.getBaseSleepTimeMs(),zookeeperConfig.getMaxRetries()))
                .connectionTimeoutMs(zookeeperConfig.getConnectionTimeoutMs())
                .build();
        log.info("初始化zookeeper完成");
        return curatorFramework;
    }
}
