package sabria.demo.asyncservice;

import android.content.Context;
import android.widget.Toast;

import com.joanzapata.android.asyncservice.api.annotation.ApplicationContext;
import com.joanzapata.android.asyncservice.api.annotation.AsyncService;
import com.joanzapata.android.asyncservice.api.annotation.CacheThenCall;
import com.joanzapata.android.asyncservice.api.annotation.ErrorManagement;
import com.joanzapata.android.asyncservice.api.annotation.Mapping;
import com.joanzapata.android.asyncservice.api.annotation.Null;
import com.joanzapata.android.asyncservice.api.annotation.Ui;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2016-01-08  13:53
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
@AsyncService
public class SnakeService {

    @ApplicationContext
    static Context applicationContext;

    @Null(NoUserEvent.class)
    @ErrorManagement(@Mapping(on = 200, send = DummyErrorMessage.class))
    public UserEvent getUserAsync(Long id) {
        return new UserEvent(id, "Joan", 25);
    }

    @CacheThenCall
    public UserEvent getUserAsyncWithCache(Long id) {
        sleep();
        displayMessage("This is a toast displayed from the DemoService.");
        return new UserEvent(id, "Joan", 25);
    }


    static OkHttpClient client = new OkHttpClient();

    @CacheThenCall
    public HuiEvent getHui(Long id) {
        sleep();

        displayMessage("getHuiData");

        //网络请求
        Request request = new Request.Builder()
                .url("https://api.github.com/")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String data =  response.body().string();
            return new HuiEvent(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Ui
    protected void displayMessage(String message) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show();
    }

    // Private methods are not overridden, so you can call them directly.
    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
