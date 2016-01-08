package sabria.demo.asyncservice;

import com.joanzapata.android.asyncservice.api.annotation.ThrowerParam;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2016-01-08  13:54
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class DummyErrorMessage {

    private Throwable throwable;
    private Long id;

    public DummyErrorMessage(Throwable throwable, @ThrowerParam("id") Long id) {
        this.throwable = throwable;
        this.id = id;
    }
}
