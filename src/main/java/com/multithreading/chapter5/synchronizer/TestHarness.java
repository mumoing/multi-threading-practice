package com.multithreading.chapter5.synchronizer;

import java.util.concurrent.CountDownLatch;

/**
 * 使用CountDownLatch来启动和停止线程
 */
public class TestHarness {

    public long timeTasks(int nThread, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThread);

        for (int i = 0; i < nThread; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long start = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void main(String[] args) {
        TestHarness harness = new TestHarness();
        try {
            System.out.println(harness.timeTasks(3, new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
