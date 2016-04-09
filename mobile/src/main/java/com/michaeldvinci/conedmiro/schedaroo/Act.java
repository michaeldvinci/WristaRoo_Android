package com.michaeldvinci.conedmiro.schedaroo;

import com.google.android.gms.wearable.DataMap;

/**
 * Created by michaeldvinci on 4/8/16.
 */
public class Act {

    public final String name;
    public final String location;
    public final String day;
    public final String time;

    public Act(String name, String time, String day, String location) {

        this.name = name;
        this.time = time;
        this.day = day;
        this.location = location;

    }

    public DataMap putToActMap(DataMap map) {

        map.putString("name", name);
        map.putString("time", time);
        map.putString("day", day);
        map.putString("location", location);

        return map;

    }

    public Act(DataMap map) {

        this(map.getString("name"),
                map.getString("time"),
                map.getString("day"),
                map.getString("location")

        );
    }

    public String getName(Act act) {

        return name;

    }

    public String getTime(Act act) {

        return time;

    }

    public String getDay(Act act) {

        return day;

    }

    public String getLocation(Act act) {

        return location;

    }

}
