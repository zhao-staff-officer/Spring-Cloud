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
 * @date 2022/4/25 11:38
 **/
@RestController
public class RepeatableRead {

    private static final Logger log = LoggerFactory.getLogger(RepeatableRead.class);

    @Autowired
    private TestDao testDao;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    /**
     * 事务A
     */
    @GetMapping("/repeatableRead/transactionalA")
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
    @GetMapping("/repeatableRead/transactionalB")
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
 *2022-04-25 15:32:58.838  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Insert】{"userName":"张三1","userPwd":"密码1"}
 * 2022-04-25 15:32:58.839  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Query】[{"id":291,"userName":"张三1","userPwd":"密码1"}]
 * 2022-04-25 15:32:58.840  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Size】1
 * 2022-04-25 15:33:00.853  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Insert】{"userName":"张三2","userPwd":"密码2"}
 * 2022-04-25 15:33:00.854  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Query】[{"id":291,"userName":"张三1","userPwd":"密码1"},{"id":292,"userName":"张三2","userPwd":"密码2"}]
 * 2022-04-25 15:33:00.855  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Size】2
 * 2022-04-25 15:33:02.347  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Insert】{"userName":"张四1","userPwd":"密码21"}
 * 2022-04-25 15:33:02.348  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Query】[{"id":293,"userName":"张四1","userPwd":"密码21"}]
 * 2022-04-25 15:33:02.349  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Size】1
 * 2022-04-25 15:33:04.353  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Insert】{"userName":"张四2","userPwd":"密码22"}
 * 2022-04-25 15:33:04.354  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Query】[{"id":293,"userName":"张四1","userPwd":"密码21"},{"id":294,"userName":"张四2","userPwd":"密码22"}]
 * 2022-04-25 15:33:04.356  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Size】2
 * 2022-04-25 15:33:04.871  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Insert】{"userName":"张三3","userPwd":"密码3"}
 * 2022-04-25 15:33:04.872  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Query】[{"id":291,"userName":"张三1","userPwd":"密码1"},{"id":292,"userName":"张三2","userPwd":"密码2"},{"id":295,"userName":"张三3","userPwd":"密码3"}]
 * 2022-04-25 15:33:04.873  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Size】3
 * 2022-04-25 15:33:06.882  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Insert】{"userName":"张三4","userPwd":"密码4"}
 * 2022-04-25 15:33:06.883  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Query】[{"id":291,"userName":"张三1","userPwd":"密码1"},{"id":292,"userName":"张三2","userPwd":"密码2"},{"id":295,"userName":"张三3","userPwd":"密码3"},{"id":296,"userName":"张三4","userPwd":"密码4"}]
 * 2022-04-25 15:33:06.885  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Size】4
 * 2022-04-25 15:33:08.371  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Insert】{"userName":"张四3","userPwd":"密码23"}
 * 2022-04-25 15:33:08.372  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Query】[{"id":293,"userName":"张四1","userPwd":"密码21"},{"id":294,"userName":"张四2","userPwd":"密码22"},{"id":297,"userName":"张四3","userPwd":"密码23"}]
 * 2022-04-25 15:33:08.373  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Size】3
 * 2022-04-25 15:33:10.381  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Insert】{"userName":"张四4","userPwd":"密码24"}
 * 2022-04-25 15:33:10.382  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Query】[{"id":293,"userName":"张四1","userPwd":"密码21"},{"id":294,"userName":"张四2","userPwd":"密码22"},{"id":297,"userName":"张四3","userPwd":"密码23"},{"id":298,"userName":"张四4","userPwd":"密码24"}]
 * 2022-04-25 15:33:10.383  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Size】4
 * 2022-04-25 15:33:10.900  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Insert】{"userName":"张三5","userPwd":"密码5"}
 * 2022-04-25 15:33:10.901  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Query】[{"id":291,"userName":"张三1","userPwd":"密码1"},{"id":292,"userName":"张三2","userPwd":"密码2"},{"id":295,"userName":"张三3","userPwd":"密码3"},{"id":296,"userName":"张三4","userPwd":"密码4"},{"id":299,"userName":"张三5","userPwd":"密码5"}]
 * 2022-04-25 15:33:10.902  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Size】5
 * 2022-04-25 15:33:12.909  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Insert】{"userName":"张三6","userPwd":"密码6"}
 * 2022-04-25 15:33:12.910  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Query】[{"id":291,"userName":"张三1","userPwd":"密码1"},{"id":292,"userName":"张三2","userPwd":"密码2"},{"id":295,"userName":"张三3","userPwd":"密码3"},{"id":296,"userName":"张三4","userPwd":"密码4"},{"id":299,"userName":"张三5","userPwd":"密码5"},{"id":300,"userName":"张三6","userPwd":"密码6"}]
 * 2022-04-25 15:33:12.911  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Size】6
 * 2022-04-25 15:33:14.392  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Insert】{"userName":"张四5","userPwd":"密码25"}
 * 2022-04-25 15:33:14.393  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Query】[{"id":293,"userName":"张四1","userPwd":"密码21"},{"id":294,"userName":"张四2","userPwd":"密码22"},{"id":297,"userName":"张四3","userPwd":"密码23"},{"id":298,"userName":"张四4","userPwd":"密码24"},{"id":301,"userName":"张四5","userPwd":"密码25"}]
 * 2022-04-25 15:33:14.394  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Size】5
 * 2022-04-25 15:33:16.397  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Insert】{"userName":"张四6","userPwd":"密码26"}
 * 2022-04-25 15:33:16.398  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Query】[{"id":293,"userName":"张四1","userPwd":"密码21"},{"id":294,"userName":"张四2","userPwd":"密码22"},{"id":297,"userName":"张四3","userPwd":"密码23"},{"id":298,"userName":"张四4","userPwd":"密码24"},{"id":301,"userName":"张四5","userPwd":"密码25"},{"id":302,"userName":"张四6","userPwd":"密码26"}]
 * 2022-04-25 15:33:16.400  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Size】6
 * 2022-04-25 15:33:16.913  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Insert】{"userName":"张三7","userPwd":"密码7"}
 * 2022-04-25 15:33:16.919  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Query】[{"id":291,"userName":"张三1","userPwd":"密码1"},{"id":292,"userName":"张三2","userPwd":"密码2"},{"id":295,"userName":"张三3","userPwd":"密码3"},{"id":296,"userName":"张三4","userPwd":"密码4"},{"id":299,"userName":"张三5","userPwd":"密码5"},{"id":300,"userName":"张三6","userPwd":"密码6"},{"id":303,"userName":"张三7","userPwd":"密码7"}]
 * 2022-04-25 15:33:16.920  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Size】7
 * 2022-04-25 15:33:18.921  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Insert】{"userName":"张三8","userPwd":"密码8"}
 * 2022-04-25 15:33:18.922  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Query】[{"id":291,"userName":"张三1","userPwd":"密码1"},{"id":292,"userName":"张三2","userPwd":"密码2"},{"id":295,"userName":"张三3","userPwd":"密码3"},{"id":296,"userName":"张三4","userPwd":"密码4"},{"id":299,"userName":"张三5","userPwd":"密码5"},{"id":300,"userName":"张三6","userPwd":"密码6"},{"id":303,"userName":"张三7","userPwd":"密码7"},{"id":304,"userName":"张三8","userPwd":"密码8"}]
 * 2022-04-25 15:33:18.923  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Size】8
 * 2022-04-25 15:33:20.405  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Insert】{"userName":"张四7","userPwd":"密码27"}
 * 2022-04-25 15:33:20.405  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Query】[{"id":293,"userName":"张四1","userPwd":"密码21"},{"id":294,"userName":"张四2","userPwd":"密码22"},{"id":297,"userName":"张四3","userPwd":"密码23"},{"id":298,"userName":"张四4","userPwd":"密码24"},{"id":301,"userName":"张四5","userPwd":"密码25"},{"id":302,"userName":"张四6","userPwd":"密码26"},{"id":305,"userName":"张四7","userPwd":"密码27"}]
 * 2022-04-25 15:33:20.406  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Size】7
 * 2022-04-25 15:33:22.413  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Insert】{"userName":"张四8","userPwd":"密码28"}
 * 2022-04-25 15:33:22.414  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Query】[{"id":293,"userName":"张四1","userPwd":"密码21"},{"id":294,"userName":"张四2","userPwd":"密码22"},{"id":297,"userName":"张四3","userPwd":"密码23"},{"id":298,"userName":"张四4","userPwd":"密码24"},{"id":301,"userName":"张四5","userPwd":"密码25"},{"id":302,"userName":"张四6","userPwd":"密码26"},{"id":305,"userName":"张四7","userPwd":"密码27"},{"id":306,"userName":"张四8","userPwd":"密码28"}]
 * 2022-04-25 15:33:22.415  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Size】8
 * 2022-04-25 15:33:22.928  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Insert】{"userName":"张三9","userPwd":"密码9"}
 * 2022-04-25 15:33:22.929  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Query】[{"id":291,"userName":"张三1","userPwd":"密码1"},{"id":292,"userName":"张三2","userPwd":"密码2"},{"id":295,"userName":"张三3","userPwd":"密码3"},{"id":296,"userName":"张三4","userPwd":"密码4"},{"id":299,"userName":"张三5","userPwd":"密码5"},{"id":300,"userName":"张三6","userPwd":"密码6"},{"id":303,"userName":"张三7","userPwd":"密码7"},{"id":304,"userName":"张三8","userPwd":"密码8"},{"id":307,"userName":"张三9","userPwd":"密码9"}]
 * 2022-04-25 15:33:22.930  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Size】9
 * 2022-04-25 15:33:24.936  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Insert】{"userName":"张三10","userPwd":"密码10"}
 * 2022-04-25 15:33:24.937  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Query】[{"id":291,"userName":"张三1","userPwd":"密码1"},{"id":292,"userName":"张三2","userPwd":"密码2"},{"id":295,"userName":"张三3","userPwd":"密码3"},{"id":296,"userName":"张三4","userPwd":"密码4"},{"id":299,"userName":"张三5","userPwd":"密码5"},{"id":300,"userName":"张三6","userPwd":"密码6"},{"id":303,"userName":"张三7","userPwd":"密码7"},{"id":304,"userName":"张三8","userPwd":"密码8"},{"id":307,"userName":"张三9","userPwd":"密码9"},{"id":308,"userName":"张三10","userPwd":"密码10"}]
 * 2022-04-25 15:33:24.938  INFO 2500 --- [nio-9020-exec-6] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-6【Size】10
 * 2022-04-25 15:33:26.424  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Insert】{"userName":"张四9","userPwd":"密码29"}
 * 2022-04-25 15:33:26.425  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Query】[{"id":293,"userName":"张四1","userPwd":"密码21"},{"id":294,"userName":"张四2","userPwd":"密码22"},{"id":297,"userName":"张四3","userPwd":"密码23"},{"id":298,"userName":"张四4","userPwd":"密码24"},{"id":301,"userName":"张四5","userPwd":"密码25"},{"id":302,"userName":"张四6","userPwd":"密码26"},{"id":305,"userName":"张四7","userPwd":"密码27"},{"id":306,"userName":"张四8","userPwd":"密码28"},{"id":309,"userName":"张四9","userPwd":"密码29"}]
 * 2022-04-25 15:33:26.426  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Size】9
 * 2022-04-25 15:33:28.434  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Insert】{"userName":"张四10","userPwd":"密码210"}
 * 2022-04-25 15:33:28.435  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Query】[{"id":293,"userName":"张四1","userPwd":"密码21"},{"id":294,"userName":"张四2","userPwd":"密码22"},{"id":297,"userName":"张四3","userPwd":"密码23"},{"id":298,"userName":"张四4","userPwd":"密码24"},{"id":301,"userName":"张四5","userPwd":"密码25"},{"id":302,"userName":"张四6","userPwd":"密码26"},{"id":305,"userName":"张四7","userPwd":"密码27"},{"id":306,"userName":"张四8","userPwd":"密码28"},{"id":309,"userName":"张四9","userPwd":"密码29"},{"id":310,"userName":"张四10","userPwd":"密码210"}]
 * 2022-04-25 15:33:28.436  INFO 2500 --- [nio-9020-exec-5] com.cloud.zookeeper.ACID.RepeatableRead  : http-nio-9020-exec-5【Size】10
 */
