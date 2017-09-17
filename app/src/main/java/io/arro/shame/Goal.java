package io.arro.shame;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by saybe on 2017-09-16.
 */

public class Goal implements Saveable {
    public final static int COMPLETED = 1;
    public final static int In_PROGRESS = 0;
    public final static int FAILED = -1;

    Date endDate = new Date();
    String title = "New Goal";
    String goal = "";


    int status = 0;

    public Goal() {

    }

    public Goal(String title, Date date, String goal) {
        this.title = title;
        this.endDate = date;
        this.goal = goal;
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
            endDate = Public_Values.getInstance().dt.parse((String) o.get("endDate"));
            title = (String) o.get("title");
            goal = (String) o.get("goal");
            status = (int) o.get("status");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject save() {
        JSONObject o = new JSONObject();
        try {
            o.put("endDate", Public_Values.getInstance().dt.format(endDate));
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
