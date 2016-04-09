/**
 * Created by coned_miro aka Michael D. Vinci on 3/8/16.
 */

package com.michaeldvinci.conedmiro.schedaroo;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Set<String> tasksSet;
    ArrayList<String> actsList;
    ArrayAdapter adapter;
    ListView list;
    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(!PreferenceManager.getDefaultSharedPreferences(this)
                .getStringSet("wristaroo", new HashSet<String>()).isEmpty()) {
            tasksSet = PreferenceManager.getDefaultSharedPreferences(this)
                    .getStringSet("wristaroo", new HashSet<String>());
            actsList = new ArrayList<>(tasksSet);
        }
        else {
            actsList = new ArrayList<>();
            Set<String> tasksSet = new HashSet<>(actsList);
            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putStringSet("wristaroo", tasksSet)
                    .commit();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<>(this, R.layout.da_item, actsList);
        adapter.notifyDataSetChanged();
        populateListView();
        Button btnEdit = (Button) findViewById(R.id.editButton);
        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditActivity.class));
            }
        });
        Button btnAdd = (Button) findViewById(R.id.addButton);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
        Button btnSave = (Button) findViewById(R.id.sendButton);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PutDataMapRequest dataMap = PutDataMapRequest.create("/wristaroo/customschedule");
                dataMap.getDataMap().putStringArrayList("schedule", actsList);
                PutDataRequest request = dataMap.asPutDataRequest().setUrgent();


                PendingResult<DataApi.DataItemResult> result = Wearable.DataApi
                        .putDataItem((googleApiClient), request);
                System.out.println("Request: " + request);
                System.out.println("Result: " + result);

            }
        });
    }

    private void populateListView() {
        list = (ListView) findViewById(R.id.listViewAdd);
        list.setAdapter(adapter);
    }
}


