package com.sodyu.test.concurrent;

import java.util.concurrent.Semaphore;

/**
 * ClassName：
 * @Date 2017/1/16 18:24
 * @since JRE 1.6.0_22  or higher
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(2);
        for (int i = 0; i <6 ; i++) {
            new MyThread(semaphore,i).start();
        }
    }

     static class MyThread extends Thread{
        Semaphore semaphore=null;
        int i;
        public MyThread(Semaphore s,int num ){
            this.semaphore=s;
            i=num;
        }

        @Override
        public void run(){
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"执行操作。。。。。。"+i);
                Thread.sleep(2000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
