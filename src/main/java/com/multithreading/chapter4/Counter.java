package com.multithreading.chapter4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 设计线程安全类的3个基本要素
 * 1、确定对象状态由哪些变量构成的
 * 2、确定限制状态变量的不变约束
 * 3、指定一个管理并发访问对象状态的策略
 */
@ThreadSafe
public class Counter {

    @GuardedBy("this")
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE)
            throw new IllegalStateException("counter overflow");

        return ++value;
    }
}
