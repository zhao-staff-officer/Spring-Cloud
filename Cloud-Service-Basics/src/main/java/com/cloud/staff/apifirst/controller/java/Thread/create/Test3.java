package com.cloud.staff.apifirst.controller.java.Thread.create;

/**
 * thread.start()方法
 */
//    public synchronized void start() {
//        /**
//         * This method is not invoked for the main method thread or "system"
//         * group threads created/set up by the VM. Any new functionality added
//         * to this method in the future may have to also be added to the VM.
//         *
//         * A zero status value corresponds to state "NEW".
//         */
//        if (threadStatus != 0)
//            throw new IllegalThreadStateException();
//
//        /* Notify the group that this thread is about to be started
//         * so that it can be added to the group's list of threads
//         * and the group's unstarted count can be decremented. */
//        加入现成组
//        group.add(this);
//
//        boolean started = false;
//        try {
//            调用native-start0方法创建现成执行
//            start0();
//            started = true;
//        } finally {
//            try {
//                if (!started) {
//                    group.threadStartFailed(this);
//                }
//            } catch (Throwable ignore) {
//                /* do nothing. If start0 threw a Throwable then
//                  it will be passed up the call stack */
//            }
//        }
//    }

/**
 * run方法
 */
//@Override
//public void run() {
//    if (target != null) {
//        target.run();
//    }
//}

public class Test3 {
    public static void main(String[] args) {
        ImplementsRunnable ir=new ImplementsRunnable();
        Thread thread=new Thread(ir,"threadname");
        thread.start();
        System.out.println("运行结束");
    }
}
