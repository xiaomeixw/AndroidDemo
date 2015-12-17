package sabria.demo.threadpractice.ThreadPool;

import sabria.demo.threadpractice.Utils;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-16  18:50
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class WorkRunnable implements Runnable {

    private String command;

    public WorkRunnable(String s){
        this.command=s;
    }

    @Override
    public void run() {
        Utils.log(Thread.currentThread().getName() + " Start. Command = " + command);
        processCommand();
        Utils.log(Thread.currentThread().getName() + " End.");
    }

    private void processCommand() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return this.command;
    }

}
