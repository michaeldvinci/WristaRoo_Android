package com.michaeldvinci.conedmiro.schedaroo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by michaeldvinci on 4/18/16.
 */
public class MyAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    ArrayList<String> data = null;

    public MyAdapter(Context context, int layoutResourceId, ArrayList<String> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        StringHolder holder = null;

        String value = getItem(position);


        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new StringHolder();
            holder.textView = (TextView)row.findViewById(R.id.text1);

            row.setTag(holder);
        }
        else
        {
            holder = (StringHolder)row.getTag();
        }

        String actTime = data.get(position);
        System.out.println(actTime);

        if (actTime == ("Christmas Barn")) {
            row.setBackgroundColor(Color.parseColor("Green"));
        }
        if (actTime == ("The Other Tent")) {
            row.setBackgroundColor(Color.parseColor("Red"));
        }
        if (actTime == "This Tent") {
            row.setBackgroundColor(Color.parseColor("White"));
        }
        if (actTime ==  "That Tent") {
            row.setBackgroundColor(Color.parseColor("Blue"));
        }
        if (actTime == "Silent Disco") {
            row.setBackgroundColor(Color.parseColor("Black"));
        }
        if (actTime == "Which Stage") {
            row.setBackgroundColor(Color.parseColor("Purple"));
        }
        if (actTime == "What Stage" ) {
            row.setBackgroundColor(Color.parseColor("Brown"));
        }

        holder.textView.setText(actTime);
        //holder.textView = (TextView)row.findViewById(R.id.text1);
        row.setTag(holder);

        return row;
    }

    static class StringHolder
    {
        TextView textView;
    }
}