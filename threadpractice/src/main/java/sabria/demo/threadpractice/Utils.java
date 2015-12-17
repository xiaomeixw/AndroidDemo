package sabria.demo.threadpractice;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-16  10:36
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class Utils {

    public static void runContent(Activity context) {
        for (int i = 0; i < 5; i++){
            Utils.showToast(context, "ThreadName = " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void showToast(final Activity context,final String text){

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void log(String log){
        Log.i(MainActivity.TAG, log);
    }




}
