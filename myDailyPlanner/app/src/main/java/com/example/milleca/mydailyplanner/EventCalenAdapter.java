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

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by milleca on 1/6/2018.
 */

public class EventCalenAdapter  extends RecyclerView.Adapter<EventCalenAdapter.ViewHolder> {
    private List<Calen> mCalenList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView event_title;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            event_title = (TextView) v.findViewById(R.id.name);

        }
    }

    public void add(int position,Calen calen) {
        mCalenList.add(position,calen);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mCalenList.remove(position);
        notifyItemRemoved(position);
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public EventCalenAdapter(List<Calen> myDataset, Context context, RecyclerView recyclerView) {
        mCalenList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventCalenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.calen_content, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EventCalenAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/quick.ttf");
        final Calen calen = mCalenList.get(position);
        holder.event_title.setText(calen.getTitle());


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUpdateActivity(calen.getID(),calen.getType());

            }
        });
    }

    private void goToUpdateActivity(int eventId,String type) {
        String mStringClickID = Integer.toString(eventId);
        if(type.equalsIgnoreCase("event")) {
            Intent intent = new Intent(mContext, EventEditActivity.class);
            intent.putExtra(EventEditActivity.EXTRA_EVENT_ID, mStringClickID);
            mContext.startActivity(intent);
        }
        if (type.equalsIgnoreCase("note")){
            Intent intent=new Intent(mContext,NoteEditActivity.class);
            intent.putExtra(NoteEditActivity.EXTRA_NOTE_ID,mStringClickID);
            mContext.startActivity(intent);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCalenList.size();
    }
}