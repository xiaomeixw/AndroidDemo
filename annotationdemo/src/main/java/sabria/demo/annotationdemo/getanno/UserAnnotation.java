package sabria.demo.annotationdemo.getanno;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2016-01-11  15:14
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
@MyAnno(name="type",gid=Long.class)
public class UserAnnotation {

    //字段
    @MyAnno(name="param",id=1,gid=Long.class) // 使用了类成员注解
    private Integer age;

    //构造器
    @MyAnno(name="construct",id=2,gid=Long.class)// 使用了构造方法注解
    public UserAnnotation() {
    }

    //方法a，b，c
    @MyAnno(name="public method", id=3, gid=Long.class)// 使用了 public 方法注解
    public void a() {
        Map<String, String> m = new HashMap<String, String>(0);
    }

    @MyAnno(name="protected method", id=4, gid=Long.class)//protected 方法注解
    protected void b() {
        Map<String, String> m = new HashMap<String, String>(0);
    }

    @MyAnno(name="private method " , id = 5, gid=Long.class) // private 方法注解
    private void c(){
        Map<String, String> m = new HashMap<String, String>(0);
    }

    public void b(Integer a){
    }


}
