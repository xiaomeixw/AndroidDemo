package sabria.demo.threadpractice.ProducerConsumer.waitnotify;

import java.util.PriorityQueue;

import sabria.demo.threadpractice.Utils;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-17  15:44
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class waitNotifyPC {

    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

    /**
     12-17 16:59:28.929    4438-4497/sabria.demo.threadpractice I/MainActivity﹕ 队列空，等待数据
     12-17 16:59:28.929    4438-4495/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：9
     12-17 16:59:28.929    4438-4497/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余0个元素
     12-17 16:59:28.929    4438-4497/sabria.demo.threadpractice I/MainActivity﹕ 队列空，等待数据
     12-17 16:59:28.929    4438-4495/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：9
     12-17 16:59:28.929    4438-4495/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：8
     12-17 16:59:28.929    4438-4495/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：7
     12-17 16:59:28.929    4438-4495/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：6
     12-17 16:59:28.929    4438-4495/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：5
     12-17 16:59:28.929    4438-4495/sabria.demo.threadpractice I/MainActivity﹕ 向队列取中插入一个元素，队列剩余空间：4
     12-17 16:59:28.929    4438-4497/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余5个元素
     12-17 16:59:28.929    4438-4497/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余4个元素
     12-17 16:59:28.929    4438-4497/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余3个元素
     12-17 16:59:28.929    4438-4497/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余2个元素
     12-17 16:59:28.929    4438-4497/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余1个元素
     12-17 16:59:28.929    4438-4497/sabria.demo.threadpractice I/MainActivity﹕ 从队列取走一个元素，队列剩余
     */
    public void startwaitNoticyPC(){
        new Producer().start();
        new Consumer().start();
    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true){
                synchronized (queue){
                    while (queue.size()==0){
                        try {
                            Utils.log("队列空，等待数据");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    //每次都移走首元素
                    queue.poll();
                    queue.notify();
                    Utils.log("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
                }
            }
        }
    }

    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true){
                synchronized (queue){
                    while (queue.size() == queueSize){
                        try {
                            Utils.log("队列满，等待有空余空间");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.offer(1);//每次插入一个元素
                    queue.notify();
                    Utils.log("向队列取中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));

                }
            }
        }
    }




}
