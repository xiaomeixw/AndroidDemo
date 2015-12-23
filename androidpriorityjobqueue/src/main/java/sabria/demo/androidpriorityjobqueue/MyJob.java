package sabria.demo.androidpriorityjobqueue;

import android.util.Log;

import com.path.android.jobqueue.Params;

import de.greenrobot.event.EventBus;
import sabria.demo.androidpriorityjobqueue.event.PostedEvent;


/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-23  16:47
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class MyJob extends com.path.android.jobqueue.Job {

    private static final String TAG = "MyJob";

    public static final int PRIORITY = 1;
    private String text;


    public MyJob(String text) {
        // This job requires network connectivity,
        // and should be persisted in case the application exits before job is completed.
        super(new Params(PRIORITY).requireNetwork().persist());
        this.text=text;
    }

    /**
     * Job has been saved to disk.
     * This is a good place to dispatch a UI event to indicate the job will eventually run.
     * In this example, it would be good to update the UI with the newly posted tweet.
     */
    @Override
    public void onAdded() {
        Log.d(TAG,"onAdded"+text);
        EventBus.getDefault().post(new PostedEvent("onAdded"+text));
    }

    /**
     * Job logic goes here. In this example, the network call to post to Twitter is done here.
     * @throws Throwable
     */
    @Override
    public void onRun() throws Throwable {
        Log.d(TAG,"onRun"+text);

        EventBus.getDefault().post(new PostedEvent("onRun"+text));
    }

    /**
     * An error occurred in onRun.
     * Return value determines whether this job should retry or cancel. You can further
     * specifcy a backoff strategy or change the job's priority. You can also apply the
     * delay to the whole group to preserve jobs' running order.
     */
    @Override
    protected void onCancel() {
        Log.d(TAG, "onCancel");
        //send delete event

    }
}
