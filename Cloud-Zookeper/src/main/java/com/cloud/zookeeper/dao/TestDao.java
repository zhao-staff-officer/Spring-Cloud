package com.cloud.zookeeper.dao;

import com.cloud.zookeeper.entity.TestEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
     * @return
     */
    @Select("select * from user")
    List<TestEntity> queryAll();

    /**
     * 新增用户
     * @param testEntity
     * @return
     */
    @Insert("insert into user(id,user_name,user_pwd) values (#{id},#{userName},#{userPwd})")
    int insert(TestEntity testEntity);


}
