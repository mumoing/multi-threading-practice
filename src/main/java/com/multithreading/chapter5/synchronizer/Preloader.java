package com.multithreading.chapter5.synchronizer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask的实现描述了一个抽象的可携带解雇的计算,Executor框架经常利用FutureTask完成异步任务
 * 使用FutureTask预加载稍后需要的数据
 */
public class Preloader {

    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return new ProductInfo("钻石", 100.00);
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
        this.thread.start();
    }

    public ProductInfo get() {
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Preloader loader = new Preloader();
        loader.start();
        System.out.println(loader.future.get());
        long end = System.currentTimeMillis();
        System.out.println("预加载数据共耗时" + (end - start) / 1000 + "s");
    }


    class ProductInfo {

        private String name;
        private double price;

        public ProductInfo(String name, double price) throws InterruptedException {
            Thread.currentThread().sleep(2000);
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "ProductInfo{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

}
