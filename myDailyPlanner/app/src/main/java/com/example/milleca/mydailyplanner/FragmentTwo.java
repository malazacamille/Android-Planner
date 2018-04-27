package com.example.milleca.mydailyplanner;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;

/**
 * Created by milleca on 1/14/2018.
 */
//event

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentTwo extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private EverydayDBHelper dbHelper;
    private EventAdapter adapter;
    private ImageView img;
    private TextView txt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two, container, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/quick.ttf");
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv);
        img=rootView.findViewById(R.id.noValueImg);
        txt=rootView.findViewById(R.id.txt1);
        mRecyclerView.setHasFixedSize(true);
        img.setImageResource(R.drawable.noevent);
        txt.setTypeface(font);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        dbHelper = new EverydayDBHelper(getActivity());
        adapter = new EventAdapter(dbHelper.getAllEvent(""),getActivity(), mRecyclerView);
        mRecyclerView.setAdapter(adapter);

        if(adapter.getItemCount()==0){
            img.setVisibility(View.VISIBLE);
            txt.setVisibility(View.VISIBLE);
        }else{
            img.setVisibility(View.INVISIBLE);
            txt.setVisibility(View.INVISIBLE);
        }
        return rootView;
    }
}

