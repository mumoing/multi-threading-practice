package com.multithreading.chapter3;

/**
 *
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            //会打印42，或者0，这是一种重排序现象，java存储模型允许编译器重新排序操作，在CPU缓存数值
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
