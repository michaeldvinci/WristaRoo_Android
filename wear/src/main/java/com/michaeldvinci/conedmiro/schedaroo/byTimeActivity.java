/**
 * Created by michaeldvinci on 3/18/16.
 */
package com.michaeldvinci.conedmiro.schedaroo;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.wearable.view.WatchViewStub;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class byTimeActivity extends Activity {
    ArrayList<String> choicesList;
    ArrayAdapter adapter;
    ListView list;
    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> customAL = new ArrayList<>();
    String dayExtra;
    String howExtra;
    String schedExtra;
    Set<String> tasksSet;

    static String xmas = "Christmas Barn";
    static String other = "The Other Tent";
    static String thisT = "This Tent";
    static String that = "That Tent";
    static String disco = "Silent Disco";
    static String which = "Which Stage";
    static String what = "What Stage";

    static String[] thTime = {
    "4:00 - 6:30", "Tiki Disco", xmas,
    "5:30 - 8:30", "Quickie Mart", disco,
    "5:45 - 6:30", "Unlocking the Truth", thisT,
    "6:00 - 6:45", "DMAs", other,
    "6:45 - 7:30", "Dej Loaf", that,
    "7:00 - 7:45", "Strand of Oaks", thisT,
    "7:00 - 8:00", "New Breed Brass Band", xmas,
    "7:15 - 8:00", "Ryan Weazver", other,
    "8:00 - 8:45", "Dopapod", that,
    "8:15 - 12:30", "Full Service Party", xmas,
    "8:15 - 9:00", "Iceage", thisT,
    "8:30 - 9:15", "Temples", other,
    "8:30 - 10:30", "The Unsheathed", disco,
    "9:15 - 10:00", "Houndmouth", that,
    "9:30 - 10:15", "The Growlers", thisT,
    "9:45 - 10:30", "Glass Animals", other,
    "10:30 - 11:30", "Tove Lo", that,
    "10:30 - 12:00", "Matoma", disco,
    "10:45 - 11:30", "courtney Barnett", thisT,
    "1100 - 11:45", "Benjamin Booker", other,
    "12:00 - 1:00", "Jungle", that,
    "12:00 - 2:00", "Quickie Mart", disco,
    "12:15 - 1:00", "Mac DeMarco", thisT,
    "12:15 - 1:15", "Gramatik", other,
    "1:00 - 2:00", "Snicklefritz", xmas,
    "2:30 - 6:00", "Tiki Disco", xmas
    };

    static String[] frTime = {
    "1:15 - 2:15", "The Districts", other,
    "2:00 - 3:00", "Brown Sabbath", which,
    "2:00 - 3:00", "Pallbearer", that,
    "2:00 - 4:45", "Full Service Party", xmas,
    "3:00 - 4:00", "King Gizzard", other,
    "3:15 - 4:15", "Tanya Tagaq", thisT,
    "3:30 - 4:30", "SOJA", what,
    "3:45 - 4:45", "Royal Blood", which,
    "3:45 - 4:45", "Between the Buried & Me", that,
    "4:30 - 6:30", "Motion Potion", disco,
    "5:00 - 6:00", "Against Me", thisT,
    "5:00 - 6:00", "New Breed Brass Band", xmas,
    "5:30 - 6:30", "Dawes", what,
    "5:30 - 6:30", "Moon Taxi", which,
    "5:30 - 6:30", "Rustie", that,
    "6:15 - 9:00", "Full Service Party", xmas,
    "6:30 - 7:30", "Sylvan Esso", other,
    "6:30 - 8:30", "Attom", disco,
    "6:45 - 7:45", "Guster", thisT,
    "7:15 - 8:30", "Kacey Musgraves", that,
    "7:15 - 8:15", "Atmosphere", which,
    "7:30 - 8:45", "Alabama Shakes", what,
    "8:15 - 9:30", "Ben Folds & yMusic", other,
    "8:30 - 9:45", "Tears for Fears", thisT,
    "9:15 - 10:30", "Ben Harper", which,
    "9:45 - 11:00", "Kendrick Lamar", thisT,
    "10:30 - 12:30", "Motion Picture", disco,
    "11:00 - 12:15", "MSMW", that,
    "11:00 - 1:00", "Full Service PArty", xmas,
    "11:15 - 12:15", "Run the Jewels", thisT,
    "11:30 - 12:45", "Earth, Wind & Fire", which,
    "12:00 - 1:30", "Deadmau5", what,
    "12:!5 - 1:45", "Flying Lotus", other,
    "12:30 - 2:00", "Matoma", disco,
    "1:00 - 2:15", "Odesza", thisT,
    "1:30 - 2:30", "Jon Cleary", xmas,
    "2:00 - 3:30", "STS9", that,
    "2:00 - 4:00", "DJ Prince Hakim", disco,
    "3:00 - 4:00", "Tiki Disco", xmas,
    "4:00 - 5:00", "Tropical Party", xmas,
    "5:00 - 6:00", "Tiki Disco", xmas
    };

    static String[] saTime = {
    "12:30 - 1:30", "Priory", thisT,
    "12:30 - 1:45", "Jon Cleary", that,
    "12:45 - 1:45", "So Percussion", other,
    "2:00 - 3:00", "Catfish & the Bottlemen", thisT,
    "2:00 - 5:00", "Full Service PArty", xmas,
    "2:15 - 3:15", "Songhoy Blues", which,
    "2:30 - 3:30", "Gregory Alan Isakov", that,
    "2:30 - 3:30", "Phox", other,
    "3:30 - 4:45", "Trampled by Turtles", what,
    "3:30 - 5:00", "E.Feld", disco,
    "3:45 - 4:45", "Woods", thisT,
    "4:00 - 5:00", "Rhiannon Giddens", which,
    "4:15 - 5:15", "SZA", other,
    "4:15 - 5:30", "Bahams", that,
    "5:00 - 6:00", "Nola Bounce Party", xmas,
    "5:00 - 7:00", "SnickleFritz", disco,
    "5:15 - 6:30", "Bleachers", thisT,
    "5:45 - 6:45", "The War on Drugs", which,
    "5:45 - 7:00", "Hozier", what,
    "6:00 - 7:30", "Jamie XX", other,
    "6:00 - 6:00", "Full Service Party", xmas,
    "6:15 - 7:30", "Sturgill Simpson", that,
    "7:00 - 8:15", "Belle & Sebastian", thisT,
    "7:00 - 10:00", "Tiki Disco", disco,
    "7:30 - 8:30", "Gary Clark Jr", which,
    "8:00 - 9:30", "SBTRKT", other,
    "8:00 - 10:00", "My Morning Jacket", what,
    "8:15 - 9:30", "Atomic Bomb", that,
    "9:15 - 10:30", "Childish Gambino", which,
    "10:00 - 1:00", "Mumford & Sons", what,
    "11:00 - 12:00", "DJ Logic", disco,
    "11:00 - 1:00", "Full Service PArty", xmas,
    "12:00 - 12:45", "Different Slep", disco,
    "12:00 - 1:15", "Slayer", thisT,
    "12:00 - 1:15", "Tycho", that,
    "12:45 - 1:30", " Nuri", disco,
    "1:00 - 2:15", "Bassnectar", which,
    "1:00 - 2:00", "Vogue Dance Off", xmas,
    "1:30 - 2:15", "Childish Major", disco,
    "1:30 - 3:00", "Superjam", other,
    "2:00 - 3:15", "Flume", that,
    "2:00 - 3:30", "D'Angelo", thisT,
    "2:15 - 3:00", "El Dusty", disco,
    "3:00 - 4:00", "Mike Gao", disco,
    "3:00 - 4:00", "Robe Rave", xmas,
    "4:00 - 6:00", " 90s Rave", xmas
    };

    static String[] suTime = {
    "12:15 - 1:15", "Pokey LaForge", which,
    "12:30 - 1:15", "Shakey Graves", that,
    "12:145 - 2:00", "Christopher Denny", thisT,
    "1:15 - 2:15", "Jessica Hernandez", other,
    "1:45 - 2:30", "Hurray for Riff Raff", that,
    "2:00 - 3:00", "Madisen Ward", which,
    "2:30 - 3:45", "Twenty One Pilots", what,
    "2:30 - 3:45", "His Golden Messenger", thisT,
    "2:45 - 3:45", "Shabazz Palaces", other,
    "3:00 - 4:00", "Bela Fleck", that,
    "3:45 - 5:00", "Awolnation", which,
    "4:00 - 5:00", "Full Service Party", xmas,
    "4:15 - 5:15", "MO", other,
    "4:15 - 5:15", "The Very Best", thisT,
    "4:45 - 5:45", "Jerry Douglas", that,
    "4:45 - 6:00", "Spoon", what,
    "5:00 - 6:00", "Hip Hop Party", xmas,
    "5:00 - 8:00", "DJ Logic", disco,
    "5:45 - 6:45", "Freddie Gibbs & Madlib", other,
    "5:45 - 7:00", "Brandi Carlile", which,
    "6:00 - 7:00", "Regge Party", xmas,
    "6:00 - 7:15", "Rudimental", thisT,
    "6:30 - 7:30", "Punch Brothers", that,
    "7:00 - 8:00", "Florence & the MAchine", what,
    "7:00 - 9:00", "Full Service Party", xmas,
    "7:30 - 8:45", "Caribou", other,
    "7:45 - 9:00", "Robert Plant", which,
    "8:00 - 9:00", "G-Eazy", thisT,
    "8:00 - 9:00", "Bluegrass Superjam", that,
    "9:00 - 11:30", "Billy Joel", what
    };

    static String[] error = {"No Data Supplied"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);

        dayExtra = getIntent().getExtras().getString("dayExtra");
        schedExtra = getIntent().getExtras().getString("schedExtra");
        howExtra = getIntent().getExtras().getString("howExtra");
        data = getIntent().getExtras().getStringArrayList("customList");

        if(!PreferenceManager.getDefaultSharedPreferences(this)
                .getStringSet("customSched", new HashSet<String>()).isEmpty()) {
            tasksSet = PreferenceManager.getDefaultSharedPreferences(this)
                    .getStringSet("customSched", new HashSet<String>());
            customAL = new ArrayList<>(tasksSet);
            System.out.println("exists in SP");
        }
        else {
            customAL = new ArrayList<>();
            customAL.add("No Data Supplied");
            Set<String> tasksSet = new HashSet<>(customAL);
            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putStringSet("customSched", tasksSet)
                    .commit();
        }

        switch(dayExtra) {
            case "Thursday":
                if(schedExtra.equals("Full Schedule")) {
                    setStage(thTime);
                }
                if(schedExtra.equals("Custom Schedule")) {
                    if (customAL.size() > 0) {
                        adapter = new ArrayAdapter<>(this, R.layout.da_item, customAL);
                    } else {
                        setStage(error);
                    }
                }
                break;
            case "Friday":
                setStage(frTime);
                break;
            case "Saturday":
                setStage(saTime);
                break;
            case "Sunday":
                setStage(suTime);
                break;
        }
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                list = (ListView) findViewById(R.id.mainChoiceList);
                list.setAdapter(adapter);
            }
        });
    }

    private void setStage(String[] dayStage) {
        choicesList = new ArrayList<>(Arrays.asList(dayStage));
        adapter = new ArrayAdapter<>(this, R.layout.da_item, choicesList);
        adapter.notifyDataSetChanged();
    }
}