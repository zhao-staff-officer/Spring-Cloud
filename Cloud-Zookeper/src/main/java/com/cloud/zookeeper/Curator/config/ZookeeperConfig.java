package com.cloud.zookeeper.Curator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
 * @description：Zookeeper配置信息
 * @date 2022/5/16 16:32
 **/
@Data
@Component
@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperConfig {
    /**
     * 链接地址
     */
    private String connectString;

    /**
     * 链接超时时间
     */
    private Integer connectionTimeoutMs;

    /**
     * 会话超时时间
     */
    private Integer sessionTimeoutMs;

    /**
     * 重试间隔时间
     */
    private Integer baseSleepTimeMs;

    /**
     * 最大重试次数
     */
    private Integer maxRetries;

    /**
     * 工作空间
     */
    private String nameSpace;
}
