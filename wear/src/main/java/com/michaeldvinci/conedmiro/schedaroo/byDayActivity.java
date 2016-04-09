/**
 * Created by michaeldvinci on 3/17/16.
 */
package com.michaeldvinci.conedmiro.schedaroo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

public class byDayActivity extends Activity {
    static String[] dayChoice = {"Thursday", "Friday", "Saturday", "Sunday"};
    static ArrayList<String> choicesList = new ArrayList<>(Arrays.asList(dayChoice));
    ArrayAdapter adapter;
    ListView list;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        adapter = new ArrayAdapter<>(this, R.layout.da_item, choicesList);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                adapter.notifyDataSetChanged();
                list = (ListView) findViewById(R.id.mainChoiceList);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        intent = new Intent(byDayActivity.this, byChoiceActivity.class);
                        intent.putExtra("schedExtra",getIntent().getExtras().getString("schedExtra"));
                        intent.putExtra("dayExtra", parent.getAdapter().getItem(position).toString());
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
