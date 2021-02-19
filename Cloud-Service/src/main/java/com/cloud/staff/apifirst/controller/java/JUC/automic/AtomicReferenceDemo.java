package com.cloud.staff.apifirst.controller.java.JUC.automic;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 引用类型
 * atomicReference,atomicReferenceArray,atomicReferenceFieldUpdater
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
    //atomicMarkableReference
    static  class AtomicMarkableReferenceTest{
        public static void main(String[] args) {
            User user1 =new User("zhangsan",10);
            User user2 =new User("lisi",12);
            AtomicMarkableReference<User> atomicMarkableReference =new AtomicMarkableReference<>(user1,false);
            atomicMarkableReference.compareAndSet(user1,user2,false,false);
            System.out.println(atomicMarkableReference.get(new boolean[1]).getAge());
        }
    }
    //atomicStampedReference
    static class AtomicStampedReferenceTest{
        public static void main(String[] args) {
            User user1 =new User("zhangsan",10);
            User user2 =new User("lisi",12);
            AtomicStampedReference<User> atomicStampedReference=new AtomicStampedReference<>(user1,158);
            int[] stamp = new int[10];
            System.out.println(atomicStampedReference.get(stamp).getName());
            System.out.println(atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(user1,user2,stamp[0],2);
            System.out.println(atomicStampedReference.get(stamp).getName());
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
