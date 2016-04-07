package com.michaeldvinci.conedmiro.schedaroo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity implements DataApi.DataListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    static String[] choicesS = {"Custom Schedule", "Full Schedule"};
    static ArrayList<String> choicesList = new ArrayList<>(Arrays.asList(choicesS));
    ArrayAdapter adapter;
    ListView list;
    Intent intent;
    static ArrayList<String> data;
    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Wearable.API)
                .build();

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
                        intent = new Intent(MainActivity.this, byDayActivity.class);
                        intent.putExtra("schedExtra", parent.getAdapter().getItem(position).toString());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void onConnected(Bundle bundle) {
        Wearable.DataApi.addListener(googleApiClient, this).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Log.i("myTag", String.valueOf(status));
            }
        });
        System.out.println("onConnected Activated!!!");
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent event : dataEvents) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                DataItem item = event.getDataItem();
                if (item.getUri().getPath().compareTo("/wristaroo/customschedule") == 0) {
                    DataMap dataMap = DataMapItem.fromDataItem(item).getDataMap();
                    data = dataMap.getStringArrayList("schedule");
                }
            } else if (event.getType() == DataEvent.TYPE_DELETED) {
                Log.i("myTag", String.valueOf(event));
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

}
