package com.multithreading.chapter5.memory;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
