package com.multithreading.chapter5.filefind;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 搜索文件并建立索引
 */
public class FileIndexTest {
    private final static int MAX_CONSUMERS = 3;

    public static void main(String[] args) {

        BlockingQueue<File> queue = new LinkedBlockingDeque<>();
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return true;
            }
        };

        new Thread(new FileCrawler(queue, fileFilter, new File("D:\\"))).start();


        for (int i = 0; i < MAX_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}
