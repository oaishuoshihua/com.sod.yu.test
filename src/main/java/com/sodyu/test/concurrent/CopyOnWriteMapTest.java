package com.sodyu.test.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Date 2017/1/12 17:44
 * @since JRE 1.6.0_22  or higher
 */
public class CopyOnWriteMapTest<K,V> extends HashMap<K,V> implements Cloneable{
    private volatile Map<K, V> internalMap;
    private ReentrantLock lock=new ReentrantLock();
    private ReentrantReadWriteLock lock1=new ReentrantReadWriteLock();
    public CopyOnWriteMapTest(){
        this.internalMap=new HashMap<K, V>();
    }

    public V put(K key,V value){
        lock.lock();
        try {
            Map<K,V> map=new HashMap<K, V>(internalMap);
            map.put(key,value);
            internalMap=map;
            return  value;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> newData){
        lock.lock();
        try {
            Map<K,V> map=new HashMap<K, V>(newData);
            map.putAll(map);
            internalMap = map;
        }finally {
            lock.unlock();
        }
    }


}
