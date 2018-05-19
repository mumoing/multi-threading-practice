package com.multithreading.chapter5.filefind;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者：搜索文件并加入队列
 */
public class FileCrawler implements Runnable {

    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                    System.out.println("遍历文件夹：" + entry.getAbsolutePath());
                } else if (!alreadyIndexed(entry)) {
                    fileQueue.put(entry);
                    System.out.println("搜索到文件：" + entry.getAbsolutePath());
                }
            }
        }
    }

    private boolean alreadyIndexed(File entry) {
        return fileQueue.contains(entry);
    }
}
