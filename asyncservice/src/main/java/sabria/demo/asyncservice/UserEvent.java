package sabria.demo.asyncservice;

import java.io.Serializable;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2016-01-08  13:54
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class UserEvent implements Serializable {

    private Long id;

    private String name;

    private int age;

    UserEvent() {}

    public UserEvent(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}