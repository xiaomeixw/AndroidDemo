package sabria.demo.fluxarchitecture.actions;

import android.util.Log;

import sabria.demo.fluxarchitecture.contants.Contant;
import sabria.demo.fluxarchitecture.dispatcher.Dispatcher;
import sabria.demo.fluxarchitecture.model.Todo;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-21  10:41
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class ActionsCreator {

    private static ActionsCreator instance;
    final Dispatcher dispatcher;


    public ActionsCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionsCreator getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new ActionsCreator(dispatcher);
        }
        return instance;
    }

    public void create(String text) {
        Log.i("---","ActionsCreator");
        dispatcher.dispatch(Contant.TODO_CREATE, Contant.KEY_TEXT, text);
    }

    public void destroy(long id) {
        dispatcher.dispatch(Contant.TODO_DESTROY, Contant.KEY_ID, id);
    }

    public void undoDestory(){
        dispatcher.dispatch(Contant.TODO_UNDO_DESTROY);
    }

    public void toggleComplete(Todo todo) {
        long id = todo.getId();
        String actionType = todo.isComplete() ? Contant.TODO_UNDO_COMPLETE : Contant.TODO_COMPLETE;

        dispatcher.dispatch(actionType, Contant.KEY_ID, id);

        dispatcher.dispatch(actionType, Contant.KEY_ID, id);
    }

    public void toggleCompleteAll() {
        dispatcher.dispatch(Contant.TODO_TOGGLE_COMPLETE_ALL);
    }

    public void destroyCompleted() {
        dispatcher.dispatch(Contant.TODO_DESTROY_COMPLETED);
    }
}
