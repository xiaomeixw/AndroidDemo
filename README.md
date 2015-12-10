# AndroidDemo
Write some android demo code to Explore android new technology and famous libraries...

## Explore JobScheduler on Android L ##
### Screen

<img src="http://i.imgur.com/XrcdLw4.png" width="45%">

### How to Use

notice: 

- _1.Only supprot API-21._
- _2.should state "android.permission.BIND_JOB_SERVICE" in Manifest._

1.Get JobScheduler SystemService

	JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

2.Set JobInfo builder

	JobInfo.Builder builder =  new JobInfo.Builder(1,
                        new ComponentName(getPackageName(), JobSchedulerService.class.getName()));
	builder.setPeriodic( 3000 );

3.extends JobService，achieve onStartJob() & onStopJob().

	public class JobSchedulerService extends JobService {
	
	    @Override
	    public boolean onStartJob(JobParameters params) {
	        mJobHanlder.sendMessage(Message.obtain(mJobHanlder, 1, params));
	        return false;
	    };
	
	    @Override
	    public boolean onStopJob(JobParameters params) {
	        mJobHanlder.removeMessages(1);
	        return false;
	    }
	
	
	}

4.Cancel Job

	jobScheduler.cancelAll();

### Want to Know more

[JobScheduler is a good feature of Android Lollipop, but what a pity only Lollipop](https://github.com/airk000/Trigger)

[A backport of Android Lollipop's JobScheduler to api 10+](https://github.com/evant/JobSchedulerCompat)

[Using the JobScheduler API on Android Lollipop](https://github.com/tutsplus/Android-JobSchedulerAPI)

[Implementing GCM Network Manager on Android](https://developers.google.com/cloud-messaging/network-manager)


