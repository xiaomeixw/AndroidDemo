package sabria.demo.fluxarchitecture.actions;

import java.util.HashMap;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-18  18:58
 * Base on Meilimei.com (PHP Service)
 * Describe:
 *
 * Actions也不复杂。类似pojo，有两个主要属性

    Type: 一个String，定义了事件的类型。
    Data: 一个map，装载了本次操作。

 * Version:1.0
 * Open source
 */
public class Action {

    private final String type;
    private final HashMap<String,Object> data;


    Action(String type,HashMap<String,Object> data){
        this.type=type;
        this.data=data;
    }

    public String getType() {
        return type;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public static Builder type(String type){
        return new Builder().with(type);
    }




    public static class Builder{

        private String type;
        private HashMap<String,Object> data;

        //return builder
        Builder with(String type){
            if(type == null){

                throw new IllegalArgumentException("Type may not be null.");
            }
            this.type=type;
            this.data=new HashMap<>();
            return this;
        }

        public Builder bundle (String key,Object value){
            if (key == null) {
                throw new IllegalArgumentException("Key may not be null.");
            }

            if (value == null) {
                throw new IllegalArgumentException("Value may not be null.");
            }
            data.put(key,value);
            return this;
        }

        public Action build(){
            if(type == null || type.isEmpty()){
                throw new IllegalArgumentException("At least one key is required.");
            }
            return new Action(type,data);
        }


    }




}
