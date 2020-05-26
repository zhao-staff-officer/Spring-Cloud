package com.cloud.staff.apifirst.controller.java.Thread.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock公平锁与非公平锁
 */
public class demo4 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        demo41 threadDemo = new demo41(lock);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "运行了");
                threadDemo.service();
            }
        };

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}

//源码分析
//非公平锁-初始lock方法
//static final class NonfairSync extends ReentrantLock.Sync {
//    private static final long serialVersionUID = 7316153563782823691L;
//
//    /**
//     * Performs lock.  Try immediate barge, backing up to normal
//     * acquire on failure.
//     */
//    final void lock() {
//        新线程先枪一下,失败执行nonfairTryAcquire()
//        if (compareAndSetState(0, 1))
//            setExclusiveOwnerThread(Thread.currentThread());
//        else
//            acquire(1);
//    }
//
//    protected final boolean tryAcquire(int acquires) {
//        return nonfairTryAcquire(acquires);
//    }
//}

//final boolean nonfairTryAcquire(int acquires) {
//    final Thread current = Thread.currentThread();
//    int c = getState();
//    如果当前对象未被锁定（state=0），新线程会再尝试获取锁
//    if (c == 0) {
//        if (compareAndSetState(0, acquires)) {
//            setExclusiveOwnerThread(current);
//            return true;
//        }
//    }
//    else if (current == getExclusiveOwnerThread()) {
//        int nextc = c + acquires;
//        if (nextc < 0) // overflow
//            throw new Error("Maximum lock count exceeded");
//        setState(nextc);
//        return true;
//    }
//    return false;
//}

//公平锁
//
//    protected final boolean tryAcquire(int acquires) {
//        final Thread current = Thread.currentThread();
//        int c = getState();
//        if (c == 0) {
//        通过hasQueuedPredecessors()保证新线程排队线程顺序获取锁
//            if (!hasQueuedPredecessors() &&
//                    compareAndSetState(0, acquires)) {
//                setExclusiveOwnerThread(current);
//                return true;
//            }
//        }
//        else if (current == getExclusiveOwnerThread()) {
//            int nextc = c + acquires;
//            if (nextc < 0)
//                throw new Error("Maximum lock count exceeded");
//            setState(nextc);
//            return true;
//        }
//        return false;
//    }
//}

/**
 * 综上所述：
 * Reentrantrlock通过new Reentrantlokc(false) 与new Renntrantlock(true)定义非公平锁与非公平锁，
 * Renntrangtlock包含sync,nofairsync,fairsync三个静态内部内，nofairsync与fairsync继承sync类，
 * 非公平锁lock()通过compareAndSetState()获取锁，如果获取锁失败，调用父类nofairtryAcquire()再次尝试获取锁。
 * 公平锁lok()调用本类tryacquire()获取锁，通过hasQuenuedPredecessors()保证当前线程与等待线程有序消费。
 */
