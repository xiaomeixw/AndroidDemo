package sabria.trigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.airk.trigger.Action;
import com.github.airk.trigger.Condition;
import com.github.airk.trigger.ContextAction;
import com.github.airk.trigger.Job;
import com.github.airk.trigger.ThreadSpace;
import com.github.airk.trigger.Trigger;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-09  17:28
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class TriggerActivity extends Activity {

    final String CUSTOM_COND1 = "trigger.sample.job_1";
    final String CUSTOM_COND2 = "trigger.sample.job_2";
    final String CUSTOM_COND3 = "trigger.sample.job_3";

    Trigger trigger;

    Button btJob1;
    Button btJob2;
    Button btJob3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btJob1 = (Button) findViewById(R.id.btJob1);
        btJob2 = (Button) findViewById(R.id.btJob2);
        btJob3 = (Button) findViewById(R.id.btJob3);

        //1. get Trigger Instance
        trigger = Trigger.getInstance(TriggerActivity.this);
        trigger.stopAndReset();

        //2.new A Job
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


        Job job2 = new Job(new ContextAction() {
            @Override
            protected void act(Context context) {
                Toast.makeText(context, context.toString(), Toast.LENGTH_SHORT).show();
            }
        }).attachOn(ThreadSpace.MAIN)
                .repeat()
                .withExtra(new Condition() {
                    @Override
                    public String[] getAction() {
                        return new String[]{CUSTOM_COND2};
                    }
                });


        Job job3 = new Job(new ContextAction() {
            @Override
            protected void act(Context context) {
                Toast.makeText(context, "Charging with custom...", Toast.LENGTH_SHORT).show();
            }
        }).attachOn(ThreadSpace.MAIN)
                .needCharging(true)
                .repeat()
                .withExtra(new Condition() {
                    @Override
                    public String[] getAction() {
                        return new String[]{CUSTOM_COND3};
                    }
                });

        Job persistAfterRebootJob = new Job(true, new PersistAfterRebootWithChargingAction())
                .attachOn(ThreadSpace.MAIN)
                .repeat(71 * 60 * 1000)
                .needCharging(true);

        //3.schedule Job
        trigger.schedule(job1, job2, job3, persistAfterRebootJob);

        btJob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent(CUSTOM_COND1));
            }
        });

        btJob2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent(CUSTOM_COND2));
            }
        });

        btJob3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent(CUSTOM_COND3));
            }
        });


    }


    public static class PersistAfterRebootWithChargingAction extends ContextAction {

        @Override
        protected void act(Context context) {
            Toast.makeText(context, "Charging...(from persist Job after reboot)", Toast.LENGTH_SHORT).show();
        }
    }

}
