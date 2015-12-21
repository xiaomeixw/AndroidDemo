package sabria.demo.tinybus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

import de.greenrobot.event.EventBus;
import de.halfbit.tinybus.TinyBus;
import sabria.demo.tinybus.event.ProcessDataEvent;

public class MainActivity extends Activity {

    TinyBus tinyBus;
    Bus ottoBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tinyBus = TinyBus.from(this);
        ottoBus = new Bus(ThreadEnforcer.ANY);
        EventBus.getDefault().register(this);

        findViewById(R.id.gotoSecond).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });



    }

    @Subscribe
    public void onProcessDataEvent(ProcessDataEvent data) {
        String msg = "MainActivity-TinyBus-onEventMainThread收到了消息：" + data.getTotalSteps();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    public void onEventMainThread(ProcessDataEvent data) {
        String msg = "onEventMainThread收到了消息：" + data.getTotalSteps();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        tinyBus.register(this);
        ottoBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        tinyBus.unregister(this);
        ottoBus.unregister(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
