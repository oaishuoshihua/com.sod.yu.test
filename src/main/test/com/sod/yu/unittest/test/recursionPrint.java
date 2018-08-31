package com.sod.yu.unittest.test;

/**
 * Created by yuhp on 2018/7/13
 **/
public class recursionPrint {

    /**
     * 给定一个数字n， 不使用循环，变量，每次 2n, 4n , 8n .....,当小于某个值时打印数字。
     * 例如 给定数字 1237，当小于5000时打印如下：
     *
     *1237
     *2474
     *4948
     *9896
     *9896
     *4948
     *2474
     *1237
     *
     * @param n
     * @param limit
     */
    public static void print(int n, int limit){
        System.out.println(n);
        if(n > 5000){
            return;
        } else{
            n = n << 1;
            print(n, limit);

        }
        System.out.println(n>> 1);
    }

    public static void main(String[] args) {
        print(1237,5000);
    }
}
