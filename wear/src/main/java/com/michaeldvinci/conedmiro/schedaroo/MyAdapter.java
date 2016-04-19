package com.michaeldvinci.conedmiro.schedaroo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by michaeldvinci on 4/18/16.
 */
public class MyAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    String data[] = null;
    String color[] = null;

    public MyAdapter(Context context, int layoutResourceId, String[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        StringHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new StringHolder();

            row.setTag(holder);
        }
        else
        {
            holder = (StringHolder)row.getTag();
        }

        //holder.txtTitle.setText(data[position]);
        //holder.txtTitle = (TextView)row.findViewById(R.id.text1);
        //row.setTag(holder);

        if (data[position] == "Christmas Barn") {
            row.setBackgroundColor(Color.parseColor("Green"));
        }

        return row;
    }

    static class StringHolder
    {
        TextView txtTitle;
    }
}