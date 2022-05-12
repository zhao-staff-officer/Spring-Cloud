package com.cloud.zookeeper.ACID;

import com.alibaba.fastjson.JSON;
import com.cloud.zookeeper.ACID.dao.TestDao;
import com.cloud.zookeeper.ACID.entity.TestEntity;
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
            testEntity.setUserName("张三" + insertSizeStart);
            testEntity.setUserPwd("密码1" + insertSizeStart);
            testDao.insert(testEntity);
            log.info(Thread.currentThread().getName()+"【Insert】" + JSON.toJSONString(testEntity));
            log.info(Thread.currentThread().getName()+"【Query】" +JSON.toJSONString(testDao.queryAll()));
            log.info(Thread.currentThread().getName()+"【Size】" +testDao.queryAll().size());

            if((insertSizeStart&1) != 1){
                Thread.sleep(4000);
            }else {
                Thread.sleep(2000);
            }
            insertSizeStart++;
        } while (insertSizeStart <= insertSizeEnd);
    }

    /**
     * 事务B
     *  脏读
     */
    @GetMapping("/readUncommitted/transactionalB")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void transactionalB() throws InterruptedException {
        int insertSizeStart = 1, insertSizeEnd = 10;
        do {
            TestEntity testEntity = new TestEntity();
            testEntity.setUserName("张四" + insertSizeStart);
            testEntity.setUserPwd("密码2" + insertSizeStart);
            testDao.insert(testEntity);
            log.info(Thread.currentThread().getName()+"【Insert】" + JSON.toJSONString(testEntity));
            log.info(Thread.currentThread().getName()+"【Query】" +JSON.toJSONString(testDao.queryAll()));
            log.info(Thread.currentThread().getName()+"【Size】" +testDao.queryAll().size());

            if((insertSizeStart&1) != 1){
                Thread.sleep(4000);
            }else {
                Thread.sleep(2000);
            }
            insertSizeStart++;
        } while (insertSizeStart <= insertSizeEnd);
    }
}

/**
 * 2022-04-25 14:58:08.576  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Insert】{"userName":"张三1","userPwd":"密码11"}
 * 2022-04-25 14:58:08.608  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"}]
 * 2022-04-25 14:58:08.609  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Size】1
 * 2022-04-25 14:58:10.618  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Insert】{"userName":"张三2","userPwd":"密码12"}
 * 2022-04-25 14:58:10.619  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"}]
 * 2022-04-25 14:58:10.620  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Size】2
 * 2022-04-25 14:58:10.993  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Insert】{"userName":"张四1","userPwd":"密码21"}
 * 2022-04-25 14:58:10.995  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"}] >>>>> 读未提交：脏读
 * 2022-04-25 14:58:10.996  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Size】3
 * 2022-04-25 14:58:12.635  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Insert】{"userName":"张三3","userPwd":"密码13"}
 * 2022-04-25 14:58:12.637  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"}] >>>> 读未提交：不可重复读
 * 2022-04-25 14:58:12.638  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Size】4  >>>>> 不可重复读:幻读
 * 2022-04-25 14:58:13.010  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Insert】{"userName":"张四2","userPwd":"密码22"}
 * 2022-04-25 14:58:13.011  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"}]
 * 2022-04-25 14:58:13.012  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Size】5
 * 2022-04-25 14:58:14.644  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Insert】{"userName":"张三4","userPwd":"密码14"}
 * 2022-04-25 14:58:14.646  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"}]
 * 2022-04-25 14:58:14.648  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Size】6
 * 2022-04-25 14:58:15.023  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Insert】{"userName":"张四3","userPwd":"密码23"}
 * 2022-04-25 14:58:15.024  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"}]
 * 2022-04-25 14:58:15.026  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Size】7
 * 2022-04-25 14:58:16.653  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Insert】{"userName":"张三5","userPwd":"密码15"}
 * 2022-04-25 14:58:16.655  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"}]
 * 2022-04-25 14:58:16.657  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Size】8
 * 2022-04-25 14:58:17.031  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Insert】{"userName":"张四4","userPwd":"密码24"}
 * 2022-04-25 14:58:17.033  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"}]
 * 2022-04-25 14:58:17.035  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Size】9
 * 2022-04-25 14:58:18.673  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Insert】{"userName":"张三6","userPwd":"密码16"}
 * 2022-04-25 14:58:18.675  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"},{"id":180,"userName":"张三6","userPwd":"密码16"}]
 * 2022-04-25 14:58:18.677  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Size】10
 * 2022-04-25 14:58:19.046  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Insert】{"userName":"张四5","userPwd":"密码25"}
 * 2022-04-25 14:58:19.048  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"},{"id":180,"userName":"张三6","userPwd":"密码16"},{"id":181,"userName":"张四5","userPwd":"密码25"}]
 * 2022-04-25 14:58:19.049  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Size】11
 * 2022-04-25 14:58:20.678  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Insert】{"userName":"张三7","userPwd":"密码17"}
 * 2022-04-25 14:58:20.680  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"},{"id":180,"userName":"张三6","userPwd":"密码16"},{"id":181,"userName":"张四5","userPwd":"密码25"},{"id":182,"userName":"张三7","userPwd":"密码17"}]
 * 2022-04-25 14:58:20.681  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Size】12
 * 2022-04-25 14:58:21.054  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Insert】{"userName":"张四6","userPwd":"密码26"}
 * 2022-04-25 14:58:21.056  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"},{"id":180,"userName":"张三6","userPwd":"密码16"},{"id":181,"userName":"张四5","userPwd":"密码25"},{"id":182,"userName":"张三7","userPwd":"密码17"},{"id":183,"userName":"张四6","userPwd":"密码26"}]
 * 2022-04-25 14:58:21.057  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Size】13
 * 2022-04-25 14:58:22.685  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Insert】{"userName":"张三8","userPwd":"密码18"}
 * 2022-04-25 14:58:22.687  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"},{"id":180,"userName":"张三6","userPwd":"密码16"},{"id":181,"userName":"张四5","userPwd":"密码25"},{"id":182,"userName":"张三7","userPwd":"密码17"},{"id":183,"userName":"张四6","userPwd":"密码26"},{"id":184,"userName":"张三8","userPwd":"密码18"}]
 * 2022-04-25 14:58:22.688  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Size】14
 * 2022-04-25 14:58:23.073  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Insert】{"userName":"张四7","userPwd":"密码27"}
 * 2022-04-25 14:58:23.074  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"},{"id":180,"userName":"张三6","userPwd":"密码16"},{"id":181,"userName":"张四5","userPwd":"密码25"},{"id":182,"userName":"张三7","userPwd":"密码17"},{"id":183,"userName":"张四6","userPwd":"密码26"},{"id":184,"userName":"张三8","userPwd":"密码18"},{"id":185,"userName":"张四7","userPwd":"密码27"}]
 * 2022-04-25 14:58:23.076  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Size】15
 * 2022-04-25 14:58:24.694  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Insert】{"userName":"张三9","userPwd":"密码19"}
 * 2022-04-25 14:58:24.696  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"},{"id":180,"userName":"张三6","userPwd":"密码16"},{"id":181,"userName":"张四5","userPwd":"密码25"},{"id":182,"userName":"张三7","userPwd":"密码17"},{"id":183,"userName":"张四6","userPwd":"密码26"},{"id":184,"userName":"张三8","userPwd":"密码18"},{"id":185,"userName":"张四7","userPwd":"密码27"},{"id":186,"userName":"张三9","userPwd":"密码19"}]
 * 2022-04-25 14:58:24.697  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Size】16
 * 2022-04-25 14:58:25.081  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Insert】{"userName":"张四8","userPwd":"密码28"}
 * 2022-04-25 14:58:25.083  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"},{"id":180,"userName":"张三6","userPwd":"密码16"},{"id":181,"userName":"张四5","userPwd":"密码25"},{"id":182,"userName":"张三7","userPwd":"密码17"},{"id":183,"userName":"张四6","userPwd":"密码26"},{"id":184,"userName":"张三8","userPwd":"密码18"},{"id":185,"userName":"张四7","userPwd":"密码27"},{"id":186,"userName":"张三9","userPwd":"密码19"},{"id":187,"userName":"张四8","userPwd":"密码28"}]
 * 2022-04-25 14:58:25.084  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Size】17
 * 2022-04-25 14:58:26.706  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Insert】{"userName":"张三10","userPwd":"密码110"}
 * 2022-04-25 14:58:26.708  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"},{"id":180,"userName":"张三6","userPwd":"密码16"},{"id":181,"userName":"张四5","userPwd":"密码25"},{"id":182,"userName":"张三7","userPwd":"密码17"},{"id":183,"userName":"张四6","userPwd":"密码26"},{"id":184,"userName":"张三8","userPwd":"密码18"},{"id":185,"userName":"张四7","userPwd":"密码27"},{"id":186,"userName":"张三9","userPwd":"密码19"},{"id":187,"userName":"张四8","userPwd":"密码28"},{"id":188,"userName":"张三10","userPwd":"密码110"}]
 * 2022-04-25 14:58:26.710  INFO 21860 --- [nio-9020-exec-2] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-2【Size】18
 * 2022-04-25 14:58:27.092  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Insert】{"userName":"张四9","userPwd":"密码29"}
 * 2022-04-25 14:58:27.094  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"},{"id":180,"userName":"张三6","userPwd":"密码16"},{"id":181,"userName":"张四5","userPwd":"密码25"},{"id":182,"userName":"张三7","userPwd":"密码17"},{"id":183,"userName":"张四6","userPwd":"密码26"},{"id":184,"userName":"张三8","userPwd":"密码18"},{"id":185,"userName":"张四7","userPwd":"密码27"},{"id":186,"userName":"张三9","userPwd":"密码19"},{"id":187,"userName":"张四8","userPwd":"密码28"},{"id":188,"userName":"张三10","userPwd":"密码110"},{"id":189,"userName":"张四9","userPwd":"密码29"}]
 * 2022-04-25 14:58:27.095  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Size】19
 * 2022-04-25 14:58:29.110  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Insert】{"userName":"张四10","userPwd":"密码210"}
 * 2022-04-25 14:58:29.112  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Query】[{"id":171,"userName":"张三1","userPwd":"密码11"},{"id":172,"userName":"张三2","userPwd":"密码12"},{"id":173,"userName":"张四1","userPwd":"密码21"},{"id":174,"userName":"张三3","userPwd":"密码13"},{"id":175,"userName":"张四2","userPwd":"密码22"},{"id":176,"userName":"张三4","userPwd":"密码14"},{"id":177,"userName":"张四3","userPwd":"密码23"},{"id":178,"userName":"张三5","userPwd":"密码15"},{"id":179,"userName":"张四4","userPwd":"密码24"},{"id":180,"userName":"张三6","userPwd":"密码16"},{"id":181,"userName":"张四5","userPwd":"密码25"},{"id":182,"userName":"张三7","userPwd":"密码17"},{"id":183,"userName":"张四6","userPwd":"密码26"},{"id":184,"userName":"张三8","userPwd":"密码18"},{"id":185,"userName":"张四7","userPwd":"密码27"},{"id":186,"userName":"张三9","userPwd":"密码19"},{"id":187,"userName":"张四8","userPwd":"密码28"},{"id":188,"userName":"张三10","userPwd":"密码110"},{"id":189,"userName":"张四9","userPwd":"密码29"},{"id":190,"userName":"张四10","userPwd":"密码210"}]
 * 2022-04-25 14:58:29.114  INFO 21860 --- [nio-9020-exec-1] c.cloud.zookeeper.ACID.ReadUncommitted   : http-nio-9020-exec-1【Size】20
 */
