package sabria.demo.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button btFun1;
    Button btFun2;
    Button btFun3;
    Button btFun4;
    Button btFun5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btFun1 = (Button) findViewById(R.id.btFun1);
        btFun2 = (Button) findViewById(R.id.btFun2);
        btFun3 = (Button) findViewById(R.id.btFun3);
        btFun4 = (Button) findViewById(R.id.btFun4);
        btFun5 = (Button) findViewById(R.id.btFun5);


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


}
