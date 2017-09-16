package io.arro.shame;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by saybe on 2017-09-16.
 */

public class Public_Values {

    private static Public_Values pv = null;

    public static Public_Values getInstance() {
        if (pv == null) {
            pv = new Public_Values();
        }

        return pv;
    }

    public DateFormat dt = new SimpleDateFormat("MM/dd/yyyy");

    private Public_Values() {

    }
}
