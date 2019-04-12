package com.cloud.staff.apifirst.controller.java.Basic.Apachlang.Time;

import static java.util.Calendar.YEAR;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * 时间转换类
 * 时间转字符串
 * @author zhaoHB
 *
 */
public class DateTimeMain {
	public static void main(String[] args) {
//		Since FastDateFormat is thread safe, 
//		you can use a static member instance:
//      private static final FastDateFormat DATE_FORMATTER = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.SHORT); 
//		This class can be used as a direct replacement to SimpleDateFormat in most formatting and parsing situations.
//		This class is especially useful in multi-threaded server environments. SimpleDateFormat is not thread-safe in any JDK version, 
//		nor will it be as Sun have closed the bug/RFE. 
//      翻译过来FastDateFormate是线程安全的，顺便吐槽了一下SimpleDateFormat是不安全的

		//线程安全
		FastDateFormat fdf=FastDateFormat.getInstance();
		//测试
		//线程不安全
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//测试
//		ExecutorService executorService = Executors.newCachedThreadPool();
//		List<String> list=Arrays.asList(
//				"2019-04-01 10:00:01",
//	            "2019-04-02 11:00:02",
//	            "2019-04-03 12:00:03",
//	            "2019-04-04 13:00:04",
//	            "2019-04-05 14:00:05");
//		for(String str:list) {
//			executorService.execute(()->{
//				try {
//					sdf.parse(str);
//					TimeUnit.SECONDS.sleep(1);
//				} catch (ParseException | InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			});
//		}
		
		//FastDateFormate  源码分析
		
		//SimpleDateFormat 源码分析
//		1:在SimpleDateFormat转换日期是通过Calendar对象来操作的，SimpleDateFormat继承DateFormat类，DateFormat类中维护一个Calendar对象
//		2:simpledateformate-parse()
//		// If the year value is ambiguous,
//        // then the two-digit year == the default start year
//        if (ambiguousYear[0]) {
//            if (parsedDate.before(defaultCenturyStart)) {
//                parsedDate = calb.addYear(100).establish(calendar).getTime();
//            }
//        }
//		3:
//		 calendarBuild.establish();
//		 Calendar establish(Calendar cal) {
//	        boolean weekDate = isSet(WEEK_YEAR)
//	                            && field[WEEK_YEAR] > field[YEAR];
//	        if (weekDate && !cal.isWeekDateSupported()) {
//	            // Use YEAR instead
//	            if (!isSet(YEAR)) {
//	                set(YEAR, field[MAX_FIELD + WEEK_YEAR]);
//	            }
//	            weekDate = false;
//	        }
//          SimpleDateFormat维护的用于format和parse方法计算日期-时间的calendar被清空了，
//		         如果此时线程A将calendar清空且没有设置新值，线程B也进入parse方法用到了SimpleDateFormat对象中的calendar对象，此时就会产生线程安全问题！
//	        cal.clear();
//	        // Set the fields from the min stamp to the max stamp so that
//	        // the field resolution works in the Calendar.
	}

}
