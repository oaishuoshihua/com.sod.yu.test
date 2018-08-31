package com.sodyu.test.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * @since JRE 1.6.0_22  or higher
 */
public class LockSupportTest {
    public static void  main(String args[]) throws Exception {
        Thread t = new Thread(new Runnable() {
            private int count = 0;

            @Override
            public void run() {
                long start = System.currentTimeMillis();
                long end = 0;

                while ((end - start) <= 1000) {
                    count++;
                    end = System.currentTimeMillis();
                }

                System.out.println("after 1 second.count=" + count);

                //等待或许许可
                LockSupport.park(Thread.currentThread());
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

            }
        });

        t.start();

        Thread.sleep(2000);

        // 中断线程
        t.interrupt();

        Thread.sleep(500);
        System.out.println("main over");
    }

}
