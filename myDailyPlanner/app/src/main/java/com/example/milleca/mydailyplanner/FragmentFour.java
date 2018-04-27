package com.example.milleca.mydailyplanner;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by milleca on 1/14/2018.
 */

public class FragmentFour extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private EverydayDBHelper dbHelper;
    private NoteAdapter adapter;
    private TextView txt;
    private ImageView img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View rootView = inflater.inflate(R.layout.fragment_four, container, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/quick.ttf");
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.note_recyclerView);
        img=rootView.findViewById(R.id.noNoteImg);
        txt=rootView.findViewById(R.id.txt);
        txt.setTypeface(font);
        img.setImageResource(R.drawable.nonote);

        mRecyclerView.setHasFixedSize(true);

        DividerItemDecoration divider = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.my_custom_divider));
        mRecyclerView.addItemDecoration(divider);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        dbHelper = new EverydayDBHelper(getActivity());
        adapter = new NoteAdapter(dbHelper.getAllNote(""), getActivity(), mRecyclerView);
        mRecyclerView.setAdapter(adapter);

        if(adapter.getItemCount()==0){
            img.setVisibility(View.VISIBLE);
            txt.setVisibility(View.VISIBLE);
        }
        else{
            img.setVisibility(View.INVISIBLE);
            txt.setVisibility(View.INVISIBLE);
        }
        return rootView;
    }


}
