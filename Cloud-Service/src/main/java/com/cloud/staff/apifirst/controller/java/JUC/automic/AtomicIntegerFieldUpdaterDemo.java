package com.cloud.staff.apifirst.controller.java.JUC.automic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 字段类型更新
 * atomicIntegerFieldUpdater,atomicLongFieldUpdater
 */
public class AtomicIntegerFieldUpdaterDemo {
    // 创建原子更新器，并设置需要更新的对象类和对象的属性
    private static AtomicIntegerFieldUpdater<User>  atomicUser = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

    public static void main(String[] args) {
          User conan = new User("conan",10);
          atomicUser.getAndIncrement(conan);
          System.out.println(atomicUser.get(conan));
          atomicUser.addAndGet(conan,12);
          System.out.println(atomicUser.get(conan));
    }



    static class User{
        private String name;
        public volatile int age;

        public User(String name,int age){
            this.name=name;
            this.age=age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
