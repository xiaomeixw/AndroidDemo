package sabria.demo.fluxarchitecture.stores;

import android.util.Log;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sabria.demo.fluxarchitecture.actions.Action;
import sabria.demo.fluxarchitecture.contants.Contant;
import sabria.demo.fluxarchitecture.dispatcher.Dispatcher;
import sabria.demo.fluxarchitecture.model.Todo;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-21  11:01
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class TodoStore extends Store {

    private static TodoStore instance;


    private final List<Todo> todos;

    protected TodoStore(Dispatcher dispatcher) {
        super(dispatcher);
        todos=new ArrayList<>();
    }



    public static TodoStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new TodoStore(dispatcher);

        }
        return instance;

    }

    public List<Todo> getTodos() {
        return todos;
    }



    @Override
    StoreChangeEvent changeEvent() {
        return new TodoStoreChangeEvent();
    }

    public class TodoStoreChangeEvent implements StoreChangeEvent {
    }

    /**
     * 这里收Action-event的，然后这里再发具体其他event事件.
     * @param action
     */
    @Subscribe
    @Override
    public void onAction(Action action) {

        Log.i("---", "onAction-event");


        switch (action.getType()){
            case Contant.TODO_CREATE:
                String text = ((String) action.getData().get(Contant.KEY_TEXT));
                create(text);
                emitStoreChange();
                break;
        }

    }

    private void create(String text) {
        long id = System.currentTimeMillis();
        Todo todo = new Todo(id, text);
        addElement(todo);
        Collections.sort(todos);
    }

    private void addElement(Todo clone) {
        todos.add(clone);
        Collections.sort(todos);
    }
}
