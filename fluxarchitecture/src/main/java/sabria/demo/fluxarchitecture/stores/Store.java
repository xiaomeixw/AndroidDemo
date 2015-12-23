package sabria.demo.fluxarchitecture.stores;

import sabria.demo.fluxarchitecture.actions.Action;
import sabria.demo.fluxarchitecture.dispatcher.Dispatcher;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-21  10:17
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public abstract class Store {

    final Dispatcher dispatcher;

    public interface StoreChangeEvent{}

    protected Store(Dispatcher dispatcher){
        this.dispatcher=dispatcher;
    }

    void emitStoreChange(){
        dispatcher.emitChange(changeEvent());
    }

    //son must
    abstract StoreChangeEvent changeEvent();

    //another must
    public abstract void onAction(Action action);

}
