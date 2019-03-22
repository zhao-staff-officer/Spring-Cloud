package com.cloud.staff.apifirst.controller.jdkproxyAndCGlib;

public class UserManagerImpl implements UserManager{

	@Override
	public void addUser(String id, String password) {
		System.out.println(".: 掉用了UserManagerImpl.addUser()方法！ ");  
		
	}

	@Override
	public void delUser(String id) {
		System.out.println(".: 掉用了UserManagerImpl.delUser()方法！ ");
		
	}

}
