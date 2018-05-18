package com.multithreading.chapter2;


import net.jcip.annotations.NotThreadSafe;

/**
 * 惰性初始化
 * 产生‘读-写-读’操作
 */
@NotThreadSafe
public class LazyInitRace {

    private LazyInitRace instance = null;

    public LazyInitRace getInstance() {
        if (instance == null) {
            return new LazyInitRace();
        }
        return instance;
    }
}
