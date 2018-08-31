package com.sodyu.test.concurrent;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @Date 2017/1/12 15:39
 * @since JRE 1.6.0_22  or higher
 */
public  class CopyOnWriteListTest{

    private CopyOnWriteArrayList cpList=new CopyOnWriteArrayList();

    public String get(String name){
        Iterator<String> iterator=cpList.iterator();
        while (iterator.hasNext()){
            String value=iterator.next();
            if(value.equals(name)){
                return value;
            }
        }
        return null;
    }

    public void set(String value){
        cpList.add(value);
    }

   public CopyOnWriteArrayList getCpList(){
       return  cpList;
   }
    public static void main(String[] args) throws InterruptedException {
        String key="test";
        CopyOnWriteListTest test=new CopyOnWriteListTest();

//        for (int i = 0; i <10 ; i++) {
//            MyThread th=new MyThread(key+i,test);
//            Thread thread=new Thread(th);
//            thread.start();
//
//        }
    }

}

