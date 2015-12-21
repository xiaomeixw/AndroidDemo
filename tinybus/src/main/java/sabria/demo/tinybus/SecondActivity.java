package sabria.demo.tinybus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

import de.halfbit.tinybus.TinyBus;
import sabria.demo.tinybus.event.ProcessDataEvent;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-21  16:36
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class SecondActivity extends Activity {

    TinyBus tinyBus;
    Bus ottoBus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        tinyBus = TinyBus.from(this);
        ottoBus = new Bus(ThreadEnforcer.ANY);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EventBus.getDefault().post(new ProcessDataEvent(20));
                //tinyBus.post(new ProcessDataEvent(20));
                ottoBus.post(new ProcessDataEvent(20));
            }
        });


    }



    @Subscribe
    public void onProcessDataEvent(ProcessDataEvent data) {
        String msg = "SecondActivity-TinyBus-onEventMainThread收到了消息：" + data.getTotalSteps();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        tinyBus.register(this);
        ottoBus.register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        tinyBus.unregister(this);
        ottoBus.unregister(this);

    }





    /**/




}
