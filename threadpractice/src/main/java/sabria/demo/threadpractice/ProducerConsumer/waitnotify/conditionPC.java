package sabria.demo.threadpractice.ProducerConsumer.waitnotify;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import sabria.demo.threadpractice.Utils;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-17  16:29
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class conditionPC {

    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();


    /**
     *
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：9
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：8
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：7
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：6
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：5
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：4
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：3
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：2
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：1
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：0
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 队列满，等待有空余空间
     12-17 16:54:26.759      774-813/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余9个元素
     12-17 16:54:26.759      774-813/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余8个元素
     12-17 16:54:26.759      774-813/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余7个元素
     12-17 16:54:26.759      774-813/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余6个元素
     12-17 16:54:26.759      774-813/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余5个元素
     12-17 16:54:26.759      774-813/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余4个元素
     12-17 16:54:26.759      774-813/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余3个元素
     12-17 16:54:26.759      774-813/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余2个元素
     12-17 16:54:26.759      774-813/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余1个元素
     12-17 16:54:26.759      774-813/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余0个元素
     12-17 16:54:26.759      774-813/sabria.demo.threadpractice I/MainActivity﹕ 队列满，等待有空余空间
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：9
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：8
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：7
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：6
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：5
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：4
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：3
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：2
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：1
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：0
     12-17 16:54:26.759      774-812/sabria.demo.threadpractice I/MainActivity﹕ 队列满，等待有空余空间
     ......
     */
    public void startConditionPC() {
        new Product().start();

        new Consumer().start();

    }


    class Product extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();

                try {
                    while (queue.size() == queueSize) {

                        try {
                            Utils.log("队列满，等待有空余空间");
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    queue.offer(1);//每次插入一个元素
                    notEmpty.signal();//对应Object的notify()
                    Utils.log("向队列取中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
                } finally {
                    lock.unlock();
                }


            }


        }
    }

    class Consumer extends Thread{

        @Override
        public void run() {
            consume();
        }

        private void consume() {

            while (true){
                lock.lock();


                try {

                    //队列不为空,就取poll,为空就await
                    while (queue.size()==0){

                        try {

                            Utils.log("队列满，等待有空余空间");
                            notEmpty.await();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }

                    queue.poll();
                    notFull.signal();

                    Utils.log("从队列取走一个元素，队列剩余"+queue.size()+"个元素");

                }finally {

                    lock.unlock();

                }






            }



        }
    }


}
