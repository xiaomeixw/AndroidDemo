package sabria.demo.fluxarchitecture.dispatcher;

import android.util.Log;

import com.squareup.otto.Bus;

import sabria.demo.fluxarchitecture.actions.Action;
import sabria.demo.fluxarchitecture.stores.Store;
import sabria.demo.fluxarchitecture.Utils.Utils;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-18  18:34
 * Base on Meilimei.com (PHP Service)
 * Describe:
 *
 * 思路:
 * 1.dispatch(Action.key  Action.value) -->是否key为empty,是否不是k-v键值对
 * 2.将key-value存入Action的build的HashMap中
 * 3.post发送事件,方法参数就是Action.build
 *
 * Version:1.0
 * Open source
 */
public class Dispatcher {

    private static Dispatcher dispatcher;

    Bus bus;

    Dispatcher(Bus bus){
        this.bus=bus;
    }

    //single-instance
    public static Dispatcher getInstance(Bus bus){
        if(dispatcher == null){
            dispatcher = new Dispatcher(bus);
        }
        return dispatcher;
    }




    public void register(Object object){
        bus.register(object);
    }

    public void unregister(Object object){
        bus.unregister(object);
    }

    public void emitChange(Store.StoreChangeEvent inter){
        post(inter);
    }


    public void dispatch(String type,Object... data){
        if (Utils.isEmpty(type)) {
            throw new IllegalArgumentException("Type must not be empty");
        }

        //must key - value
        if(data.length % 2 !=0){
            throw new IllegalArgumentException("Data must be a valid list of key,value pairs");
        }

        Action.Builder builder = Action.type(type);
        
        int i =0;
        while (i<data.length){
            String key = (String) data[i++];
            Object value = data[i++];
            builder.bundle(key,value);
        }

        //post

        Log.i("---", "dispatch");

        post(builder.build());
    }


    private void post(final Object event){
        bus.post(event);
    }



}
