package com.cloud.staff.common.unknown.dstandcst;

import cn.hutool.core.bean.BeanUtil;

/**
 * 今天遇到一个很奇怪问题，传入相同时间格式yyyyMMdd
 * 用hutool工具包转换一个转换为cst，一个转换为dst
 * @author 赵参谋
 * @date 2019-05-10
 * 下面我们开始研究
 * 马丹
 */
public class DateTestMain {
	
	public static void main(String[] args) {
		String date="20190101";
		Date date2=new Date();
		for(int i=0,j=20;i<j;i++) {
			BeanUtil.copyProperties(date, date2);
			System.out.println();
		}
	}

}
