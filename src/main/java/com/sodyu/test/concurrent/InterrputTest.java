package com.sodyu.test.concurrent;

/**
 * @Date 2017/2/21 11:10
 * @since JRE 1.6.0_22  or higher
 */
public class InterrputTest {
    public static void main(String[] args){
        InterruptThread thread=new InterrputTest().new InterruptThread(Thread.currentThread());
        Thread t=new Thread(thread);
        t.start();
        try {
            System.out.println("执行join");
            t.join();
        } catch (Exception e) {
            System.out.println("thread interrupted");
        }
        System.out.println("main over");
    }

    class InterruptThread implements Runnable{
        Thread thread=null;
        InterruptThread(Thread t){
            this.thread=t;
        }
        @Override
        public void run() {
            long i=0;
            while (i<20){
                System.out.println("thread"+(i++));
                if(i>10)
                thread.interrupt();//打断主线程等待
            }
        }
    }
}
