package com.example.milleca.mydailyplanner;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by milleca on 1/25/2018.
 */

public class FragmentPriorityTask extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private EverydayDBHelper dbHelper;
    private TaskPriorityAdapter adapter;
    private TextView title;
    private String filter = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alltask, container, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/quick.ttf");
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        title=rootView.findViewById(R.id.title);
        title.setText("Priority");
        title.setTypeface(font);
        mRecyclerView.setHasFixedSize(true);

        DividerItemDecoration divider = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.my_custom_divider));
        mRecyclerView.addItemDecoration(divider);

        getActivity().getWindow()
                .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        dbHelper = new EverydayDBHelper(getActivity());
        adapter = new TaskPriorityAdapter(dbHelper.getAllTask("asc"), getActivity(), mRecyclerView);
        mRecyclerView.setAdapter(adapter);
        return rootView;
    }

}
