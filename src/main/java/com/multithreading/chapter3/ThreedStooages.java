package com.multithreading.chapter3;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * 用不可变性保证对象安全
 * ThreedStooages在被创建后不能再修改set
 */
@Immutable
public class ThreedStooages {

    private final Set<String> stooges = new HashSet<>();

    public ThreedStooages(){
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name){
        return stooges.contains(name);
    }
}
