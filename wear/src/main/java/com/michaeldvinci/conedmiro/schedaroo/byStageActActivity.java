/**
 * Created by michaeldvinci on 3/18/16.
 */
package com.michaeldvinci.conedmiro.schedaroo;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class byStageActActivity extends Activity {

    String dayExtra;
    String howExtra;
    String schedExtra;
    String stageExtra;

    static String[] actEmpty = {};

    static String[] actThisTh = {
            "5:45 - 6:30", "Unlocking the Truth",
            "7:00 - 7:45", "Strand of Oaks",
            "8:15 - 9:00", "Iceage",
            "9:30 - 10:15", "The Growlers",
            "10:45 - 11:30", "Courtney Barnett",
            "12:15 - 1:00", "Mac DeMarco"
    };
    static String[] actThatTh = {
            "6:45 - 7:30", "Dej Loaf",
            "8:00 - 8:45", "Dopapod",
            "9:15 - 10:00", "Houndmouth",
            "10:30 - 11:30", "Tove Lo",
            "12:00 - 1:00", "Jungle"
    };
    static String[] actOtherTh = {
            "6:00 - 6:45", "DMAs",
            "7:15 - 8:00", "Ryan Weaver",
            "8:30 - 9:15", "Temples",
            "9:45 - 10:30", "Glass Animals",
            "11:00 - 11:45", "Benjamin Booker",
            "12:15 - 1:15", "Gramatik"
    };
    static String[] actDiscoTh = {
            "5:30 - 8:30", "Quickie Mart",
            "8:30 - 10:30", "The Unsheathed",
            "10:30 - 12:00", "Matoma",
            "12:00 - 2:00", "Quickie Mart"
    };
    static String[] actXmasTh = {
            "4:00 - 6:30", "Tiki Disco",
            "7:00 - 8:00", "New Breed Brass Band",
            "8:15 - 12:30", "Full Service Party",
            "1:00 - 2:00", "Snicklefritz",
            "2:30 - 6:00", "Tiki Disco"
    };

    static ArrayList<String> choicesList;
    ArrayAdapter adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);

        dayExtra = getIntent().getExtras().getString("dayExtra");
        schedExtra = getIntent().getExtras().getString("schedExtra");
        howExtra = getIntent().getExtras().getString("howExtra");
        stageExtra = getIntent().getExtras().getString("stageExtra");

        switch (dayExtra) {
            case "Thursday":
                switch (stageExtra) {
                    case "What Stage":
                        setStage(actEmpty);
                        break;
                    case "Which Stage":
                        setStage(actEmpty);
                        break;
                    case "This Tent":
                        setStage(actThisTh);
                        break;
                    case "That Tent":
                        setStage(actThatTh);
                        break;
                    case "The Other Tent":
                        setStage(actOtherTh);
                        break;
                    case "Silent Disco":
                        setStage(actDiscoTh);
                        break;
                    case "Christmas Barn":
                        setStage(actXmasTh);
                        break;
                }
                break;
            case "Friday":
                switch (stageExtra) {
                    case "What Stage":
                        break;
                    case "Which Stage":
                        break;
                    case "This Tent":
                        break;
                    case "That Tent":
                        break;
                    case "The Other Tent":
                        break;
                    case "Silent Disco":
                        break;
                    case "Christmas Barn":
                        break;
                }
                break;
            case "Saturday":
                switch (stageExtra) {
                    case "What Stage":
                        break;
                    case "Which Stage":
                        break;
                    case "This Tent":
                        break;
                    case "That Tent":
                        break;
                    case "The Other Tent":
                        break;
                    case "Silent Disco":
                        break;
                    case "Christmas Barn":
                        break;
                }
                break;
            case "Sunday":
                switch (stageExtra) {
                    case "What Stage":
                        break;
                    case "Which Stage":
                        break;
                    case "This Tent":
                        break;
                    case "That Tent":
                        break;
                    case "The Other Tent":
                        break;
                    case "Silent Disco":
                        break;
                    case "Christmas Barn":
                        break;
                }
                break;
        }

        adapter = new ArrayAdapter<>(this, R.layout.da_item, choicesList);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                adapter.notifyDataSetChanged();
                list = (ListView) findViewById(R.id.mainChoiceList);
                list.setAdapter(adapter);
            }
        });
    }

    private ArrayList<String> setStage(String[] dayStage) {
        choicesList = new ArrayList<>(Arrays.asList(dayStage));
        return choicesList;
    }
}
