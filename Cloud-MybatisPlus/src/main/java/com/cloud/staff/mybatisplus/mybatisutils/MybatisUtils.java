package com.cloud.staff.mybatisplus.mybatisutils;

import com.cloud.staff.mybatisplus.pojo.entity.DatabaseEntity;
import org.springframework.stereotype.Component;

/**
 * mybatisutils工具包
 */
@Component
public class MybatisUtils <T extends DatabaseEntity>{


    /**
     * 主键查询
     * @param t
     * @return
     */
    public T queryByPrimyKey(T t){
        
        return  null;
    }



}
