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


}
