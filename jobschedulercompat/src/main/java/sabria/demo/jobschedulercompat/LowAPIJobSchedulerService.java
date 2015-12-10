package sabria.demo.jobschedulercompat;


import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-09  16:09
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class LowAPIJobSchedulerService extends JobService {

    private Handler mJobHanlder = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "JobService task running", Toast.LENGTH_SHORT).show();
            jobFinished((JobParameters) msg.obj, false);
            return true;
        }
    });

    /**
     * Start your job in a seperate thread
     * @param params
     * @return
     */
    @Override
    public boolean onStartJob(JobParameters params) {
        mJobHanlder.sendMessage(Message.obtain(mJobHanlder, 1, params));
        return false;
    }

    /**
     * Stop the running job, returing true if it needs to be recheduled.
     * @param params
     * @return
     */
    @Override
    public boolean onStopJob(JobParameters params) {
        mJobHanlder.removeMessages(1);
        return true;
    }


}
