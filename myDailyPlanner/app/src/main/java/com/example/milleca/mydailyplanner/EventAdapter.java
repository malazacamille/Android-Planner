package com.example.milleca.mydailyplanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by milleca on 1/6/2018.
 */

public class EventAdapter  extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Event> mEventList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView event_title,event_date,avail_txt;
        public ImageView event_photo,date_img;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            event_title = (TextView) v.findViewById(R.id.event_title);
            event_date = (TextView) v.findViewById(R.id.event_date);
            event_photo=(ImageView)v.findViewById(R.id.event_photo);
            date_img=v.findViewById(R.id.date_img);
            avail_txt=v.findViewById(R.id.avail_txt);

        }
    }

    public void add(int position,Event event) {
        mEventList.add(position, event);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mEventList.remove(position);
        notifyItemRemoved(position);
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public EventAdapter(List<Event> myDataset, Context context, RecyclerView recyclerView) {
        mEventList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String[] sDateSplit;
        String[] eDateSplit;
        int sYear,sMonth,sDay,eYear,eMonth,eDay;

        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/quick.ttf");
        final Event event = mEventList.get(position);
        holder.event_title.setText(event.getTitle());
        holder.event_title.setTypeface(font,Typeface.BOLD);
        holder.event_date.setText(event.getSDate());
        holder.event_date.setTypeface(font);
        holder.avail_txt.setText(event.getAvail());
        sDateSplit = event.getSDate().split("-");
        eDateSplit = event.getEDate().split("-");

        sYear = Integer.parseInt(sDateSplit[0]);
        sMonth = Integer.parseInt(sDateSplit[1]);
        sDay= Integer.parseInt(sDateSplit[2]);

        eYear = Integer.parseInt(eDateSplit[0]);
        eMonth = Integer.parseInt(eDateSplit[1]);
        eDay= Integer.parseInt(eDateSplit[2]);

        String mn= String.valueOf(sMonth);
        if(mn.equalsIgnoreCase("01")){
            sMonth=1;
        }
        if(mn.equalsIgnoreCase("02")){
            sMonth=2;
        }
        if(mn.equalsIgnoreCase("03")){
            sMonth=3;
        }
        if(mn.equalsIgnoreCase("04")){
            sMonth=4;
        }
        if(mn.equalsIgnoreCase("05")){
            sMonth=5;
        }
        if(mn.equalsIgnoreCase("06")){
            sMonth=6;
        }
        if(mn.equalsIgnoreCase("07")){
            sMonth=7;
        }
        if(mn.equalsIgnoreCase("08")){
            sMonth=8;
        }
        if(mn.equalsIgnoreCase("09")){
            sMonth=9;
        }

        String dy= String.valueOf(sDay);
        if(dy.equalsIgnoreCase("01")){
            sDay=1;
        }
        if(dy.equalsIgnoreCase("02")){
            sDay=2;
        }
        if(dy.equalsIgnoreCase("03")){
            sDay=3;
        }
        if(dy.equalsIgnoreCase("04")){
            sDay=4;
        }
        if(dy.equalsIgnoreCase("05")){
            sDay=5;
        }
        if(dy.equalsIgnoreCase("06")){
            sDay=6;
        }
        if(dy.equalsIgnoreCase("07")){
            sDay=7;
        }
        if(dy.equalsIgnoreCase("08")){
            sDay=8;
        }
        if(dy.equalsIgnoreCase("09")){
            sDay=9;
        }

        String smn= String.valueOf(eMonth);
        if(smn.equalsIgnoreCase("01")){
            eMonth=1;
        }
        if(smn.equalsIgnoreCase("02")){
            eMonth=2;
        }
        if(smn.equalsIgnoreCase("03")){
            eMonth=3;
        }
        if(smn.equalsIgnoreCase("04")){
            eMonth=4;
        }
        if(smn.equalsIgnoreCase("05")){
            eMonth=5;
        }
        if(smn.equalsIgnoreCase("06")){
            eMonth=6;
        }
        if(smn.equalsIgnoreCase("07")){
            eMonth=7;
        }
        if(smn.equalsIgnoreCase("08")){
            eMonth=8;
        }
        if(smn.equalsIgnoreCase("09")){
            eMonth=9;
        }

        String edy= String.valueOf(eDay);
        if(edy.equalsIgnoreCase("01")){
            eDay=1;
        }
        if(edy.equalsIgnoreCase("02")){
            eDay=2;
        }
        if(edy.equalsIgnoreCase("03")){
            eDay=3;
        }
        if(edy.equalsIgnoreCase("04")){
            eDay=4;
        }
        if(edy.equalsIgnoreCase("05")){
            eDay=5;
        }
        if(edy.equalsIgnoreCase("06")){
            eDay=6;
        }
        if(edy.equalsIgnoreCase("07")){
            eDay=7;
        }
        if(edy.equalsIgnoreCase("08")){
            eDay=8;
        }
        if(edy.equalsIgnoreCase("09")){
            eDay=9;
        }

        Calendar cal = new GregorianCalendar(sYear, sMonth-1, sDay);
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String month_name = month_date.format(cal.getTime());

        Calendar cal2 = new GregorianCalendar(eYear, eMonth-1, eDay);
        SimpleDateFormat month_date2 = new SimpleDateFormat("MMM");
        String month_name2 = month_date2.format(cal2.getTime());

        if(sMonth==eMonth){
            holder.event_date.setText(month_name+" "+sDay+"-"+eDay+" "+sYear);
        }
        if(sDay==eDay){
            holder.event_date.setText(month_name+" "+sDay+", "+sYear);
        }
        if(sMonth!=eMonth) {
            holder.event_date.setText(month_name + " " + sDay + "-" + month_name2 + " " + eDay + " " + sYear);
        }
        switch (event.getIcon()){
            case "icon1":
            holder.event_photo.setBackgroundResource(R.drawable.ic1);
            break;
            case "icon2":
                holder.event_photo.setBackgroundResource(R.drawable.ic2);
                break;
            case "icon3":
                holder.event_photo.setBackgroundResource(R.drawable.ic3);
                break;
            case "icon4":
                holder.event_photo.setBackgroundResource(R.drawable.ic4);
                break;
            case "icon5":
                holder.event_photo.setBackgroundResource(R.drawable.ic5);
                break;
            case "icon6":
                holder.event_photo.setBackgroundResource(R.drawable.ic6);
                break;
            case "icon7":
                holder.event_photo.setBackgroundResource(R.drawable.ic7);
                break;
            case "icon8":
                holder.event_photo.setBackgroundResource(R.drawable.ic8);
                break;
            case "icon9":
                holder.event_photo.setBackgroundResource(R.drawable.ic9);
                break;
            case "icon10":
                holder.event_photo.setBackgroundResource(R.drawable.ic10);
                break;
            case "icon11":
                holder.event_photo.setBackgroundResource(R.drawable.ic11);
                break;
            case "icon12":
                holder.event_photo.setBackgroundResource(R.drawable.ic12);
                break;
            case "icon13":
                holder.event_photo.setBackgroundResource(R.drawable.ic13);
                break;
            case "icon14":
                holder.event_photo.setBackgroundResource(R.drawable.ic14);
                break;
            case "icon15":
                holder.event_photo.setBackgroundResource(R.drawable.ic15);
                break;
            case "icon16":
                holder.event_photo.setBackgroundResource(R.drawable.ic16);
                break;
            case "icon17":
                holder.event_photo.setBackgroundResource(R.drawable.ic17);
                break;
            case "icon18":
                holder.event_photo.setBackgroundResource(R.drawable.ic18);
                break;
        }
        holder.date_img.setBackgroundResource(R.drawable.date);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUpdateActivity(event.getID());

            }
        });
    }

    private void goToUpdateActivity(int eventId) {
        String mStringClickID = Integer.toString(eventId);
        Intent intent=new Intent(mContext,EventEditActivity.class);
        intent.putExtra(EventEditActivity.EXTRA_EVENT_ID,mStringClickID);
        mContext.startActivity(intent);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mEventList.size();
    }
}