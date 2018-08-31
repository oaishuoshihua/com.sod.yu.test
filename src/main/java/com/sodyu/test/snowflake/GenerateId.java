package com.sodyu.test.snowflake;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName：
 * @Date 2017/2/13 11:11
 * @since JRE 1.6.0_22  or higher
 */
public class GenerateId {
    final ReadWriteLock lock = new ReentrantReadWriteLock();
    volatile long pretime = -1L;
    volatile int preSeq;
    protected volatile int currentSeq = 0;

    public  long create()
    {
        long nodeIndex = 0;
        long ct = 0L;

        int seq = 0;
        this.lock.writeLock().lock();
        try
        {
            ct = getCurrentTime();
            if (ct == this.pretime)
            {
                seq = nextSeq();
                if (seq == this.preSeq) {
                    ct = tailTime(ct);
                }
            }
            else if (ct > this.pretime)
            {
                seq = nextSeq();
                this.preSeq = seq;
            }
            this.pretime = ct;
        }
        finally
        {
            this.lock.writeLock().unlock();
        }
        ct = ct << 15 | seq;
        ct = ct << 8 | nodeIndex;
        ct = ct << 8 | 0;

        return ct;
    }

    protected long getCurrentTime()
    {
        return System.currentTimeMillis() / 1000L;
    }

    protected long tailTime(long time)
    {
        long ct = getCurrentTime();
        while (ct <= time) {
            ct = getCurrentTime();
        }
        return ct;
    }

    protected int nextSeq()
    {
        this.currentSeq += 1;
        if (this.currentSeq >= 32767) {
            this.currentSeq = 0;
        }
        return this.currentSeq;
    }

    public static void main(String[] args) {;
        int count=0;
        long maxWorkerId=-1L;
        StringBuffer str=new StringBuffer();
        int i=0;
        while(i<64){
            if((maxWorkerId&1)!=0)
            {
                count++;
                str.append(1);
            }else{
                str.append(0);
            }
            maxWorkerId=maxWorkerId>>1;
            i++;
        }
        System.out.println(count);
        System.out.println(str.reverse().toString());
    }
    class Test implements Runnable{
        GenerateId entity;
        public Test(GenerateId entity){
            this.entity=entity;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                long id= entity.create();
                System.out.println(id);
            }
        }
    }
}
