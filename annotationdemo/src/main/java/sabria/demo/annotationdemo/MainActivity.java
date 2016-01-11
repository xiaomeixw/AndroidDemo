package sabria.demo.annotationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.lang.reflect.Method;

import sabria.demo.annotationdemo.runtime.PasswordUtils;
import sabria.demo.annotationdemo.runtime.UserInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectRunTime();
    }

    private void injectRunTime() {
        //拿被注解方法上的
        for (Method m : PasswordUtils.class.getDeclaredMethods()) {
            boolean hasAnnotation = m.isAnnotationPresent(UserInfo.class);
            /**
             * 判断方法中是否有指定注解类型的注解
             */
            if (hasAnnotation) {
                //拿到注解类
                UserInfo annotation = m.getAnnotation(UserInfo.class);
                if (annotation != null) {
                    //这样就拿到了定义的值:47
                    String id = annotation.id();
                    String description = annotation.description();
                    Toast.makeText(MainActivity.this,"id="+id+"/description="+description,Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
