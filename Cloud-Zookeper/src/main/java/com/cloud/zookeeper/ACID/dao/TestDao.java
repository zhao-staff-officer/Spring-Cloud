package com.cloud.zookeeper.ACID.dao;

import com.cloud.zookeeper.ACID.entity.TestEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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
 * @description：
 * @date 2022/4/22 14:22
 **/
@Mapper
public interface TestDao {

    /**
     * 查询所有用户
     * 关闭mybatis缓存
     * @return
     */
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    @Select("select * from user")
    List<TestEntity> queryAll();

    /**
     * 新增用户
     * @param testEntity
     * @return
     */
    @Insert("insert into user(user_name,user_pwd) values (#{userName},#{userPwd})")
    int insert(TestEntity testEntity);


}
