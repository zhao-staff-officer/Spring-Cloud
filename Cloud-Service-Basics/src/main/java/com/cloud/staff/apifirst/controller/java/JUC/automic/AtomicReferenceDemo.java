package com.cloud.staff.apifirst.controller.java.JUC.automic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 引用类型
 * atomicReference,atomicReferenceArray,
 * atomicMarkableReference,atomicStampedReference
 */
public class AtomicReferenceDemo {

    //atomicReferenceTest
    static class AtomicReferenceTest{
        public static AtomicReference<User> atomicReference=new AtomicReference<User>();

        public static void main(String[] args) {
            User user=new User("connan",15);
            atomicReference.set(user);
            User updaterUser = new User("zhangsan",18);
            atomicReference.compareAndSet(user,updaterUser);
            System.out.println(atomicReference.get().getName());
        }

    }


    static class User{
        private String name;
        public  int age;

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
