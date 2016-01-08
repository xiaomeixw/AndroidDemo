package sabria.demo.asyncservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.joanzapata.android.asyncservice.api.annotation.InjectService;
import com.joanzapata.android.asyncservice.api.annotation.OnMessage;
import com.joanzapata.android.asyncservice.api.internal.AsyncService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @InjectService
    public SnakeService service;
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncService.inject(this);

        tvText= (TextView) findViewById(R.id.tvText);

        findViewById(R.id.btGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //service.getUserAsyncWithCache(3L);
                service.getHui(3L);
            }
        });
    }


    /*
        The return type of getUserAsyncWithCache() is UserEvent, so the result will come here.
        As we used @Cached on getUserAsyncWithCache(), the result will probably come twice :
        the first result will be the cached value, the second one will be the up-to-date result.
        We will never receive a cached result AFTER a real result, even if we call getUserAsyncWithCache()
        multiple times.
     */
    @OnMessage
    public void onUserFetched(UserEvent e) {
        tvText.setText(e.getName() + " " + e.getAge());
    }

    @OnMessage
    public void onHuiFetched(HuiEvent e) {
        tvText.setText(e.getJson());
    }

    /*
        Sometimes we just need to know when the result arrives,
        but don't actually use it. So just use a no-arg method
        with the event type in the annotation.
     */
    @OnMessage({UserEvent.class, UserEvent.class})
    public void onUserFetched() {
        Log.i(TAG, "User fetched !");
    }

    @OnMessage(UserEvent.class)
    public void onUserFetchedTest() {
        Log.i(TAG, "User fetched !");
    }

    /*
        By default we only receive the results of requests made
         on the injected service of this class. It means that if
         you have two fragments using a different service, and
         calling the same method on the service, they won't receive
         others response.
         That fits most case, but if for some reason you want
         to listen to ALL events of a certain type, no matter
         which service the method was called on, you can use
         from = ALL.
     */
    @OnMessage(from = OnMessage.Sender.ALL)
    public void onUserFetchedFromAnywhere(UserEvent e) {
        Log.i(TAG, "User fetched from anywhere !");
    }



}
