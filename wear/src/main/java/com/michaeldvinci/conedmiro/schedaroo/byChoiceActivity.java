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

public class byChoiceActivity extends Activity {
    static String[] howChoice = {"By Stage", "By Time"};
    static ArrayList<String> choicesList = new ArrayList<>(Arrays.asList(howChoice));
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
                        if (parent.getAdapter().getItem(position).toString().equals("By Stage")) {
                            intent = new Intent(byChoiceActivity.this, byStageActivity.class);
                            System.out.println("schedExtra: " + getIntent().getExtras().getString("schedExtra"));
                            intent.putExtra("schedExtra", getIntent().getExtras().getString("schedExtra"));
                            System.out.println("dayExtra: " + getIntent().getExtras().getString("dayExtra"));
                            intent.putExtra("dayExtra", getIntent().getExtras().getString("dayExtra"));
                            System.out.println("howExtra: " + parent.getAdapter().getItem(position).toString());
                            intent.putExtra("howExtra",parent.getAdapter().getItem(position).toString());
                            startActivity(intent);
                        }
                        if (parent.getAdapter().getItem(position).toString().equals("By Time")) {
                            intent = new Intent(byChoiceActivity.this, byTimeActivity.class);
                            System.out.println("schedExtra: " + getIntent().getExtras().getString("schedExtra"));
                            intent.putExtra("schedExtra", getIntent().getExtras().getString("schedExtra"));
                            System.out.println("dayExtra: " + getIntent().getExtras().getString("dayExtra"));
                            intent.putExtra("dayExtra", getIntent().getExtras().getString("dayExtra"));
                            System.out.println("howExtra: " + parent.getAdapter().getItem(position).toString());
                            intent.putExtra("howExtra",parent.getAdapter().getItem(position).toString());
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}
