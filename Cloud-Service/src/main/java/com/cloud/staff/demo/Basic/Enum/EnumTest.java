package com.cloud.staff.demo.Basic.Enum;

public enum EnumTest {

	Mon("Monday","1"), Tue("Tuesday","3"), Wed("Wednesday","5"), Thu("Thursday","7"), Fri("Friday","2"), Sat("Saturday","8"),
	Sun("Sunday","10");

	private String day;
	private String num;

	 EnumTest(String day,String num) {
	 this.day = day;
	 this.num = num;
	 }

	public String getDay() {
		return day;
	}

	public String getNum() {
		return num;
	}


}
