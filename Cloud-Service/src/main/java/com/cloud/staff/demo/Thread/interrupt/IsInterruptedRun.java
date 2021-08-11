package com.cloud.staff.demo.Thread.interrupt;

public class IsInterruptedRun {
    public static void main(String[] args) {
        try {
           IsInterruptedThread isInterruptedThread=new IsInterruptedThread();
            isInterruptedThread.start();
            Thread.sleep(1000);
            isInterruptedThread.interrupt();
            System.out.println("是否停止工作1"+(isInterruptedThread.isInterrupted()?"true":"false"));
            System.out.println("是否停止工作2"+(isInterruptedThread.isInterrupted()?"true":"false"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//    /**
//     * Tests whether this thread has been interrupted.  The <i>interrupted
//     * status</i> of the thread is unaffected by this method.
//     *
//     * <p>A thread interruption ignored because a thread was not alive
//     * at the time of the interrupt will be reflected by this method
//     * returning false.
//     *
//     * @return  <code>true</code> if this thread has been interrupted;
//     *          <code>false</code> otherwise.
//     * @see     #interrupted()
//     * @revised 6.0
//     */
//    public boolean isInterrupted() {
//        return isInterrupted(false);
//    }
