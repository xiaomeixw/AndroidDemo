package sabria.demo.rxjava;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button btFun1;
    Button btFun2;
    Button btFun3;
    Button btFun4;
    Button btFun5;
    Button btFun6;
    ImageView ivImage;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btFun1 = (Button) findViewById(R.id.btFun1);
        btFun2 = (Button) findViewById(R.id.btFun2);
        btFun3 = (Button) findViewById(R.id.btFun3);
        btFun4 = (Button) findViewById(R.id.btFun4);
        btFun5 = (Button) findViewById(R.id.btFun5);
        btFun6 = (Button) findViewById(R.id.btFun6);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        btFun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxFunder1();
            }
        });

        btFun2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxFunder2();
            }
        });

        btFun3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rxFunder3();
            }
        });

        btFun4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxFunder4();
            }
        });

        btFun5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxFunder5();
            }
        });

        btFun6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxFunder6();
            }
        });

        
    }




    private void rxFunder1() {

        //1.创建被观察者
        //send three times
        Observable<String> observable = Observable.just("Hello", "RxJava", "go...go...go!");

        //2.创建观察者
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            /**
             * onNext will call three times!
             * @param o
             */
            @Override
            public void onNext(String o) {
                /**
                 *  12-11 18:54:45.829  14880-14880/sabria.demo.rxjava D/MainActivity﹕ onNextHello
                 *  12-11 18:54:45.829  14880-14880/sabria.demo.rxjava D/MainActivity﹕ onNextRxJava
                 *  12-11 18:54:45.829  14880-14880/sabria.demo.rxjava D/MainActivity﹕ onNextgo...go...go!
                 *  12-11 18:54:45.829  14880-14880/sabria.demo.rxjava D/MainActivity﹕ onCompleted
                 */
                Log.d(TAG, "onNext"+o);
            }
        };

        //3.注册被观察者
        observable.subscribe(observer);

    }


    private void rxFunder2() {

        //1.创建被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                /**
                 * 调用观察者 三次onNext 一次onCompleted OR 一次onError (onCompleted和onError两者只会调用任意一个)
                 * 你自己可以随意定义要调用几次
                 */
                subscriber.onNext("Hello");
                subscriber.onNext("RxJava");
                subscriber.onNext("go...go...go!");
                subscriber.onCompleted();
                subscriber.onError(new Throwable());
            }
        });


        //2.创建观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                /**
                 * 12-11 18:55:20.299  14880-14880/sabria.demo.rxjava D/MainActivity﹕ onNextHello
                 * 12-11 18:55:20.299  14880-14880/sabria.demo.rxjava D/MainActivity﹕ onNextRxJava
                 * 12-11 18:55:20.299  14880-14880/sabria.demo.rxjava D/MainActivity﹕ onNextgo...go...go!
                 * 12-11 18:55:20.299  14880-14880/sabria.demo.rxjava D/MainActivity﹕ onCompleted
                 */
                Log.d(TAG, "onNext"+s);
            }
        };

        //3.注册观察者
        observable.subscribe(observer);


    }


    private void rxFunder3() {

        //1.创建被观察者
        String[] words = {"Hello", "RxJava", "go...go...go!"};
        Observable<String> observable = Observable.from(words);


        //2.创建观察者
        Action1<String> onNextAction1 = new Action1<String>() {

            @Override
            public void call(String s) {
                Log.d(TAG, "onNext"+s);
            }
        };


        Action1<Throwable> onErrorAction1 = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "onError"+throwable);
            }
        };


        Action0 onCompletedaction0 = new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "onCompleted");

            }
        };



        //3.注册被观察者
        /**
         * 12-15 15:34:02.232  19872-19872/sabria.demo.rxjava D/MainActivity﹕ onNextHello
         * 12-15 15:34:02.232  19872-19872/sabria.demo.rxjava D/MainActivity﹕ onNextRxJava
         * 12-15 15:34:02.232  19872-19872/sabria.demo.rxjava D/MainActivity﹕ onNextgo...go...go!
         */
        //observable.subscribe(onNextAction1);

        /**
         * 12-15 15:34:57.692  20640-20640/sabria.demo.rxjava D/MainActivity﹕ onNextHello
         * 12-15 15:34:57.692  20640-20640/sabria.demo.rxjava D/MainActivity﹕ onNextRxJava
         * 12-15 15:34:57.692  20640-20640/sabria.demo.rxjava D/MainActivity﹕ onNextgo...go...go!
         */
        //observable.subscribe(onNextAction1,onErrorAction1);

        /**
         * 12-15 15:41:10.632  24321-24321/sabria.demo.rxjava D/MainActivity﹕ onNextHello
         * 12-15 15:41:10.632  24321-24321/sabria.demo.rxjava D/MainActivity﹕ onNextRxJava
         * 12-15 15:41:10.632  24321-24321/sabria.demo.rxjava D/MainActivity﹕ onNextgo...go...go!
         * 12-15 15:41:10.632  24321-24321/sabria.demo.rxjava D/MainActivity﹕ onCompleted
         */
        observable.subscribe(onNextAction1,onErrorAction1,onCompletedaction0);

        /**
         * there where be have an error notice: Found: 'rx.functions.Action0', required: 'rx.functions.Action1<java.lang.Throwable>'
         */
        //observable.subscribe(onNextAction1, onCompletedaction0);

    }

    private void rxFunder4() {

        /**
         * 代码风格变化:链式调用
         */
        String[] words = {"Hello", "RxJava", "go...go...go!"};

        Observable.from(words).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "onNext" + s);
            }
        });



        /**
         * 12-15 15:58:04.052      370-370/sabria.demo.rxjava D/MainActivity﹕ onNextHello
         * 12-15 15:58:04.052      370-370/sabria.demo.rxjava D/MainActivity﹕ onNextRxJava
         * 12-15 15:58:04.052      370-370/sabria.demo.rxjava D/MainActivity﹕ onNextgo...go...go!
         * 12-15 15:58:04.052      370-370/sabria.demo.rxjava D/MainActivity﹕ onCompleted
         */
        Observable.from(words).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                /**
                 * 12-15 15:55:01.262  31171-31171/sabria.demo.rxjava D/MainActivity﹕ onNextHello
                 * 12-15 15:55:01.262  31171-31171/sabria.demo.rxjava D/MainActivity﹕ onNextRxJava
                 * 12-15 15:55:01.262  31171-31171/sabria.demo.rxjava D/MainActivity﹕ onNextgo...go...go!
                 */
                Log.d(TAG, "onNext" + s);
            }
        },new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "onError"+throwable);
            }
        },new Action0(){
            @Override
            public void call() {
                Log.d(TAG, "onCompleted");
        }
        });


    }




    private void rxFunder5() {

        final int ic_launcher = R.mipmap.ic_launcher;
        Observable.create(new Observable.OnSubscribe<Drawable>() {

            /**
             * Not MainLooper
             * @param subscriber
             */
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Log.d(TAG, "Observable");
                Drawable drawable = getResources().getDrawable(ic_launcher);

                subscriber.onNext(drawable);
                subscriber.onCompleted();

                LogMainLooper("Observable.create");

            }
        })

                .subscribeOn(Schedulers.io())   // subscribe() 发生在 IO 线程
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        /**
                         * MainLooper
                         */
                        LogMainLooper("doOnSubscribe");
                        progressBar.setVisibility(View.VISIBLE);
                    }
                })

                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程

                /**
                * MainLooper
                */
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onCompleted() {
                        LogMainLooper("onCompleted");
                        Log.d(TAG, "onCompleted");
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogMainLooper("onError");
                        Log.d(TAG, "Throwable" + e);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        LogMainLooper("onNext");
                        Log.d(TAG, "onNext" + drawable);
                        ivImage.setImageDrawable(drawable);
                    }
                });







    }


    private void rxFunder6() {
        final int ic_launcher = R.mipmap.ic_launcher;
        Observable.just(ic_launcher)
                //一对一转化
                .map(new Func1<Integer, Drawable>() {
                    @Override
                    public Drawable call(Integer integer) {
                        return getResources().getDrawable(ic_launcher);
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onCompleted() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        ivImage.setImageDrawable(drawable);
                    }
                });



    }


    public void LogMainLooper(String content){
        if(Looper.myLooper() == Looper.getMainLooper()){
            Toast.makeText(MainActivity.this, content+"----isMainThread: =" + true, Toast.LENGTH_SHORT).show();
        }
    }


}
