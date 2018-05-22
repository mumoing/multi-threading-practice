package com.multithreading.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用Callable和Future等待处理结果
 */
public class FutrueRenderer {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    void operation(String source) {
        Callable<List<String>> task = new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                List<String> result = new ArrayList<>();
                String[] sources = source.split("-");
                for (int i = 0; i < sources.length; i++) {
                    result.add(sources[i]);
                }
                return result;
            }
        };

        Future<List<String>> future = executor.submit(task);

        try {
            List<String> result = future.get();
            result.stream().forEach(System.out::println);
            // 打印结果
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            future.cancel(true);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "111-ssss-44-vvv-5555";
        new FutrueRenderer().operation(source);
    }
}
