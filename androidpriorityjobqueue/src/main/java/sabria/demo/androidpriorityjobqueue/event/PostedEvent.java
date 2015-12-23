package sabria.demo.androidpriorityjobqueue.event;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-23  19:00
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class PostedEvent {

    private String text;

    public PostedEvent(String text){
        this.text=text;
    }

    public String getText() {
        return text;
    }
}
