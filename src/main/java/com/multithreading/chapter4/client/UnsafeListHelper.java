package com.multithreading.chapter4.client;

import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 虽然putIfAbsent方法被声明为synchronized，但是这个锁没有用到ListHelper上，仅仅描绘了同步的幻想，等于使用的是不同的锁
 *
 * @param <E>
 */
@NotThreadSafe
public class UnsafeListHelper<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }


    public static void main(String[] args) {
        UnsafeListHelper<String> helper = new UnsafeListHelper<>();
        while (true) {
            new Thread() {
                @Override
                public void run() {
                    helper.putIfAbsent("hello world");
                }
            }.start();
            System.out.println(helper.list.size());
        }
    }
}
