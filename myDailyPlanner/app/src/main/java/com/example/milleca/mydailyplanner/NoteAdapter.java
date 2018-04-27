package com.example.milleca.mydailyplanner;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private List<Note> mNoteList;
    private Context mContext;
    private RecyclerView mRecyclerV;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView date_created,note_content;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            date_created = (TextView) v.findViewById(R.id.date);
            note_content=v.findViewById(R.id.content);
        }
    }

    public void add(int position,Note note) {
        mNoteList.add(position,note);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mNoteList.remove(position);
        notifyItemRemoved(position);
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public NoteAdapter(List<Note> myDataset, Context context, RecyclerView recyclerView) {
        mNoteList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.note_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/quick.ttf");
        final Note note = mNoteList.get(position);
        holder.note_content.setTypeface(font);
        holder.date_created.setTypeface(font);
        holder.date_created.setText(note.getDate());
        holder.note_content.setText(note.getNoteContent());

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdateActivity(note.getID());
            }
        });


    }



    private void goToUpdateActivity(int reminderId) {
        String mStringClickID = Integer.toString(reminderId);
        Intent goToUpdate = new Intent(mContext, NoteEditActivity.class);
        goToUpdate.putExtra(NoteEditActivity.EXTRA_NOTE_ID, mStringClickID);
        mContext.startActivity(goToUpdate);

    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
}
