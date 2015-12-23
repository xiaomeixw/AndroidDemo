package sabria.demo.fluxarchitecture.model;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-21  10:52
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class Todo  implements Cloneable, Comparable<Todo> {

    long id;
    boolean complete;
    String text;

    public Todo(long id, String text) {
        this.id = id;
        this.text = text;
        this.complete = false;
    }

    public Todo(long id, String text, boolean complete) {
        this.id = id;
        this.text = text;
        this.complete = complete;
    }

    public long getId() {
        return id;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getText() {
        return text;
    }

    @Override
    public Todo clone()  {
        return new Todo(id, text, complete);
    }

    @Override
    public int compareTo(Todo todo) {
        if (id == todo.getId()) {
            return 0;
        } else if (id < todo.getId()) {
            return -1;
        } else {
            return 1;
        }
    }
}
