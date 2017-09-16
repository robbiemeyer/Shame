package io.arro.shame;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by saybe on 2017-09-16.
 */

public class Goal implements Saveable {
    Date endDate;
    String title = "New Goal";
    String goal = "";


    public enum Status {
        COMPLETED, FAILED, IN_PROGRESS
    }

    Status status = Status.IN_PROGRESS;

    public Goal() {

    }

    public Goal(JSONObject o) {
        load(o);
    }

    public Date getNextEventDate() {
        return endDate;
    }

    @Override
    public void load(JSONObject o) {
        try {
            endDate = (Date) o.get("endDate");
            title = (String) o.get("title");
            goal = (String) o.get("goal");
            status = (Status) o.get("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject save() {
        JSONObject o = new JSONObject();
        try {
            o.put("endDate", endDate);
            o.put("title", title);
            o.put("goal", goal);
            o.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return o;
    }

    public String toString() {
        return save().toString();
    }

}
