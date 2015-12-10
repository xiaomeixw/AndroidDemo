# AndroidDemo
Write some Android demo code to Explore Android New technology , Black technology and Famous libraries...



## Quick Look:

* [Explore JobScheduler on Android L](#一-explore-jobscheduler-on-android-l)
* [Explore JobScheduler Support Library : Trigger](#二-explore-jobscheduler-support-library--trigger)
* [Explore JobScheduler Support Library : JobSchedulerCompat](#三-explore-jobscheduler-support-library--jobschedulercompat)


## APK Resourse

[APK : Explore JobScheduler on Android L](https://github.com/xiaomeixw/AndroidDemo/blob/master/apk/jobscheduler-debug.apk)

[APK : Explore JobScheduler Support Library : Trigger](https://github.com/xiaomeixw/AndroidDemo/blob/master/apk/trigger-debug.apk)

[APK : Explore JobScheduler Support Library : JobSchedulerCompat](https://github.com/xiaomeixw/AndroidDemo/blob/master/apk/jobschedulercompat-debug.apk)

## 一. Explore JobScheduler on Android L
### Screen

<img src="http://i.imgur.com/XrcdLw4.png" width="35%">

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



## 二. Explore JobScheduler Support Library : Trigger ##

[https://github.com/airk000/Trigger](https://github.com/airk000/Trigger "https://github.com/airk000/Trigger")

### Screen

<img src="http://i.imgur.com/JiWAMAs.png" width="35%">

### How to Use

notice: 

- _1.supprot API14._
- _2.should state there permission in Manifest._



> permission : ACCESS_NETWORK_STATE & WAKE_LOCK & RECEIVE_BOOT_COMPLETED
> 
> service : android:name="com.github.airk.trigger.TriggerLoop"
> 
> receiver : com.github.airk.trigger.PersistReceiver

1.Get Trigger Instance

	 Trigger trigger = Trigger.getInstance(TriggerActivity.this);

2.New A Job

	Job job1 = new Job(new Action() {
            @Override
            protected void act() {
                Toast.makeText(TriggerActivity.this, "btJob1", Toast.LENGTH_SHORT).show();
            }
        }).attachOn(ThreadSpace.BACKGROUND)
                .repeat()
                .withExtra(new Condition() {
                    @Override
                    public String[] getAction() {
                        return new String[]{CUSTOM_COND1};
                    }
                });

3.Schedule Job

	trigger.schedule(job1, job2, job3, persistAfterRebootJob);


### Want to Know more

[https://github.com/airk000](https://github.com/airk000)


## 三. Explore JobScheduler Support Library : JobSchedulerCompat ##

[https://github.com/evant/JobSchedulerCompat](https://github.com/evant/JobSchedulerCompat)

### Screen

<img src="http://i.imgur.com/468zXES.png" width="35%">

### How to Use

notice: 

- _1.supprot API10+._
- _2.should state there permission in Manifest._



> permission : ACCESS_NETWORK_STATE & WAKE_LOCK & RECEIVE_BOOT_COMPLETED
> 
> service : android:name=".service.TestJobService"
> 

1.Get JobScheduler Instance

	 JobScheduler jobScheduler=JobScheduler.getInstance(LowAPIJobSchedulerMainActivity.this);

2.Set JobInfo Builder

	JobInfo job = new JobInfo.Builder(1,
                        new ComponentName(getPackageName(), LowAPIJobSchedulerService.class.getName()))
                        .setMinimumLatency(1000)
                        .setOverrideDeadline(2000)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                        .setRequiresCharging(true)
                        .setExtras(extras).build();

    jobScheduler.schedule(job);

3.extends JobService,achieve onStartJob() & onStopJob()

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

4.Cancel Job

	jobScheduler.cancelAll();


### Want to Know more

[https://github.com/evant/JobSchedulerCompat/issues/34](https://github.com/evant/JobSchedulerCompat/issues/34)
