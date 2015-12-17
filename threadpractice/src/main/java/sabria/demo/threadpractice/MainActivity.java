package sabria.demo.threadpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import sabria.demo.threadpractice.ProducerConsumer.PCMechanism;
import sabria.demo.threadpractice.ThreadPool.FutureTaskPool;
import sabria.demo.threadpractice.lockobject.LockObject;
import sabria.demo.threadpractice.lockobject.LockObjectWith;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    Button btFun1;
    Button btFun2;
    Button btFun3;
    Button btFun4;
    Button btFun5;
    Button btFun6;
    Button btFun7;
    Button btFun8;
    Button btFun9;
    Button btFun10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClick();
    }

    private void initView() {
        btFun1 = (Button) findViewById(R.id.btFun1);
        btFun2 = (Button) findViewById(R.id.btFun2);
        btFun3 = (Button) findViewById(R.id.btFun3);
        btFun4 = (Button) findViewById(R.id.btFun4);
        btFun5 = (Button) findViewById(R.id.btFun5);
        btFun6 = (Button) findViewById(R.id.btFun6);
        btFun7 = (Button) findViewById(R.id.btFun7);
        btFun8 = (Button) findViewById(R.id.btFun8);
        btFun9 = (Button) findViewById(R.id.btFun9);
        btFun10 = (Button) findViewById(R.id.btFun10);
    }

    private void onClick() {
        btFun1.setOnClickListener(this);
        btFun2.setOnClickListener(this);
        btFun3.setOnClickListener(this);
        btFun4.setOnClickListener(this);
        btFun5.setOnClickListener(this);
        btFun6.setOnClickListener(this);
        btFun7.setOnClickListener(this);
        btFun8.setOnClickListener(this);
        btFun9.setOnClickListener(this);
        btFun10.setOnClickListener(this);
    }


    /**
     * new Thread(Runnable).start();
     */
    private void startThreadScheme1() {
        //匿名方案
        new Thread(new Runnable() {
            @Override
            public void run() {
                Utils.runContent(MainActivity.this);
            }
        }).start();
    }


    /**
     * new MyThread().start();
     * 和scheme1相同
     */
    private void startThreadScheme2() {
        //继承方案
        new RunnerThread().start();
    }

    /**
     * implements Runnable
     */
    private void startThreadScheme3() {
        new Thread(new RunnerRunnable()).start();
    }


    private void volatileScheme4() {
        if(!volatileKeyWord.isRunning){
            volatileKeyWord.isRunning=true;
        }
        new volatileKeyWord().start();
    }

    private void stopVolatileScheme4() {
        volatileKeyWord.shutDown();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btFun1:
                startThreadScheme1();
                break;

            case R.id.btFun2:
                startThreadScheme2();
                break;

            case R.id.btFun3:
                startThreadScheme3();
                break;

            case R.id.btFun4:
                volatileScheme4();
                break;

            case R.id.btFun5:
                stopVolatileScheme4();
                break;

            case R.id.btFun6:
                ThreadJoin();
                break;

            case R.id.btFun7:
                ObjectLock();
                break;

            case R.id.btFun8:
                ThreadPool();
                break;

            case R.id.btFun9:
                PCMe();
                break;

            case R.id.btFun10:
                waitNotify();
                break;
        }

    }




    static class volatileKeyWord extends Thread {

        private static volatile boolean isRunning = true;

        @Override
        public void run() {
            while (isRunning) {
                Log.i(TAG,"Running"+Thread.currentThread().getName());
                System.out.println("Running"+Thread.currentThread().getName());

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void shutDown() {
            isRunning = false;
        }
    }


    /**
     * !!! recommend
     */
    class RunnerRunnable implements Runnable {

        @Override
        public void run() {
            Utils.runContent(MainActivity.this);
        }
    }


    class RunnerThread extends Thread {
        @Override
        public void run() {
            Utils.runContent(MainActivity.this);
        }
    }

    //*********************************
    // Thread join
    //*********************************
    private int count =0;
    private void ThreadJoin() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0 ; i <100 ; i ++){
                    increment(Thread.currentThread().getName());
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0 ; i <100 ; i ++){
                    increment(Thread.currentThread().getName());
                }
            }
        });


        /**
         * without join
         12-16 17:01:38.308  11644-11699/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37177 and count is: 1
         12-16 17:01:38.308  11644-11699/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37177 and count is: 2
         12-16 17:01:38.308  11644-11698/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37176 and count is: 3
         12-16 17:01:38.308  11644-11698/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37176 and count is: 4
         12-16 17:01:38.308  11644-11698/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37176 and count is: 5
         12-16 17:01:38.308  11644-11698/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37176 and count is: 6
         12-16 17:01:38.308  11644-11698/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37176 and count is: 7
         12-16 17:01:38.308  11644-11698/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37176 and count is: 8
         12-16 17:01:38.308  11644-11698/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37176 and count is: 9
         12-16 17:01:38.308  11644-11698/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37176 and count is: 10
         12-16 17:01:38.308  11644-11698/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37176 and count is: 11
         12-16 17:01:38.308  11644-11698/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37176 and count is: 12
         12-16 17:01:38.308  11644-11699/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37177 and count is: 13
         12-16 17:01:38.308  11644-11699/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37177 and count is: 14
         12-16 17:01:38.308  11644-11699/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37177 and count is: 15
         12-16 17:01:38.308  11644-11699/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37177 and count is: 16
         12-16 17:01:38.318  11644-11699/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37177 and count is: 17
         12-16 17:01:38.318  11644-11699/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37177 and count is: 18
         12-16 17:01:38.318  11644-11699/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37177 and count is: 19
         12-16 17:01:38.318  11644-11699/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37177 and count is: 20
         */


        /**
         * with join
         *
         12-16 17:28:37.478  24623-24661/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37224 and count is: 1
         12-16 17:28:37.478  24623-24661/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37224 and count is: 2
         12-16 17:28:37.478  24623-24661/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37224 and count is: 3
         12-16 17:28:37.478  24623-24661/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37224 and count is: 4
         12-16 17:28:37.478  24623-24661/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37224 and count is: 5
         12-16 17:28:37.478  24623-24661/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37224 and count is: 6
         12-16 17:28:37.478  24623-24661/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37224 and count is: 7
         12-16 17:28:37.478  24623-24661/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37224 and count is: 8
         12-16 17:28:37.478  24623-24661/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37224 and count is: 9
         12-16 17:28:37.478  24623-24661/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37224 and count is: 10
         12-16 17:28:37.478  24623-24662/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37225 and count is: 11
         12-16 17:28:37.478  24623-24662/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37225 and count is: 12
         12-16 17:28:37.478  24623-24662/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37225 and count is: 13
         12-16 17:28:37.478  24623-24662/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37225 and count is: 14
         12-16 17:28:37.478  24623-24662/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37225 and count is: 15
         12-16 17:28:37.478  24623-24662/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37225 and count is: 16
         12-16 17:28:37.478  24623-24662/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37225 and count is: 17
         12-16 17:28:37.478  24623-24662/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37225 and count is: 18
         12-16 17:28:37.478  24623-24662/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37225 and count is: 19
         12-16 17:28:37.478  24623-24662/sabria.demo.threadpractice I/MainActivity﹕ Thread in Progress: Thread-37225 and count is: 20
         12-16 17:28:37.478  24623-24623/sabria.demo.threadpractice I/MainActivity﹕ Count is: 20
         */
        try {
            thread1.join();
            thread2.start();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Utils.log("Count is: " + count);

    }

    private void increment(String threadName) {
        count++;
        Utils.log("Thread in Progress: " + threadName + " and count is: " + count);
    }



    //*********************************
    // Object Lock
    //*********************************

    /**
     *
     12-16 18:23:32.508  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObject---Time taken: 4945
     12-16 18:23:32.508  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObject---List1: 2000; List2: 2000
     12-16 18:23:37.328  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObjectWith---Time taken: 4817
     12-16 18:23:37.328  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObjectWith---List1: 2000; List2: 2000
     12-16 18:23:47.368  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObject---Time taken: 4766
     12-16 18:23:47.368  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObject---List1: 2000; List2: 2000
     12-16 18:23:52.128  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObjectWith---Time taken: 4767
     12-16 18:23:52.128  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObjectWith---List1: 2000; List2: 2000
     12-16 18:24:00.628  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObject---Time taken: 4788
     12-16 18:24:00.628  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObject---List1: 2000; List2: 2000
     12-16 18:24:05.408  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObjectWith---Time taken: 4785
     12-16 18:24:05.408  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObjectWith---List1: 2000; List2: 2000
     12-16 18:24:14.308  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObject---Time taken: 4700
     12-16 18:24:14.308  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObject---List1: 2000; List2: 2000
     12-16 18:24:19.088  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObjectWith---Time taken: 4783
     12-16 18:24:19.088  20283-20283/sabria.demo.threadpractice I/MainActivity﹕ LockObjectWith---List1: 2000; List2: 2000
     */
    private void ObjectLock() {
        LockObject lockObject = new LockObject();
        LockObjectWith lockObjectWith = new LockObjectWith();
        lockObject.createThread();
        lockObjectWith.createThread();
    }

    //*********************************
    // Thread Pool
    //*********************************
    private void ThreadPool() {
        //new ThreadPool().startAsyncThreadPoolSumbit();
        new FutureTaskPool().startCallbackFutureTask();
    }

    //*********************************
    // producer-consumer
    //*********************************
    private void PCMe() {
        new PCMechanism().startPcM();
        //new waitNotifyPC().startwaitNoticyPC();
        //new conditionPC().startConditionPC();
    }


    //*********************************
    // wait-notify
    //*********************************
    private void waitNotify() {
    }



}
