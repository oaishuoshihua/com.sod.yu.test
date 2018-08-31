package com.sodyu.test.concurrent;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @Date 2017/2/9 10:14
 * @since JRE 1.6.0_22  or higher
 */
public class ProducterCustomerBySemapore {
    private Semaphore notFull=new Semaphore(10);
    private Semaphore notEmpty=new Semaphore(0);
    private Semaphore lock=new Semaphore(1);
    private LinkedList<String> buffer=new LinkedList<String>();

    public void put(String value){

        try {
            notFull.acquire();
            lock.acquire();
            buffer.add(value);
            System.out.println("生产者"+Thread.currentThread().getName()+"放入值："+value);
        } catch (InterruptedException e) {
            System.out.println("生产者报错！");
        }finally {
            lock.release();
            notEmpty.release();
        }
    }

    public void take(){
        try {
            notEmpty.acquire();
            lock.acquire();
            String value=buffer.remove();
            System.out.println("消费者"+Thread.currentThread().getName()+"获取值："+value);
        } catch (InterruptedException e) {
            System.out.println("消费者报错！");
        }finally {
            lock.release();
            notFull.release();
        }
    }

   static class ProducterThread implements Runnable{
        private ProducterCustomerBySemapore entity=null;
        public ProducterThread(ProducterCustomerBySemapore entity){
            this.entity=entity;
        }
        @Override
        public void run() {
            for (int i=0; i<10 ; i++) {
                entity.put(i+"");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
   static class CustomerThread implements Runnable{
        private ProducterCustomerBySemapore entity=null;
        public CustomerThread(ProducterCustomerBySemapore entity){
            this.entity=entity;
        }
        @Override
        public void run() {
            for (int i = 0; i <10 ; i++) {
                entity.take();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducterCustomerBySemapore entity=new ProducterCustomerBySemapore();
        for (int i = 0; i <3 ; i++) {
            new Thread(new ProducterThread(entity),"productor"+i).start();
            new Thread(new CustomerThread(entity),"customer"+i).start();
        }
    }
}

