package com.michaeldvinci.conedmiro.schedaroo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        DataApi.DataListener {

    static String[] choicesS = {"Custom Schedule", "Full Schedule"};
    static ArrayList<String> choicesList = new ArrayList<>(Arrays.asList(choicesS));
    ArrayList<String> customList = null;
    ArrayAdapter adapter;
    ListView list;
    Intent intent;
    GoogleApiClient mGoogleApiClient;
    private final String WEAR_MESSAGE_PATH = "com.michaeldvinci.key.schedule";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();

        com.michaeldvinci.conedmiro.schedaroo.customSched.getInstance().setCustom(choicesList);

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
                        intent.putStringArrayListExtra("customList", customList);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        System.out.println("[wear] - onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
        System.out.println("[wear] - mGoogleApiClient.connect() called");

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Wearable.DataApi.addListener(mGoogleApiClient, this);
        System.out.println("[wear] - onConnect() success");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Wearable.DataApi.removeListener(mGoogleApiClient, this);
        mGoogleApiClient.disconnect();
        System.out.println("[wear] - mGoogleApiClient.disconnect() called");
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent event : dataEvents) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                DataItem item = event.getDataItem();
                if (item.getUri().getPath().compareTo("/wristaroo") == 0) {
                    System.out.println("[wear] - it's definitely /wristaroo");
                    DataMap dataMap = DataMapItem.fromDataItem(item).getDataMap();
                    updateCustomList(dataMap.getStringArrayList(WEAR_MESSAGE_PATH));
                    System.out.println("[wear] - data changed");
                    //customSched.setCustom(dataMap.getStringArrayList(WEAR_MESSAGE_PATH));
                }
            }

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void updateCustomList(ArrayList<String> c) {

        customList = c;
        System.out.println("[wear] - customList(input): " + c);
        //customSched.setCustom(customList);
        System.out.println("[wear] - Custom Sched Set");
        System.out.println(" ");
        System.out.println(" ");
        //System.out.println("customSched = " + customSched.getCustom());
    }
}