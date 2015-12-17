package sabria.demo.threadpractice.lockobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sabria.demo.threadpractice.Utils;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-16  18:01
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class LockObject {

    private Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();


    /**
     * 12-16 18:16:40.048  16129-16129/sabria.demo.threadpractice I/MainActivity﹕ Time taken: 4811
     * 12-16 18:16:40.048  16129-16129/sabria.demo.threadpractice I/MainActivity﹕ List1: 2000; List2: 2000
     */
    public void createThread(){

        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        thread1.start();


        try {
            thread1.join();
            thread2.start();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        Utils.log("LockObject---Time taken: " + (end - start));

        Utils.log("LockObject---List1: " + list1.size() + "; List2: " + list2.size());

    }

    private void process() {
        for (int i = 0; i < 1000; i++) {
            step1();
            step2();
        }
    }

    private void step1() {
        try {
            //do your work here
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }

    private void step2() {
        try {
            //do your work here
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }



}
