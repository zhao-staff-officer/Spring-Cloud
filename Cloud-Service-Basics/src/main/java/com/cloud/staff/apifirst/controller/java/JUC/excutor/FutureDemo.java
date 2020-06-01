package com.cloud.staff.apifirst.controller.java.JUC.excutor;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {

    static class TaskDemo implements Callable<Integer>{

        private int id;
        public TaskDemo(int id) {
            this.id = id;
        }

        @Override
        public Integer call() throws Exception {
            if(id%2==0)
            Thread.sleep((long) (Math.random()*10000));
            System.out.println("run task["+id+"]");
            return id;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec =Executors.newFixedThreadPool(5);
        try{
            List<Future<Integer>> list = new ArrayList<>();
            for(int i =0;i<10;i++){
               list.add(exec.submit(new FutureDemo.TaskDemo(i)));
            }

            for(Future<Integer> f:list){
               int value = f.get();
               System.out.println(value);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            exec.shutdown();
        }
    }


}
