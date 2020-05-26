package com.cloud.staff.mybatisplus.mybatisutilstest;

import com.cloud.staff.mybatisplus.mybatisutils.MybatisUtils;
import com.cloud.staff.mybatisplus.pojo.entity.FinanceParkIncomeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class MybatisUtilsTest {

    @Autowired
    private MybatisUtils<FinanceParkIncomeEntity> mybatisUtils;

    @Test
    public void test(){
        FinanceParkIncomeEntity financeParkIncomeEntity=new FinanceParkIncomeEntity();
        FinanceParkIncomeEntity financeParkIncomeEntityResult = mybatisUtils.queryByPrimyKey(financeParkIncomeEntity);
    }
}
