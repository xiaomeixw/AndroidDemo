package sabria.demo.threadpractice.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import sabria.demo.threadpractice.Utils;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-16  18:44
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class ThreadPool {

    /**
     * 12-16 19:07:48.378    9262-9318/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 0
     * 12-16 19:07:48.378    9262-9321/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-3 Start. Command = 2
     * 12-16 19:07:48.378    9262-9320/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 Start. Command = 1
     * 12-16 19:07:48.378    9262-9322/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-4 Start. Command = 3
     * 12-16 19:07:48.378    9262-9323/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-5 Start. Command = 4
     * 12-16 19:07:51.378    9262-9318/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:07:51.378    9262-9321/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-3 End.
     * 12-16 19:07:51.378    9262-9318/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 5
     * 12-16 19:07:51.378    9262-9320/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 End.
     * 12-16 19:07:51.378    9262-9321/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-3 Start. Command = 6
     * 12-16 19:07:51.378    9262-9322/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-4 End.
     * 12-16 19:07:51.378    9262-9322/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-4 Start. Command = 7
     * 12-16 19:07:51.378    9262-9320/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 Start. Command = 8
     * 12-16 19:07:51.378    9262-9323/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-5 End.
     * 12-16 19:07:51.378    9262-9323/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-5 Start. Command = 9
     * 12-16 19:07:54.378    9262-9318/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:07:54.378    9262-9321/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-3 End.
     * 12-16 19:07:54.378    9262-9322/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-4 End.
     * 12-16 19:07:54.378    9262-9320/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 End.
     * 12-16 19:07:54.378    9262-9323/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-5 End.
     * 12-16 19:07:54.378    9262-9262/sabria.demo.threadpractice I/MainActivity﹕ Finished all threads
     */
    public void startFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            WorkRunnable workRunnable = new WorkRunnable(i + "");
            pool.execute(workRunnable);
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
        }
        Utils.log("Finished all threads");
    }

    /**
     * 12-16 19:25:00.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 0
     * 12-16 19:25:03.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:25:03.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 1
     * 12-16 19:25:06.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:25:06.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 2
     * 12-16 19:25:09.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:25:09.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 3
     * 12-16 19:25:12.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:25:12.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 4
     * 12-16 19:25:15.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:25:15.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 5
     * 12-16 19:25:18.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:25:18.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 6
     * 12-16 19:25:21.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:25:21.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 7
     * 12-16 19:25:24.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:25:24.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 8
     * 12-16 19:25:27.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:25:27.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 9
     * 12-16 19:25:30.238  17531-17662/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:25:30.238  17531-17531/sabria.demo.threadpractice I/MainActivity﹕ Finished all threads
     */
    public void startSingleThreadPool() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            WorkRunnable workRunnable = new WorkRunnable(i + "");
            pool.execute(workRunnable);
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
        }
        Utils.log("Finished all threads");
    }

    /**
     * 12-16 19:32:23.248  21394-21442/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 Start. Command = 1
     * 12-16 19:32:23.248  21394-21443/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-3 Start. Command = 2
     * 12-16 19:32:23.258  21394-21441/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 0
     * 12-16 19:32:23.258  21394-21444/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-4 Start. Command = 3
     * 12-16 19:32:23.258  21394-21445/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-5 Start. Command = 4
     * 12-16 19:32:23.258  21394-21446/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-6 Start. Command = 5
     * 12-16 19:32:23.258  21394-21447/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-7 Start. Command = 6
     * 12-16 19:32:23.258  21394-21448/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-8 Start. Command = 7
     * 12-16 19:32:23.258  21394-21449/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-9 Start. Command = 8
     * 12-16 19:32:23.258  21394-21450/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-10 Start. Command = 9
     * 12-16 19:32:26.248  21394-21442/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 End.
     * 12-16 19:32:26.258  21394-21443/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-3 End.
     * 12-16 19:32:26.258  21394-21444/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-4 End.
     * 12-16 19:32:26.258  21394-21441/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:32:26.258  21394-21445/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-5 End.
     * 12-16 19:32:26.258  21394-21446/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-6 End.
     * 12-16 19:32:26.258  21394-21447/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-7 End.
     * 12-16 19:32:26.258  21394-21448/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-8 End.
     * 12-16 19:32:26.258  21394-21449/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-9 End.
     * 12-16 19:32:26.258  21394-21450/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-10 End.
     * 12-16 19:32:26.258  21394-21394/sabria.demo.threadpractice I/MainActivity﹕ Finished all threads
     */
    public void startNewCacheThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            WorkRunnable workRunnable = new WorkRunnable(i + "");
            pool.execute(workRunnable);
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
        }
        Utils.log("Finished all threads");
    }

    /**
     * 12-16 19:45:25.008  28447-28486/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 0
     * 12-16 19:45:25.018  28447-28487/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 Start. Command = 2
     * 12-16 19:45:28.008  28447-28486/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:45:28.008  28447-28486/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 4
     * 12-16 19:45:28.028  28447-28487/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 End.
     * 12-16 19:45:28.028  28447-28487/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 Start. Command = 6
     * 12-16 19:45:31.008  28447-28486/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:45:31.008  28447-28486/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 8
     * 12-16 19:45:31.028  28447-28487/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 End.
     * 12-16 19:45:31.028  28447-28487/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 Start. Command = 1
     * 12-16 19:45:34.008  28447-28486/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:45:34.008  28447-28486/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 3
     * 12-16 19:45:34.028  28447-28487/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 End.
     * 12-16 19:45:34.028  28447-28487/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 Start. Command = 5
     * 12-16 19:45:37.008  28447-28486/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:45:37.018  28447-28486/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 7
     * 12-16 19:45:37.028  28447-28487/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 End.
     * 12-16 19:45:37.028  28447-28487/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 Start. Command = 9
     * 12-16 19:45:40.018  28447-28486/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:45:40.028  28447-28487/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-2 End.
     * 12-16 19:45:40.028  28447-28447/sabria.demo.threadpractice I/MainActivity﹕ Finished all threads
     */
    public void startScheduledThreadPool() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 10; i++) {
            WorkRunnable workRunnable = new WorkRunnable(i + "");
            if (i % 2 == 0) {
                pool.execute(workRunnable);
            } else {
                pool.schedule(workRunnable, 10, TimeUnit.MILLISECONDS);
            }
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
        }
        Utils.log("Finished all threads");
    }

    /**
     * 12-16 19:50:02.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 0
     * 12-16 19:50:05.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:50:05.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 2
     * 12-16 19:50:08.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:50:08.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 4
     * 12-16 19:50:11.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:50:11.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 6
     * 12-16 19:50:14.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:50:14.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 8
     * 12-16 19:50:17.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:50:17.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 1
     * 12-16 19:50:20.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:50:20.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 3
     * 12-16 19:50:23.358  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:50:23.368  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 5
     * 12-16 19:50:26.368  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:50:26.368  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 7
     * 12-16 19:50:29.368  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:50:29.368  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 Start. Command = 9
     * 12-16 19:50:32.368  30772-30950/sabria.demo.threadpractice I/MainActivity﹕ pool-1-thread-1 End.
     * 12-16 19:50:32.368  30772-30772/sabria.demo.threadpractice I/MainActivity﹕ Finished all threads
     */
    public void startSingleScheduledThreadPool() {
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 10; i++) {
            WorkRunnable workRunnable = new WorkRunnable(i + "");
            if (i % 2 == 0) {
                pool.execute(workRunnable);
            } else {
                pool.schedule(workRunnable, 10, TimeUnit.MILLISECONDS);
            }
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
        }
        Utils.log("Finished all threads");
    }


    public void startMyCustomerThreadPool() {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(20);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 2, TimeUnit.MILLISECONDS, queue);
        for (int i = 0; i < 100; i++) {
            WorkRunnable workRunnable = new WorkRunnable(i + "");
            pool.execute(workRunnable);
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
        }
        Utils.log("Finished all threads");
    }


    /**
     *
     12-17 09:55:31.238  14285-14285/sabria.demo.threadpractice I/MainActivity﹕ CPU_COUNT=4
     12-17 09:55:31.238  14285-14347/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #1 Start. Command = 0
     12-17 09:55:31.238  14285-14348/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #2 Start. Command = 1
     12-17 09:55:31.238  14285-14349/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #3 Start. Command = 2
     12-17 09:55:31.238  14285-14350/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #4 Start. Command = 3
     12-17 09:55:31.238  14285-14351/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #5 Start. Command = 4
        ----------
     12-17 09:55:34.248  14285-14347/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #1 End.
     12-17 09:55:34.248  14285-14348/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #2 End.
     12-17 09:55:34.248  14285-14349/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #3 End.
     12-17 09:55:34.248  14285-14347/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #1 Start. Command = 5
     12-17 09:55:34.248  14285-14350/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #4 End.
     12-17 09:55:34.248  14285-14349/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #3 Start. Command = 6
     12-17 09:55:34.248  14285-14350/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #4 Start. Command = 7
     12-17 09:55:34.248  14285-14351/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #5 End.
     12-17 09:55:34.248  14285-14351/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #5 Start. Command = 8
     12-17 09:55:34.248  14285-14348/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #2 Start. Command = 9
     12-17 09:55:37.248  14285-14349/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #3 End.
     12-17 09:55:37.248  14285-14350/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #4 End.
     12-17 09:55:37.248  14285-14347/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #1 End.
     12-17 09:55:37.248  14285-14351/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #5 End.
     12-17 09:55:37.248  14285-14348/sabria.demo.threadpractice I/MainActivity﹕ AsyncTask #2 End.
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 1;
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<>(128);
    public void startAsyncTaskThreadPool() {

        Utils.log("CPU_COUNT="+CPU_COUNT);//4

        Executor THREAD_POOL_EXECUTOR
                = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
                TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

        for (int i = 0; i < 10; i++) {
            WorkRunnable workRunnable = new WorkRunnable(i + "");
            THREAD_POOL_EXECUTOR.execute(workRunnable);
        }

    }


    //-----------------------------------
    public void startAsyncThreadPoolSumbit()  {
        ExecutorService THREAD_POOL_EXECUTOR
                = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
                TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

        for (int i = 0; i < 10; i++) {
            //the workRunnable not implements callable so return null
            WorkRunnable workRunnable = new WorkRunnable(i + "");
            Future<?> future = THREAD_POOL_EXECUTOR.submit(workRunnable);
            //
            try {
                Utils.log("future=" +future.get());
            } catch (Exception e) {
                e.printStackTrace();
                Utils.log("Exception");
            }

        }
    }


}
