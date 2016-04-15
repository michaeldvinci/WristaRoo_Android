package com.michaeldvinci.conedmiro.schedaroo;

import java.util.ArrayList;

/**
 * Created by michaeldvinci on 4/12/16.
 */
public class customSched {
    private static customSched mInstance = null;

    private ArrayList<String> customSchedule;

    private customSched() {

        customSchedule = new ArrayList<String>();

    }

    public static customSched getInstance() {
        if(mInstance == null) {
            mInstance = new customSched();
        }
        return mInstance;
    }

    public customSched(ArrayList<String> customSched) {

        this.customSchedule = customSched;

    }

    public ArrayList<String> getCustom() {

        return this.customSchedule;

    }

    public void setCustom(ArrayList<String> input) {

        customSchedule = input;

    }
}
