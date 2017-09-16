package io.arro.shame;

import org.json.JSONObject;

/**
 * Created by saybe on 2017-09-16.
 */

public interface Saveable {

    public void load(JSONObject o);
    public JSONObject save();
}
