package sabria.demo.asyncservice;

import java.io.Serializable;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-11-11  09:58
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class HuiEvent implements Serializable{

    private String json;

    HuiEvent(){

    }

    HuiEvent(String json){
        this.json=json;

    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
