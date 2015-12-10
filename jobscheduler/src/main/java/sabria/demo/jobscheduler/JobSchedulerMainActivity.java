package sabria.demo.jobscheduler;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-09  15:11
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
@TargetApi(21)
public class JobSchedulerMainActivity extends Activity {

    JobScheduler  jobScheduler;
    Button startJob;
    Button cancelJob;

    /**
     * Android JobScheduler should need API L
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startJob = (Button) findViewById(R.id.startJob);
        cancelJob = (Button) findViewById(R.id.cancelJob);

        //1.get JobScheduler SystemService
        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);


        startJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //2.set JobInfo
                 JobInfo.Builder builder =  new JobInfo.Builder(1,
                        new ComponentName(getPackageName(), JobSchedulerService.class.getName()));

                builder.setPeriodic( 3000 );

                if( jobScheduler.schedule( builder.build() ) <= 0 ) {
                    //If something goes wrong
                    Toast.makeText(JobSchedulerMainActivity.this,"jobScheduler - wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //
        cancelJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //3.stop job
                jobScheduler.cancelAll();
            }
        });



    }
}
