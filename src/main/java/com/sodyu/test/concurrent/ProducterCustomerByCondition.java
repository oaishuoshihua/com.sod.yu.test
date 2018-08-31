package com.sodyu.test.concurrent;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName：
 * @Date 2017/2/21 15:02
 * @since JRE 1.6.0_22  or higher
 */
public class ProducterCustomerByCondition<T> {
    private static Lock lock=new ReentrantLock();
    private static Condition notFull=lock.newCondition();
    private static Condition notEmpty=lock.newCondition();
    private LinkedList<T> list=new LinkedList<T>();
    private  final int MAXSIZE;
    ProducterCustomerByCondition(int size){
        MAXSIZE=size;
    }
    public void put(T value) throws InterruptedException {
        lock.lock();
        try {
            if(list.size()==MAXSIZE){
                notFull.await();
            }
            list.add(value);
            notEmpty.signal();
            System.out.println("生产者"+Thread.currentThread().getName()+"放入值："+value);
        } finally {
            lock.unlock();
        }
    }
    public T take() throws InterruptedException {
        lock.lock();
        try {
            if(list.size()==0){
                notEmpty.await();
            }
            T value=(T)list.remove();
            notFull.signal();
            System.out.println("消费者"+Thread.currentThread().getName()+"获取值："+value);
            return value;
        } finally {
            lock.unlock();
        }
    }

    static class ProducterThread implements Runnable{
        private ProducterCustomerByCondition entity=null;
        public ProducterThread(ProducterCustomerByCondition entity){
            this.entity=entity;
        }
        @Override
        public void run() {
            for (int i=0; i<10 ; i++) {
                try {
                    entity.put(i+"");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class CustomerThread implements Runnable{
        private ProducterCustomerByCondition entity=null;
        public CustomerThread(ProducterCustomerByCondition entity){
            this.entity=entity;
        }
        @Override
        public void run() {
            for (int i = 0; i <10 ; i++) {
                try {
                    entity.take();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducterCustomerByCondition<String> entity=new ProducterCustomerByCondition<String>(10);
        for (int i = 0; i <3 ; i++) {
            new Thread(new ProducterThread(entity),"productor"+i).start();
            new Thread(new CustomerThread(entity),"customer"+i).start();
        }
    }
}
