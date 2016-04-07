/**
 * Created by coned_miro aka Michael D. Vinci on 3/8/16.
 */

package com.michaeldvinci.conedmiro.schedaroo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EditActivity extends AppCompatActivity {

    Set<String> tasksSet;
    List<String> actsList;
    ArrayAdapter adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasksSet = PreferenceManager.getDefaultSharedPreferences(this)
                .getStringSet("wristaroo", new HashSet<String>());
        actsList = new ArrayList<>(tasksSet);
        AlertDialog alertDialog = new AlertDialog.Builder(EditActivity.this).create();
        alertDialog.setMessage("Long Press to Delete");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        setContentView(R.layout.activity_edit);
        adapter = new ArrayAdapter<>(this, R.layout.da_item, actsList);
        populateListView();
        Button btnCancel = (Button) findViewById(R.id.cancelButton);
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditActivity.this, MainActivity.class));
            }
        });

        Button btnSave = (Button) findViewById(R.id.saveButton);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> tasksSet = new HashSet<>(actsList);
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                        .edit()
                        .putStringSet("wristaroo", tasksSet)
                        .commit();
                startActivity(new Intent(EditActivity.this, MainActivity.class));
            }
        });
        Button btnClear = (Button) findViewById(R.id.clearButton);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actsList.clear();
                adapter.notifyDataSetChanged();
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long arg3) {
                actsList.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }

        });
    }

    private void populateListView() {
        list = (ListView) findViewById(R.id.editListView);
        list.setAdapter(adapter);
    }
}


