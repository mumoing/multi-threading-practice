package com.multithreading.chapter5.memory;

public class MemonizerTest {

    private final Computable<Double, Double> c = new Computable<Double, Double>() {
        @Override
        public Double compute(Double arg) throws InterruptedException {
            return factory(arg);
        }
    };

    private final Computable<Double, Double> cache = new Memoizer<>(c);


    Double factory(Double n) {
        if (n == 1D) {
            return 1D;
        } else {
            return n * factory(n - 1);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MemonizerTest test = new MemonizerTest();
        long start = System.currentTimeMillis();
        System.out.println(test.cache.compute(100D));

        long end1 = System.currentTimeMillis();
        System.out.println("耗时"+(end1-start));

        System.out.println(test.cache.compute(100D));
        long end2 = System.currentTimeMillis();
        System.out.println("耗时"+(end2-end1));
    }
}
