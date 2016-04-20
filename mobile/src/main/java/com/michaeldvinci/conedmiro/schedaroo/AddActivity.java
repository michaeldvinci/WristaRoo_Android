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
import java.util.Set;

public class AddActivity extends AppCompatActivity {

    static String[] actsFull = {
            "Pearl Jam",
            "Dead & Co.",
            "LCD Soundsystem",
            "J. Cole",
            "Ellie Goulding",
            "Macklemore",
            "Tame Impala",
            "Death Cab for Cutie",
            "M83",
            "HAIM",
            "Halsey",
            "Ween",
            "Jason Isbell",
            "CHVRCHES",
            "Miguel",
            "Chris Stapleson",
            "Judd Apatow & Friends",
            "The Chainsmokers",
            "Superjam",
            "Big Grams",
            "Band of Horses",
            "Leon Bridges",
            "Grace Potter",
            "Father John Misty",
            "Adam Devine",
            "Purity Ring",
            "The Claypool Lennon Delirium",
            "Two Door Cinema Club",
            "Sam Hunt",
            "Flosstradaumus",
            "Zeds Dead",
            "Adventure Club",
            "Tyler The Creator",
            "Bridget Everett",
            "Griz",
            "Mavis Staples",
            "TouchPants",
            "Kurt Vile & the Violators",
            "Blood Orange",
            "Lord Huron",
            "RL Grime",
            "The Bluegrass Situation",
            "X Ambassadors",
            "St. Lucia",
            "Piff the Magic Dragon",
            "Lucius",
            "Vince Staples",
            "Third eye Blind",
            "Cymande",
            "Misterwives",
            "Clutch",
            "Goldlink",
            "Lettuce",
            "Cashmere Cat",
            "Goddamn Comedy Jam",
            "Fidlar",
            "Ibeyi",
            "Keys N Krates",
            "Sam Bush Band",
            "The Wood Brothers",
            "Saint Motel",
            "Daughter",
            "The FLoozies",
            "Marian Hill",
            "Kamasi Washington",
            "BØRNS",
            "Post Malone",
            "Shamir",
            "Allen Stone",
            "Brett Dennen",
            "Oh Wonder",
            "Boy & the Bear",
            "Dungen",
            "Judah & the Lion",
            "Steve Gunn",
            "Steep Canyon Rangers",
            "The Oh Hellos",
            "The Internet",
            "John Moreland",
            "Andra Day",
            "Anderson East",
            "Twin Peaks",
            "Bully",
            "Natalie Prass",
            "Chicano Batman",
            "Givers",
            "Hermitude",
            "Jarryd James",
            "Lizzo",
            "Papadosio",
            "Rayland Baxter",
            "Vulfpeck",
            "Sata Watkins",
            "The Knocks",
            "Waxahatchee",
            "Beach Fossils",
            "Whilk and Misky",
            "Hundred Waters",
            "The London Souls",
            "Lolawolf",
            "Civil Twilight",
            "Roman Gianarthur",
            "Lany",
            "Con Brio",
            "Flux Capacitor",
            "Lael Neale",
            "Mail the Horse",
            "FKJ",
            "Joseph",
            "Doe Paoro",
            "Wet",
            "Hinds",
            "Full Service Party",
            "Bryson Tiller",
            "Bob Moses",
            "Amanda Shires",
            "Arkells",
            "Holy White Hound",
            "Luke Bell",
            "Whitney",
            "Andrew Combs",
            "Mothers",
            "Powers",
            "Public Access TV",
            "Henry Wagons",
            "Firekid",
            "Lane 8",
            "Tiki Disco",
            "Amasa Hines",
            "Aubrie Sellers",
            "Cardiknox",
            "Dylan LeBlanc",
            "Grace Mitchell",
            "Grandma Sprrow",
            "Promised Land Sound",
            "Issac Gracie",
            "Joe Fertler & the Rainbow Seekers",
            "Lawrence",
            "The Record Company",
            "Sir the BaptistMArgaret Glaspy",
            "NSR",
            "Red Bull Music Academy",
            "Maren Morris",
            "Sun Club",
            "Sunflower Bean",
            "Finish Ticket",
            "Dawn",
            "Korey Dane",
            "Polly A.",
            "Swim Deep",
            "Austin Plaine"
    };

    static ArrayList<String> actsList;
    ArrayAdapter adapter;
    ListView listAdd;
    Set<String> tasksSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(!PreferenceManager.getDefaultSharedPreferences(this)
                .getStringSet("wristaroo", new HashSet<String>()).isEmpty()) {
            tasksSet = PreferenceManager.getDefaultSharedPreferences(this)
                    .getStringSet("wristaroo", new HashSet<String>());
            actsList = new ArrayList<>(tasksSet);
            System.out.println("ActsList Populated");
        }
        else {
            actsList = new ArrayList<>();
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);
        adapter = new ArrayAdapter<>(this, R.layout.da_item, actsFull);
        populateListView();
        Button btnCancel = (Button) findViewById(R.id.cancelButton);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });
        Button btnSave = (Button) findViewById(R.id.saveButton);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddActivity.this, MainActivity.class));
                Set<String> tasksSet = new HashSet<>(actsList);
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                        .edit()
                        .putStringSet("wristaroo", tasksSet)
                        .commit();
            }
        });
        listAdd.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, View view,
                                           final int position, long arg3) {
                if (!actsList.contains(parent.getAdapter().getItem(position).toString())) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(
                            AddActivity.this);
                    alert.setMessage("Add " + parent.getAdapter().getItem(position).toString() + "?");
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            actsList.add(parent.getAdapter().getItem(position).toString());
                            System.out.println("Added!");
                            dialog.dismiss();
                        }
                    });
                    alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }
                else {
                    AlertDialog.Builder alertError = new AlertDialog.Builder(
                            AddActivity.this);
                    alertError.setMessage("Error, " + parent.getAdapter().getItem(position).toString() + " is already in list!");
                    alertError.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertError.show();
                }

            }
        });
    }

    private void populateListView() {
        listAdd = (ListView) findViewById(R.id.listViewAdd);
        listAdd.setAdapter(adapter);
    }
}


