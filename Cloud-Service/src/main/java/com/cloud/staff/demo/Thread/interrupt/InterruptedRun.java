package com.cloud.staff.demo.Thread.interrupt;

public class InterruptedRun {
//    test1:
//    public static void main(String[] args) {
//       try{
//           InterruptedThread interruptedThread=new InterruptedThread();
//           interruptedThread.start();
//           Thread.sleep(1000);
//           interruptedThread.interrupt();
//           System.out.println("是否停止工作1"+(Thread.interrupted()?"true":"false"));
//           System.out.println("是否停止工作1"+(Thread.interrupted()?"true":"false"));
//       }catch (Exception e){
//            e.printStackTrace();
//       }
//    }
//     result:false/false
//    test2
//    public static void main(String[] args) {
//        try{
//            Thread.currentThread().interrupt();
//            System.out.println("是否停止工作1"+(Thread.interrupted()?"true":"false"));
//            System.out.println("是否停止工作1"+(Thread.interrupted()?"true":"false"));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//    result: true/false

}

//    /**
//     * Tests whether the current thread has been interrupted.  The
//     * <i>interrupted status</i> of the thread is cleared by this method.  In
//     * other words,
//     if this method were to be called twice in succession, the
//     r如果这个方法被使用2次
//     * second call would return false (unless the current thread were
//      第二次会返回false.校验一次会重置线程状态为未停止。

//      private native boolean isInterrupted(boolean ClearInterrupted);
//      isInterrupted(true)清除状态

//     * interrupted again, after the first call had cleared its interrupted
//     * status and before the second call had examined it).
//     *
//     * <p>A thread interruption ignored because a thread was not alive
//     * at the time of the interrupt will be reflected by this method
//     * returning false.
//     *
//     * @return  <code>true</code> if the current thread has been interrupted;
//     *          <code>false</code> otherwise.
//     * @see #isInterrupted()
//     * @revised 6.0
//     */
//    当前线程是否停止
//    public static boolean interrupted() {
//        return currentThread().isInterrupted(true);
//    }
