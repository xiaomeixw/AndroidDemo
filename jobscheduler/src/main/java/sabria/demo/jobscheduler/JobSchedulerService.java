package sabria.demo.jobscheduler;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-09  16:09
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
@TargetApi(21)
public class JobSchedulerService extends JobService {

    private Handler mJobHanlder = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "JobService task running", Toast.LENGTH_SHORT).show();
            jobFinished((JobParameters) msg.obj, false);
            return true;
        }
    });

    //4. two method must Override
    @Override
    public boolean onStartJob(JobParameters params) {
        mJobHanlder.sendMessage(Message.obtain(mJobHanlder, 1, params));
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mJobHanlder.removeMessages(1);
        return false;
    }


}
