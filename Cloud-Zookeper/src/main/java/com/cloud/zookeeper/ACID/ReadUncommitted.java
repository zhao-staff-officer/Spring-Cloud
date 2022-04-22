package com.cloud.zookeeper.ACID;

import com.alibaba.fastjson.JSON;
import com.cloud.zookeeper.dao.TestDao;
import com.cloud.zookeeper.entity.TestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 赵参谋
 * @version $
 * =========================================================================
 * 变更履历：
 * -------------------------------------------------------------------------
 * 变更编号     变更时间    变更人   变更原因    变更内容
 * -------------------------------------------------------------------------
 * <p>
 * @description：读未提交
 * @date 2022/4/20 21:36
 **/
@RestController
public class ReadUncommitted {
    private static final Logger log = LoggerFactory.getLogger(ReadUncommitted.class);

    @Autowired
    private TestDao testDao;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    /**
     * 事务A
     */
    @GetMapping("/readUncommitted/transactionalA")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED,propagation = Propagation.REQUIRES_NEW)
    public void transactionalA() throws InterruptedException {
        int insertSizeStart = 1, insertSizeEnd = 10;
        do {
            TestEntity testEntity = new TestEntity();
            testEntity.setId(insertSizeStart);
            testEntity.setUserName("张三" + insertSizeStart);
            testEntity.setUserPwd("密码" + insertSizeStart);
            testDao.insert(testEntity);
            log.info(Thread.currentThread().getName()+"【Insert】" + JSON.toJSONString(testEntity));
            Thread.sleep(2000);
            insertSizeStart++;
        } while (insertSizeStart <= insertSizeEnd);
    }

    /**
     * 事务B
     */
    @GetMapping("/readUncommitted/transactionalB")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void transactionalB() throws InterruptedException {
        int start = 1, end = 10;
        do {
            List<TestEntity> test = testDao.queryAll();
            log.info(Thread.currentThread().getName()+"【Query】:"+ JSON.toJSONString(test));
            Thread.sleep(2000);
            start++;
        } while (start <= end);

    }
}
