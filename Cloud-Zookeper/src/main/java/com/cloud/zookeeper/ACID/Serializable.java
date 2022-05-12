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
 * @description：
 * @date 2022/4/25 15:48
 **/
@RestController
public class Serializable {

    private static final Logger log = LoggerFactory.getLogger(Serializable.class);

    @Autowired
    private TestDao testDao;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    /**
     * 事务A
     */
    @GetMapping("/serializable/transactionalA")
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRES_NEW)
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
    @GetMapping("/serializable/transactionalB")
    @Transactional(isolation = Isolation.REPEATABLE_READ)
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
 * 2022-04-25 15:50:53.655  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Insert】{"userName":"张三1","userPwd":"密码1"}
 * 2022-04-25 15:50:53.656  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Query】[{"id":331,"userName":"张三1","userPwd":"密码1"}]
 * 2022-04-25 15:50:53.657  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Size】1
 * 2022-04-25 15:50:55.671  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Insert】{"userName":"张三2","userPwd":"密码2"}
 * 2022-04-25 15:50:55.672  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Query】[{"id":331,"userName":"张三1","userPwd":"密码1"},{"id":332,"userName":"张三2","userPwd":"密码2"}]
 * 2022-04-25 15:50:55.673  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Size】2
 * 2022-04-25 15:50:56.630  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Insert】{"userName":"张四1","userPwd":"密码21"}
 * 2022-04-25 15:50:56.631  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Query】[{"id":333,"userName":"张四1","userPwd":"密码21"}]
 * 2022-04-25 15:50:56.632  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Size】1
 * 2022-04-25 15:50:58.642  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Insert】{"userName":"张四2","userPwd":"密码22"}
 * 2022-04-25 15:50:58.642  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Query】[{"id":333,"userName":"张四1","userPwd":"密码21"},{"id":334,"userName":"张四2","userPwd":"密码22"}]
 * 2022-04-25 15:50:58.643  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Size】2
 * 2022-04-25 15:50:59.684  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Insert】{"userName":"张三3","userPwd":"密码3"}
 * 2022-04-25 15:50:59.685  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Query】[{"id":331,"userName":"张三1","userPwd":"密码1"},{"id":332,"userName":"张三2","userPwd":"密码2"},{"id":335,"userName":"张三3","userPwd":"密码3"}]
 * 2022-04-25 15:50:59.686  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Size】3
 * 2022-04-25 15:51:01.701  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Insert】{"userName":"张三4","userPwd":"密码4"}
 * 2022-04-25 15:51:01.702  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Query】[{"id":331,"userName":"张三1","userPwd":"密码1"},{"id":332,"userName":"张三2","userPwd":"密码2"},{"id":335,"userName":"张三3","userPwd":"密码3"},{"id":336,"userName":"张三4","userPwd":"密码4"}]
 * 2022-04-25 15:51:01.703  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Size】4
 * 2022-04-25 15:51:02.824  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Insert】{"userName":"张四3","userPwd":"密码23"}
 * 2022-04-25 15:51:02.825  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Query】[{"id":333,"userName":"张四1","userPwd":"密码21"},{"id":334,"userName":"张四2","userPwd":"密码22"},{"id":337,"userName":"张四3","userPwd":"密码23"}]
 * 2022-04-25 15:51:02.826  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Size】3
 * 2022-04-25 15:51:04.842  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Insert】{"userName":"张四4","userPwd":"密码24"}
 * 2022-04-25 15:51:04.843  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Query】[{"id":333,"userName":"张四1","userPwd":"密码21"},{"id":334,"userName":"张四2","userPwd":"密码22"},{"id":337,"userName":"张四3","userPwd":"密码23"},{"id":338,"userName":"张四4","userPwd":"密码24"}]
 * 2022-04-25 15:51:04.844  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Size】4
 * 2022-04-25 15:51:05.788  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Insert】{"userName":"张三5","userPwd":"密码5"}
 * 2022-04-25 15:51:05.789  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Query】[{"id":331,"userName":"张三1","userPwd":"密码1"},{"id":332,"userName":"张三2","userPwd":"密码2"},{"id":335,"userName":"张三3","userPwd":"密码3"},{"id":336,"userName":"张三4","userPwd":"密码4"},{"id":339,"userName":"张三5","userPwd":"密码5"}]
 * 2022-04-25 15:51:05.790  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Size】5
 * 2022-04-25 15:51:07.805  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Insert】{"userName":"张三6","userPwd":"密码6"}
 * 2022-04-25 15:51:07.806  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Query】[{"id":331,"userName":"张三1","userPwd":"密码1"},{"id":332,"userName":"张三2","userPwd":"密码2"},{"id":335,"userName":"张三3","userPwd":"密码3"},{"id":336,"userName":"张三4","userPwd":"密码4"},{"id":339,"userName":"张三5","userPwd":"密码5"},{"id":340,"userName":"张三6","userPwd":"密码6"}]
 * 2022-04-25 15:51:07.807  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Size】6
 * 2022-04-25 15:51:08.846  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Insert】{"userName":"张四5","userPwd":"密码25"}
 * 2022-04-25 15:51:08.847  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Query】[{"id":333,"userName":"张四1","userPwd":"密码21"},{"id":334,"userName":"张四2","userPwd":"密码22"},{"id":337,"userName":"张四3","userPwd":"密码23"},{"id":338,"userName":"张四4","userPwd":"密码24"},{"id":341,"userName":"张四5","userPwd":"密码25"}]
 * 2022-04-25 15:51:08.848  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Size】5
 * 2022-04-25 15:51:10.859  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Insert】{"userName":"张四6","userPwd":"密码26"}
 * 2022-04-25 15:51:10.861  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Query】[{"id":333,"userName":"张四1","userPwd":"密码21"},{"id":334,"userName":"张四2","userPwd":"密码22"},{"id":337,"userName":"张四3","userPwd":"密码23"},{"id":338,"userName":"张四4","userPwd":"密码24"},{"id":341,"userName":"张四5","userPwd":"密码25"},{"id":342,"userName":"张四6","userPwd":"密码26"}]
 * 2022-04-25 15:51:10.862  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Size】6
 * 2022-04-25 15:51:11.819  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Insert】{"userName":"张三7","userPwd":"密码7"}
 * 2022-04-25 15:51:11.820  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Query】[{"id":331,"userName":"张三1","userPwd":"密码1"},{"id":332,"userName":"张三2","userPwd":"密码2"},{"id":335,"userName":"张三3","userPwd":"密码3"},{"id":336,"userName":"张三4","userPwd":"密码4"},{"id":339,"userName":"张三5","userPwd":"密码5"},{"id":340,"userName":"张三6","userPwd":"密码6"},{"id":343,"userName":"张三7","userPwd":"密码7"}]
 * 2022-04-25 15:51:11.821  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Size】7
 * 2022-04-25 15:51:13.838  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Insert】{"userName":"张三8","userPwd":"密码8"}
 * 2022-04-25 15:51:13.839  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Query】[{"id":331,"userName":"张三1","userPwd":"密码1"},{"id":332,"userName":"张三2","userPwd":"密码2"},{"id":335,"userName":"张三3","userPwd":"密码3"},{"id":336,"userName":"张三4","userPwd":"密码4"},{"id":339,"userName":"张三5","userPwd":"密码5"},{"id":340,"userName":"张三6","userPwd":"密码6"},{"id":343,"userName":"张三7","userPwd":"密码7"},{"id":344,"userName":"张三8","userPwd":"密码8"}]
 * 2022-04-25 15:51:13.840  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Size】8
 * 2022-04-25 15:51:14.869  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Insert】{"userName":"张四7","userPwd":"密码27"}
 * 2022-04-25 15:51:14.871  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Query】[{"id":333,"userName":"张四1","userPwd":"密码21"},{"id":334,"userName":"张四2","userPwd":"密码22"},{"id":337,"userName":"张四3","userPwd":"密码23"},{"id":338,"userName":"张四4","userPwd":"密码24"},{"id":341,"userName":"张四5","userPwd":"密码25"},{"id":342,"userName":"张四6","userPwd":"密码26"},{"id":345,"userName":"张四7","userPwd":"密码27"}]
 * 2022-04-25 15:51:14.872  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Size】7
 * 2022-04-25 15:51:16.876  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Insert】{"userName":"张四8","userPwd":"密码28"}
 * 2022-04-25 15:51:16.878  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Query】[{"id":333,"userName":"张四1","userPwd":"密码21"},{"id":334,"userName":"张四2","userPwd":"密码22"},{"id":337,"userName":"张四3","userPwd":"密码23"},{"id":338,"userName":"张四4","userPwd":"密码24"},{"id":341,"userName":"张四5","userPwd":"密码25"},{"id":342,"userName":"张四6","userPwd":"密码26"},{"id":345,"userName":"张四7","userPwd":"密码27"},{"id":346,"userName":"张四8","userPwd":"密码28"}]
 * 2022-04-25 15:51:16.879  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Size】8
 * 2022-04-25 15:51:17.844  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Insert】{"userName":"张三9","userPwd":"密码9"}
 * 2022-04-25 15:51:17.845  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Query】[{"id":331,"userName":"张三1","userPwd":"密码1"},{"id":332,"userName":"张三2","userPwd":"密码2"},{"id":335,"userName":"张三3","userPwd":"密码3"},{"id":336,"userName":"张三4","userPwd":"密码4"},{"id":339,"userName":"张三5","userPwd":"密码5"},{"id":340,"userName":"张三6","userPwd":"密码6"},{"id":343,"userName":"张三7","userPwd":"密码7"},{"id":344,"userName":"张三8","userPwd":"密码8"},{"id":347,"userName":"张三9","userPwd":"密码9"}]
 * 2022-04-25 15:51:17.846  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Size】9
 * 2022-04-25 15:51:19.856  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Insert】{"userName":"张三10","userPwd":"密码10"}
 * 2022-04-25 15:51:19.857  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Query】[{"id":331,"userName":"张三1","userPwd":"密码1"},{"id":332,"userName":"张三2","userPwd":"密码2"},{"id":335,"userName":"张三3","userPwd":"密码3"},{"id":336,"userName":"张三4","userPwd":"密码4"},{"id":339,"userName":"张三5","userPwd":"密码5"},{"id":340,"userName":"张三6","userPwd":"密码6"},{"id":343,"userName":"张三7","userPwd":"密码7"},{"id":344,"userName":"张三8","userPwd":"密码8"},{"id":347,"userName":"张三9","userPwd":"密码9"},{"id":348,"userName":"张三10","userPwd":"密码10"}]
 * 2022-04-25 15:51:19.859  INFO 27028 --- [nio-9020-exec-3] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-3【Size】10
 * 2022-04-25 15:51:20.881  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Insert】{"userName":"张四9","userPwd":"密码29"}
 * 2022-04-25 15:51:20.882  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Query】[{"id":333,"userName":"张四1","userPwd":"密码21"},{"id":334,"userName":"张四2","userPwd":"密码22"},{"id":337,"userName":"张四3","userPwd":"密码23"},{"id":338,"userName":"张四4","userPwd":"密码24"},{"id":341,"userName":"张四5","userPwd":"密码25"},{"id":342,"userName":"张四6","userPwd":"密码26"},{"id":345,"userName":"张四7","userPwd":"密码27"},{"id":346,"userName":"张四8","userPwd":"密码28"},{"id":349,"userName":"张四9","userPwd":"密码29"}]
 * 2022-04-25 15:51:20.883  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Size】9
 * 2022-04-25 15:51:22.885  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Insert】{"userName":"张四10","userPwd":"密码210"}
 * 2022-04-25 15:51:22.886  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Query】[{"id":333,"userName":"张四1","userPwd":"密码21"},{"id":334,"userName":"张四2","userPwd":"密码22"},{"id":337,"userName":"张四3","userPwd":"密码23"},{"id":338,"userName":"张四4","userPwd":"密码24"},{"id":341,"userName":"张四5","userPwd":"密码25"},{"id":342,"userName":"张四6","userPwd":"密码26"},{"id":345,"userName":"张四7","userPwd":"密码27"},{"id":346,"userName":"张四8","userPwd":"密码28"},{"id":349,"userName":"张四9","userPwd":"密码29"},{"id":350,"userName":"张四10","userPwd":"密码210"}]
 * 2022-04-25 15:51:22.887  INFO 27028 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.Serializable    : http-nio-9020-exec-5【Size】10
 */
