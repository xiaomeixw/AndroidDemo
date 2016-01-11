package sabria.demo.annotationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import sabria.demo.annotationdemo.getanno.MyAnno;
import sabria.demo.annotationdemo.getanno.UserAnnotation;
import sabria.demo.annotationdemo.runtime.PasswordUtils;
import sabria.demo.annotationdemo.runtime.UserInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectRunTime();
        injectGetAnno();
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
                    say("id=" + id + "/description=" + description);
                }
            }
        }
    }

    private void injectGetAnno()  {

        try {
            parseTypeAnnotation();

            parseMethodAnnotation();

            parseConstructAnnotation();

            parseFieldAnnotation();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }




    /**
     * 01-11 15:24:57.414  25515-25515/sabria.demo.annotationdemo I/MainActivity﹕ type name = sabria.demo.annotationdemo.getanno.UserAnnotation  |  id = 0  |  name = type  | gid = class java.lang.Long
     * @throws ClassNotFoundException
     */
    private void parseTypeAnnotation() throws ClassNotFoundException{
        //先找到类
        Class clazz = Class.forName("sabria.demo.annotationdemo.getanno.UserAnnotation");
        //拿到注解器
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            //拿到类上的注解对象
            MyAnno testA = (MyAnno) annotation;
            say("type name = " + clazz.getName() + "  |  id = " + testA.id() + "  |  name = " + testA.name() + "  |  　　　　　　　　　　　　　　　　　　　　gid = " + testA.gid());
        }
    }

    /**
     * 01-11 15:24:57.424  25515-25515/sabria.demo.annotationdemo I/MainActivity﹕ method name = a  |  id = 3  |  description = public method  |  gid = class java.lang.Long
     01-11 15:24:57.434  25515-25515/sabria.demo.annotationdemo I/MainActivity﹕ method name = b  |  id = 4  |  description = protected method  |  gid = class java.lang.Long
     01-11 15:24:57.434  25515-25515/sabria.demo.annotationdemo I/MainActivity﹕ method name = c  |  id = 5  |  description = private method   |  gid = class java.lang.Long
     */
    private void parseMethodAnnotation() {
        //拿到UserAnnotation类中的所有方法
        Method[] methods = UserAnnotation.class.getDeclaredMethods();
        //一个一个方法判断
        for (Method method : methods) {
             /*
             * 判断方法中是否有指定注解类型的注解
             * 方法上面是否有@MyAnno
             */
            boolean hasAnnotation = method.isAnnotationPresent(MyAnno.class);
            if(hasAnnotation){
                //有的话,拿注解器
                MyAnno annotation = method.getAnnotation(MyAnno.class);
                say("method name = " + method.getName() + "  |  id = " +
                        annotation.id() + "  |  description = " + annotation.name() + "  |  gid = " + annotation.gid());
            }
        }
    }


    /**
     * 01-11 15:30:08.984  30046-30046/sabria.demo.annotationdemo I/MainActivity﹕ constructor = sabria.demo.annotationdemo.getanno.UserAnnotation   |   id = 2  |  description = construct  |   gid= class java.lang.Long
     */
    private void parseConstructAnnotation() {
        Constructor[] constructors = UserAnnotation.class.getConstructors();
        for (Constructor constructor : constructors) {
	            /*
	             * 判断构造方法中是否有指定注解类型的注解
	             */
            boolean hasAnnotation = constructor.isAnnotationPresent(MyAnno.class);
            if(hasAnnotation){
	                 /*
	                 * 根据注解类型返回方法的指定类型注解
	                 */
                MyAnno annotation = (MyAnno) constructor.getAnnotation(MyAnno.class);
                say("constructor = " + constructor.getName()
                        + "   |   id = " + annotation.id() + "  |  description = "
                        + annotation.name() + "  |   gid= "+annotation.gid());
            }
        }
    }


    /**
     * 01-11 15:30:08.994  30046-30046/sabria.demo.annotationdemo I/MainActivity﹕ Field = age  |  id = 1  |  description = param  |  gid= class java.lang.Long
     */
    private void parseFieldAnnotation() {

        //1.拿这个类上的所有字段
        Field[] fields = UserAnnotation.class.getDeclaredFields();
        for (Field field : fields) {
            //2.该字段上是否有注解
            boolean hasAnnotation = field.isAnnotationPresent(MyAnno.class);
            if(hasAnnotation){
                //3.拿到注解器对象
                MyAnno annotation = field.getAnnotation(MyAnno.class);
                say("Field = " + field.getName()
                        + "  |  id = " + annotation.id() + "  |  description = "
                        + annotation.name() + "  |  gid= "+annotation.gid());
            }
        }

    }


    private void say(String content){
        Log.i("MainActivity",content);
        Toast.makeText(MainActivity.this,content,Toast.LENGTH_LONG).show();
    }

}
