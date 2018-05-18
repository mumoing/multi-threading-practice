package com.multithreading.chapter4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 使用限制确保线程安全
 * 虽然HashSet不是线程安全的，由于mySet是私有的，不会溢出，访问方法都有锁进行保护
 */
@ThreadSafe
public class PersonSet {

    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person p){
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p){
        return mySet.contains(p);
    }


    class Person{}
}
