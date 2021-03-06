/**
 * Created by coned_miro aka Michael D. Vinci on 3/8/16.
 */

package com.michaeldvinci.conedmiro.schedaroo;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    Set<String> tasksSet;
    ArrayList<String> actsList;
    ArrayAdapter adapter;
    ListView list;
    Button btnSend;
    GoogleApiClient mGoogleApiClient;
    DataMap map;
    private final String WEAR_MESSAGE_PATH = "com.michaeldvinci.key.schedule";
    private final String TIME_MESSAGE_PATH = "com.michaeldvinci.key.timestamp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


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
        btnSend = (Button) findViewById(R.id.sendButton);
        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sendToWearable();

            }
        });
    }

    private void sendToWearable() {
        if (!actsList.isEmpty()) {
            PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/wristaroo");
            map = putDataMapReq.getDataMap();

            System.out.println("1");

            map.putStringArrayList(WEAR_MESSAGE_PATH, actsList);
            map.putLong(TIME_MESSAGE_PATH, System.currentTimeMillis());

            PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                    .edit()
                    .putString("dataMap", Base64.encodeToString(map.toByteArray(), Base64.DEFAULT))
                    .commit();

            PutDataRequest putDataReq = putDataMapReq.asPutDataRequest();
            Wearable.DataApi.putDataItem(mGoogleApiClient, putDataReq);

            System.out.println("2");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();

        System.out.println("[mobile] - mGoogleApiClient.connect() called");
    }

    @Override
    protected void onStop() {
        if (null != mGoogleApiClient && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
            System.out.println("[mobile] - mGoogleApiClient.disconnect() called");
        }
        super.onStop();
    }

    private void populateListView() {
        list = (ListView) findViewById(R.id.listViewAdd);
        list.setAdapter(adapter);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}


