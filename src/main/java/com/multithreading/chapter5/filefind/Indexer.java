package com.multithreading.chapter5.filefind;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 消费者：从队列获取文件并建立索引
 */
public class Indexer implements Runnable {

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File file) {
        //从队列中取出文件，模拟建立索引过程
        queue.remove(file);
        System.out.println(Thread.currentThread().getName() + "：为文件" + file.getAbsolutePath() + "添加索引");
    }
}
