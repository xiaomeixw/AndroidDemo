package sabria.demo.tinybus.event;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-21  17:07
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class ProcessDataEvent {

    public final int totalSteps;

    public ProcessDataEvent(int totalSteps) {
        this.totalSteps = totalSteps;
    }

    public int getTotalSteps() {
        return totalSteps;
    }
}
