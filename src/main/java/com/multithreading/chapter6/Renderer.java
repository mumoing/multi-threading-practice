package com.multithreading.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

/**
 * CompletionService:Executor+BlockingQueue
 * 使用CompletionService处理多线程问题
 *
 */
public class Renderer {

    private final ExecutorService executor;

    public Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    void operation(List<String> source) {

        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        for (String s : source) {
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return s;
                }
            });
        }

        for (int i = 0; i < source.size(); i++) {
            try {
                Future<String> future = completionService.take();
                System.out.println(future.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        List<String> sources = new ArrayList<>();
        sources.add("111");
        sources.add("222");
        sources.add("3333");
        sources.add("4444");
        sources.add("55555");

        new Renderer(Executors.newSingleThreadExecutor()).operation(sources);
    }
}
