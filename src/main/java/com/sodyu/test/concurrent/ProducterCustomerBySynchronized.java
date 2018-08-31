package com.sodyu.test.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName：
 * @Date 2017/2/9 10:49
 * @since JRE 1.6.0_22  or higher
 */
public class ProducterCustomerBySynchronized {
    private List<String> queue=new ArrayList<String>();
    private final static int MAX=50;
    public synchronized void put(String value) {
        try {
            if(queue.size()>=50){
                this.wait();
            }
            queue.add(value);
            System.out.println("生产者"+Thread.currentThread().getName()+"放入值："+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            this.notify();
        }
    }
    public synchronized void take() {
        try {
            if(queue.size()<=0){
                this.wait();
            }
            String value=queue.remove(0);
            System.out.println("消费者"+Thread.currentThread().getName()+"获取值："+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            this.notify();
        }
    }

    static class ProducterThread implements Runnable{
        private ProducterCustomerBySynchronized entity=null;
        public ProducterThread(ProducterCustomerBySynchronized entity){
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
        private ProducterCustomerBySynchronized entity=null;
        public CustomerThread(ProducterCustomerBySynchronized entity){
            this.entity=entity;
        }
        @Override
        public void run() {
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                entity.take();

            }
        }
    }
    public static void main(String[] args) {
        ProducterCustomerBySynchronized entity=new ProducterCustomerBySynchronized();
        for (int i = 0; i <1 ; i++) {
            new Thread(new ProducterThread(entity),"productor"+i).start();
            new Thread(new CustomerThread(entity),"customer"+i).start();
        }
    }
}
