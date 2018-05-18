package com.multithreading.chapter4;

/**
 * 用私有锁保护状态
 */
public class PrivateLock {

    private final Object myLock = new Object();


    void someMethod(){
        synchronized (myLock){
            //do sth...
        }
    }
}
