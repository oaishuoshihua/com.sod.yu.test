package com.sodyu.test.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Date 2017/1/16 18:35
 * @since JRE 1.6.0_22  or higher
 */
public class CyclicBarrierTest {
    private static CyclicBarrier cyclicBarrier=new CyclicBarrier(4, new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"执行回调");
        }
    });

    public static void main(String[] args) {

        for (int i = 0; i <4 ; i++) {
            new MyThread().start();
        }
        System.out.println("主线程");
    }

    static class  MyThread extends Thread{
        public MyThread(){

        }
        @Override
        public void run(){
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行任务");
                Thread.sleep(1000);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName()+"与大家一起执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
