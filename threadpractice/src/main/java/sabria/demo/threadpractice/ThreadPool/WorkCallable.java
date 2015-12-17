package sabria.demo.threadpractice.ThreadPool;

import java.util.concurrent.Callable;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-17  11:26
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class WorkCallable implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = sum + i;
        }
        return sum;
    }


}
