package sabria.demo.threadpractice.ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import sabria.demo.threadpractice.Utils;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-17  11:22
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class FutureTaskPool {

    /**
     * 12-17 11:41:56.798  28450-28450/sabria.demo.threadpractice I/MainActivity﹕ Future运行结果:=45
     */
    public static void startCallbackFuture(){
        ExecutorService pool = Executors.newCachedThreadPool();
        WorkCallable workCallable = new WorkCallable();

        //for (int i = 0 ; i<10 ; i++){
            Future<Integer> future = pool.submit(workCallable);
            try {
                Integer integer = future.get();
                Utils.log("Future运行结果:="+integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        // }
        pool.shutdown();

    }

    /**
     * 12-17 11:47:28.508      904-904/sabria.demo.threadpractice I/MainActivity﹕ Future运行结果:=45
     */
    public static void startCallbackFutureTask(){
        ExecutorService pool = Executors.newCachedThreadPool();
        WorkCallable workCallable = new WorkCallable();

        FutureTask<Integer> integerFutureTask = new FutureTask<>(workCallable);
        pool.submit(integerFutureTask);
        pool.shutdown();

        try {
            Utils.log("Future运行结果:=" + integerFutureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
