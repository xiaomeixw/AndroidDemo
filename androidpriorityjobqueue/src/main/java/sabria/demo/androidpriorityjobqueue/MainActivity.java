package sabria.demo.androidpriorityjobqueue;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.path.android.jobqueue.JobManager;

import de.greenrobot.event.EventBus;
import sabria.demo.androidpriorityjobqueue.event.PostedEvent;

public class MainActivity extends AppCompatActivity {

    JobManager jobManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        jobManager = MyApplication.getInstance().getJobManager();

        findViewById(R.id.btStartJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobManager.addJobInBackground(new MyJob("job1"));
                jobManager.addJobInBackground(new MyJob("job2"));
                jobManager.addJobInBackground(new MyJob("job3"));
                jobManager.addJobInBackground(new MyJob("job4"));
                jobManager.addJobInBackground(new MyJob("job5"));
                jobManager.addJobInBackground(new MyJob("job6"));
                jobManager.addJobInBackground(new MyJob("job7"));
                jobManager.addJobInBackground(new MyJob("job8"));
                jobManager.addJobInBackground(new MyJob("job9"));
                jobManager.addJobInBackground(new MyJob("job10"));
                jobManager.addJobInBackground(new MyJob("job11"));
                jobManager.addJobInBackground(new MyJob("job12"));
                jobManager.addJobInBackground(new MyJob("job13"));

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        jobManager.addJobInBackground(new MyJob("job14"));
                    }
                }, 500);


            }
        });

    }


    public void onEventMainThread(PostedEvent event) {
        //we could just add this to top or replace element instead of refreshing whole list
        onUpdateEvent(event.getText());
    }

    private void onUpdateEvent(String text) {
        Toast.makeText(MainActivity.this,"------------:"+text,Toast.LENGTH_LONG).show();
        Log.i("----",text);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
