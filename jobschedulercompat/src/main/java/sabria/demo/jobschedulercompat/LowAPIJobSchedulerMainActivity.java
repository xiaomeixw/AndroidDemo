package sabria.demo.jobschedulercompat;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.tatarka.support.job.JobInfo;
import me.tatarka.support.job.JobScheduler;
import me.tatarka.support.os.PersistableBundle;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-09  15:11
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class LowAPIJobSchedulerMainActivity extends Activity {

    JobScheduler jobScheduler;
    Button startJob;
    Button cancelJob;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startJob = (Button) findViewById(R.id.startJob);
        cancelJob = (Button) findViewById(R.id.cancelJob);

        //1.get JobScheduler Instance
        jobScheduler=JobScheduler.getInstance(LowAPIJobSchedulerMainActivity.this);

        final PersistableBundle extras = new PersistableBundle();
        extras.putString("key", "value");




        startJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //2.Set JobInfo Builder
                JobInfo job = new JobInfo.Builder(1,
                        new ComponentName(getPackageName(), LowAPIJobSchedulerService.class.getName()))
                        .setMinimumLatency(1000)
                        .setOverrideDeadline(2000)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                        .setRequiresCharging(true)
                        .setExtras(extras).build();

                jobScheduler.schedule(job);


            }
        });

        //
        cancelJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobScheduler.cancelAll();
            }
        });



    }
}
