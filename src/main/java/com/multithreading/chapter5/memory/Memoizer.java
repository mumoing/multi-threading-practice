package com.multithreading.chapter5.memory;

import java.util.concurrent.*;

/**
 * 为计算结果建立高效、可伸缩的高速缓存
 * @param <A>
 * @param <V>
 */
public class Memoizer<A, V> implements Computable<A, V> {

    /**
     * 1.用ConcurrentMap代替HashMap,相对于对方法加锁，提高的并发性
     * 2.当一个线程启动一个开销很大的就按，可能会出现重复这个计算，用ConcurrentMap<A,Future<V>>代替ConcurrentMap<A,V>，future只要结果可用，就会
     * 立即返回；否则会一直堵塞知道结果被计算出来
     */
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            }catch (CancellationException e){
                //防止缓存污染，一旦发现计算被取消，就把Future移除
                cache.remove(arg,f);
            }catch (ExecutionException e){
                e.printStackTrace();
            }
        }
    }
}
