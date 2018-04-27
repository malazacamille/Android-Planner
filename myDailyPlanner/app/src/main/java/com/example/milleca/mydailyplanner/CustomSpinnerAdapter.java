package com.example.milleca.mydailyplanner;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milleca on 1/18/2018.
 */

public class CustomSpinnerAdapter extends ArrayAdapter<SpinnerData> {
    int groupid;
    Activity context;
    ArrayList<SpinnerData> list;
    LayoutInflater inflater;
    public CustomSpinnerAdapter(Activity context, int groupid, int id, ArrayList<SpinnerData>
            list){
        super(context,id,list);
        this.list=list;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid=groupid;
    }

    public View getView(int position, View convertView, ViewGroup parent ){
        View itemView=inflater.inflate(groupid,parent,false);

        ImageView imageView=(ImageView)itemView.findViewById(R.id.imageView);
        imageView.setImageResource(list.get(position).getIcon());
        TextView textView=(TextView)itemView.findViewById(R.id.textView);
        textView.setText(list.get(position).getIconName());
        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup
            parent){
        return getView(position,convertView,parent);

    }
}




