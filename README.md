# 《JAVA并发编程实践》读书笔记及源码

## 第一章 介绍
## 第二章 线程安全
## 第三章 线程可见性
  synchronized不仅可以用于加锁，还具有另一个作用‘内存可见性’
  
  volatile是一种同步的弱形式，他确保对一个变量更新以可预见的方式告知其他的线程；而且对它的操作不会也其他的内容操作一起被重排序
  
  synchronized是一种加锁行为，有可能造成阻塞；而volatile不会加锁，只是轻量级的同步机制
  
  volatile通常被当做标志完成、中断、状态的标记使用，volatile的语义补足语使自增操作（i++）原子化
  
  ThreadLocal提供了get和set的访问器，为每个使用它的线程维护一份单独的拷贝。
## 第四章 组合对象
## 第五章 构建块
  ConcurrentHashMap不能够在独占访问中加锁，所以不能使用客户端加锁来创建新的原子操作

  CopyOnWriteArrayList使用写入时复制的的策略保证线程安全，每次需要修改的时候，他们会创建并重新发布一个新的容器拷贝

  BlockingQueue提供了可阻塞的put和take方法，它们与定时的offer和poll是等价的，如果Queue已满，put方法将阻塞直到空间可用；同理，take方法将被阻塞直到有元素可用
  
  java类库中提供了一些BlockingQueue的实现，LinkedBlockingQueuehe和ArrayBlockingQueue是FIFO队列，PriorityBlockingQueue是一个按优先级排序的队列，SynchronizedQueue不是一个真正的队列，不会为队列元素维护任何存储空间，它维护的是一个排队的线程清单，这些线程等待把元素加入(enqueue)或者移除(dequeue)队列
  
  Java6新增了两个容器类型，Deque和BlockingDeque，分别扩展了Queue和BlockingQueue，实现的是一个双端队列。双端队列适用于窃取工作的模式，适合用于解决消费者和生产者同体的问题。
  
  #### Synchronizer
  闭锁(latch):它可以延迟线程的进度知道线程到达肿着状态，如CountDownLatch,FutureTask,Semaphore
  
  关卡(barrier):关卡能够阻塞一组线程，直到某些事情发生。关卡与闭锁的关键不同在于，所有线程必须同时到达关卡点，才能继续处理。闭锁等待的事件，关卡等待的是其他线程，如CyclicBarrier
  
## 任务执行
#### Executor框架
    
    newSingleThreadPool:创建定长的线程池
    
    newCachedThreadPool:创建一个可缓存的线程池
    
    newSingleThreadExecutor:创建一个单线程化的executor
    
    newScheduledThreadPool:创建一个定长的线程池，而且支持定时的以及周期性的任务
    
    ExecutorService扩展了Executor接口，并添加了一些线程周期管理的方法
    
    Callable和Future可携带结果
    
    ExecutorCompletionService:Executor+BlockingQueue
