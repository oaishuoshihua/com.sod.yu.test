package com.sodyu.test.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @Date 2017/2/14 10:31
 * @since JRE 1.6.0_22  or higher
 */
public class CountDownLatchTest {
    private CountDownLatch latch=new CountDownLatch(1);
    private CountDownLatch notifymain=new CountDownLatch(6);
    class LatchThread implements Runnable{

       private CountDownLatch latch;
       private CountDownLatch notifymain;
       public LatchThread(CountDownLatch latch,CountDownLatch notifymain){
           this.latch=latch;
           this.notifymain=notifymain;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"准备就绪");
            try {
                notifymain.countDown();
                latch.await();
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName()+"继续执行");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程等待各个线程准备就绪。。。。");
        CountDownLatchTest entity=new CountDownLatchTest();
        for (int i = 0; i < 6; i++) {
            new Thread(entity.new LatchThread(entity.latch,entity.notifymain),"thread"+i).start();
        }
        entity.notifymain.await();
        System.out.println("各线程等待主线程通知。。。。");
        System.out.println("主线程开始下发通知。。。。");
        entity.latch.countDown();
        //System.out.println("主线程结束");
    }

}
