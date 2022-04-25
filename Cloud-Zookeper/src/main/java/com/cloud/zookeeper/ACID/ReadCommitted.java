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
 * @description：读已提交
 * @date 2022/4/25 10:53
 **/
@RestController
public class ReadCommitted {

    private static final Logger log = LoggerFactory.getLogger(ReadCommitted.class);

    @Autowired
    private TestDao testDao;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    /**
     * 事务A
     */
    @GetMapping("/readCommitted/transactionalA")
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void transactionalA() throws InterruptedException {
        int insertSizeStart = 1, insertSizeEnd = 10;
        do {
            TestEntity testEntity = new TestEntity();
            testEntity.setUserName("张三" + insertSizeStart);
            testEntity.setUserPwd("密码" + insertSizeStart);
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
     */
    @GetMapping("/readCommitted/transactionalB")
    @Transactional(isolation = Isolation.READ_COMMITTED)
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
 * 2022-04-25 15:09:06.035  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Insert】{"userName":"张三1","userPwd":"密码1"}
 * 2022-04-25 15:09:06.036  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"}]
 * 2022-04-25 15:09:06.038  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Size】1
 * 2022-04-25 15:09:08.044  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Insert】{"userName":"张三2","userPwd":"密码2"}
 * 2022-04-25 15:09:08.045  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"},{"id":212,"userName":"张三2","userPwd":"密码2"}]
 * 2022-04-25 15:09:08.046  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Size】2
 * 2022-04-25 15:09:10.048  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Insert】{"userName":"张三3","userPwd":"密码3"}
 * 2022-04-25 15:09:10.049  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"},{"id":212,"userName":"张三2","userPwd":"密码2"},{"id":213,"userName":"张三3","userPwd":"密码3"}]
 * 2022-04-25 15:09:10.049  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Size】3
 * 2022-04-25 15:09:11.340  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Insert】{"userName":"张四1","userPwd":"密码21"}
 * 2022-04-25 15:09:11.341  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Query】[{"id":214,"userName":"张四1","userPwd":"密码21"}]
 * 2022-04-25 15:09:11.342  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Size】1
 * 2022-04-25 15:09:12.060  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Insert】{"userName":"张三4","userPwd":"密码4"}
 * 2022-04-25 15:09:12.061  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"},{"id":212,"userName":"张三2","userPwd":"密码2"},{"id":213,"userName":"张三3","userPwd":"密码3"},{"id":215,"userName":"张三4","userPwd":"密码4"}]
 * 2022-04-25 15:09:12.062  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Size】4
 * 2022-04-25 15:09:13.348  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Insert】{"userName":"张四2","userPwd":"密码22"}
 * 2022-04-25 15:09:13.349  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Query】[{"id":214,"userName":"张四1","userPwd":"密码21"},{"id":216,"userName":"张四2","userPwd":"密码22"}]
 * 2022-04-25 15:09:13.350  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Size】2
 * 2022-04-25 15:09:14.071  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Insert】{"userName":"张三5","userPwd":"密码5"}
 * 2022-04-25 15:09:14.072  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"},{"id":212,"userName":"张三2","userPwd":"密码2"},{"id":213,"userName":"张三3","userPwd":"密码3"},{"id":215,"userName":"张三4","userPwd":"密码4"},{"id":217,"userName":"张三5","userPwd":"密码5"}]
 * 2022-04-25 15:09:14.073  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Size】5
 * 2022-04-25 15:09:15.363  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Insert】{"userName":"张四3","userPwd":"密码23"}
 * 2022-04-25 15:09:15.364  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Query】[{"id":214,"userName":"张四1","userPwd":"密码21"},{"id":216,"userName":"张四2","userPwd":"密码22"},{"id":218,"userName":"张四3","userPwd":"密码23"}]
 * 2022-04-25 15:09:15.365  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Size】3
 * 2022-04-25 15:09:16.088  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Insert】{"userName":"张三6","userPwd":"密码6"}
 * 2022-04-25 15:09:16.089  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"},{"id":212,"userName":"张三2","userPwd":"密码2"},{"id":213,"userName":"张三3","userPwd":"密码3"},{"id":215,"userName":"张三4","userPwd":"密码4"},{"id":217,"userName":"张三5","userPwd":"密码5"},{"id":219,"userName":"张三6","userPwd":"密码6"}]
 * 2022-04-25 15:09:16.090  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Size】6
 * 2022-04-25 15:09:17.366  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Insert】{"userName":"张四4","userPwd":"密码24"}
 * 2022-04-25 15:09:17.367  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Query】[{"id":214,"userName":"张四1","userPwd":"密码21"},{"id":216,"userName":"张四2","userPwd":"密码22"},{"id":218,"userName":"张四3","userPwd":"密码23"},{"id":220,"userName":"张四4","userPwd":"密码24"}]
 * 2022-04-25 15:09:17.368  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Size】4
 * 2022-04-25 15:09:18.094  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Insert】{"userName":"张三7","userPwd":"密码7"}
 * 2022-04-25 15:09:18.095  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"},{"id":212,"userName":"张三2","userPwd":"密码2"},{"id":213,"userName":"张三3","userPwd":"密码3"},{"id":215,"userName":"张三4","userPwd":"密码4"},{"id":217,"userName":"张三5","userPwd":"密码5"},{"id":219,"userName":"张三6","userPwd":"密码6"},{"id":221,"userName":"张三7","userPwd":"密码7"}]
 * 2022-04-25 15:09:18.096  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Size】7
 * 2022-04-25 15:09:19.376  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Insert】{"userName":"张四5","userPwd":"密码25"}
 * 2022-04-25 15:09:19.377  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Query】[{"id":214,"userName":"张四1","userPwd":"密码21"},{"id":216,"userName":"张四2","userPwd":"密码22"},{"id":218,"userName":"张四3","userPwd":"密码23"},{"id":220,"userName":"张四4","userPwd":"密码24"},{"id":222,"userName":"张四5","userPwd":"密码25"}]
 * 2022-04-25 15:09:19.378  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Size】5
 * 2022-04-25 15:09:20.100  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Insert】{"userName":"张三8","userPwd":"密码8"}
 * 2022-04-25 15:09:20.101  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"},{"id":212,"userName":"张三2","userPwd":"密码2"},{"id":213,"userName":"张三3","userPwd":"密码3"},{"id":215,"userName":"张三4","userPwd":"密码4"},{"id":217,"userName":"张三5","userPwd":"密码5"},{"id":219,"userName":"张三6","userPwd":"密码6"},{"id":221,"userName":"张三7","userPwd":"密码7"},{"id":223,"userName":"张三8","userPwd":"密码8"}]
 * 2022-04-25 15:09:20.102  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Size】8
 * 2022-04-25 15:09:21.390  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Insert】{"userName":"张四6","userPwd":"密码26"}
 * 2022-04-25 15:09:21.391  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Query】[{"id":214,"userName":"张四1","userPwd":"密码21"},{"id":216,"userName":"张四2","userPwd":"密码22"},{"id":218,"userName":"张四3","userPwd":"密码23"},{"id":220,"userName":"张四4","userPwd":"密码24"},{"id":222,"userName":"张四5","userPwd":"密码25"},{"id":224,"userName":"张四6","userPwd":"密码26"}]
 * 2022-04-25 15:09:21.393  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Size】6
 * 2022-04-25 15:09:22.108  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Insert】{"userName":"张三9","userPwd":"密码9"}
 * 2022-04-25 15:09:22.109  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"},{"id":212,"userName":"张三2","userPwd":"密码2"},{"id":213,"userName":"张三3","userPwd":"密码3"},{"id":215,"userName":"张三4","userPwd":"密码4"},{"id":217,"userName":"张三5","userPwd":"密码5"},{"id":219,"userName":"张三6","userPwd":"密码6"},{"id":221,"userName":"张三7","userPwd":"密码7"},{"id":223,"userName":"张三8","userPwd":"密码8"},{"id":225,"userName":"张三9","userPwd":"密码9"}]
 * 2022-04-25 15:09:22.110  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Size】9
 * 2022-04-25 15:09:23.395  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Insert】{"userName":"张四7","userPwd":"密码27"}
 * 2022-04-25 15:09:23.396  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Query】[{"id":214,"userName":"张四1","userPwd":"密码21"},{"id":216,"userName":"张四2","userPwd":"密码22"},{"id":218,"userName":"张四3","userPwd":"密码23"},{"id":220,"userName":"张四4","userPwd":"密码24"},{"id":222,"userName":"张四5","userPwd":"密码25"},{"id":224,"userName":"张四6","userPwd":"密码26"},{"id":226,"userName":"张四7","userPwd":"密码27"}]
 * 2022-04-25 15:09:23.397  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Size】7
 * 2022-04-25 15:09:24.123  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Insert】{"userName":"张三10","userPwd":"密码10"}
 * 2022-04-25 15:09:24.125  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"},{"id":212,"userName":"张三2","userPwd":"密码2"},{"id":213,"userName":"张三3","userPwd":"密码3"},{"id":215,"userName":"张三4","userPwd":"密码4"},{"id":217,"userName":"张三5","userPwd":"密码5"},{"id":219,"userName":"张三6","userPwd":"密码6"},{"id":221,"userName":"张三7","userPwd":"密码7"},{"id":223,"userName":"张三8","userPwd":"密码8"},{"id":225,"userName":"张三9","userPwd":"密码9"},{"id":227,"userName":"张三10","userPwd":"密码10"}]
 * 2022-04-25 15:09:24.126  INFO 21860 --- [nio-9020-exec-9] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-9【Size】10
 * 2022-04-25 15:09:25.410  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Insert】{"userName":"张四8","userPwd":"密码28"}
 * 2022-04-25 15:09:25.411  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Query】[{"id":214,"userName":"张四1","userPwd":"密码21"},{"id":216,"userName":"张四2","userPwd":"密码22"},{"id":218,"userName":"张四3","userPwd":"密码23"},{"id":220,"userName":"张四4","userPwd":"密码24"},{"id":222,"userName":"张四5","userPwd":"密码25"},{"id":224,"userName":"张四6","userPwd":"密码26"},{"id":226,"userName":"张四7","userPwd":"密码27"},{"id":228,"userName":"张四8","userPwd":"密码28"}]
 * 2022-04-25 15:09:25.412  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Size】8
 * 2022-04-25 15:09:27.414  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Insert】{"userName":"张四9","userPwd":"密码29"}
 * 2022-04-25 15:09:27.416  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"},{"id":212,"userName":"张三2","userPwd":"密码2"},{"id":213,"userName":"张三3","userPwd":"密码3"},{"id":214,"userName":"张四1","userPwd":"密码21"},{"id":215,"userName":"张三4","userPwd":"密码4"},{"id":216,"userName":"张四2","userPwd":"密码22"},{"id":217,"userName":"张三5","userPwd":"密码5"},{"id":218,"userName":"张四3","userPwd":"密码23"},{"id":219,"userName":"张三6","userPwd":"密码6"},{"id":220,"userName":"张四4","userPwd":"密码24"},{"id":221,"userName":"张三7","userPwd":"密码7"},{"id":222,"userName":"张四5","userPwd":"密码25"},{"id":223,"userName":"张三8","userPwd":"密码8"},{"id":224,"userName":"张四6","userPwd":"密码26"},{"id":225,"userName":"张三9","userPwd":"密码9"},{"id":226,"userName":"张四7","userPwd":"密码27"},{"id":227,"userName":"张三10","userPwd":"密码10"},{"id":228,"userName":"张四8","userPwd":"密码28"},{"id":229,"userName":"张四9","userPwd":"密码29"}] >>>> 读已提交：不可重复读。读取到事务A提交数据
 * 2022-04-25 15:09:27.417  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Size】19 >>>>>> 读已提交:幻读
 * 2022-04-25 15:09:29.429  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Insert】{"userName":"张四10","userPwd":"密码210"}
 * 2022-04-25 15:09:29.431  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Query】[{"id":211,"userName":"张三1","userPwd":"密码1"},{"id":212,"userName":"张三2","userPwd":"密码2"},{"id":213,"userName":"张三3","userPwd":"密码3"},{"id":214,"userName":"张四1","userPwd":"密码21"},{"id":215,"userName":"张三4","userPwd":"密码4"},{"id":216,"userName":"张四2","userPwd":"密码22"},{"id":217,"userName":"张三5","userPwd":"密码5"},{"id":218,"userName":"张四3","userPwd":"密码23"},{"id":219,"userName":"张三6","userPwd":"密码6"},{"id":220,"userName":"张四4","userPwd":"密码24"},{"id":221,"userName":"张三7","userPwd":"密码7"},{"id":222,"userName":"张四5","userPwd":"密码25"},{"id":223,"userName":"张三8","userPwd":"密码8"},{"id":224,"userName":"张四6","userPwd":"密码26"},{"id":225,"userName":"张三9","userPwd":"密码9"},{"id":226,"userName":"张四7","userPwd":"密码27"},{"id":227,"userName":"张三10","userPwd":"密码10"},{"id":228,"userName":"张四8","userPwd":"密码28"},{"id":229,"userName":"张四9","userPwd":"密码29"},{"id":230,"userName":"张四10","userPwd":"密码210"}]
 * 2022-04-25 15:09:29.432  INFO 21860 --- [io-9020-exec-10] com.cloud.zookeeper.ACID.ReadCommitted   : http-nio-9020-exec-10【Size】20
 */
