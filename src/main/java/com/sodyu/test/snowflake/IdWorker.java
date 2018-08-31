package com.sodyu.test.snowflake;


public class IdWorker {

    private long workerId;//机器码后5位
    private long datacenterId;//机器码前5位
    private long sequence = 0L;//序列号默认值
    private long twepoch = 1288834974657L;//初始时间戳变量（必须在当前时间之前）
    private long workerIdBits = 5L; //机器码中的后5位所占位数
    private long datacenterIdBits = 5L;//机器码中的前5位所占位数
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);//机器码后5位最大值（或者掩码值，具体值看下例移位操作）
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);//机器码前5位最大值（掩码值，具体看下例移位操作）
    private long sequenceBits = 12L;//序列号占12位（其实可以自己设置，默认1位不用+41位时间戳+10位机器码+12位序列号）
    private long workerIdShift = sequenceBits;//机器码后5位需要的左移位数
    private long datacenterIdShift = sequenceBits + workerIdBits;//机器码前5位需要左移的位数
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;//时间戳需要左移的位数
    private long sequenceMask = -1L ^ (-1L << sequenceBits);//序列号掩码（即同一毫秒可以支持的最大序列号）

    private long lastTimestamp = -1L;

    public IdWorker(long workerId, long datacenterId) {//判断机器码
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {//同一时间戳的话将会增加序列号，如果序列号达到最大则从0重新开始，时间重新取
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
        }
    }
}

