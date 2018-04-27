package com.example.milleca.mydailyplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by milleca on 1/14/2018.
 */

public class FragmentThree extends Fragment {
    public TextView theme1,delete,passcode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_three, container, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/quick.ttf");
        theme1=rootView.findViewById(R.id.theme);
        delete=rootView.findViewById(R.id.delete_data);
        passcode=rootView.findViewById(R.id.passcode);
        theme1.setTypeface(font);
        delete.setTypeface(font);
        passcode.setTypeface(font);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
                builder.setMessage("delete all data?")
                        .setPositiveButton("delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                EverydayDBHelper dbHelper=new EverydayDBHelper(getContext());
                                dbHelper.deleteAllCalen();
                                dbHelper.deleteAllNote();
                                dbHelper.deleteAllTask();
                                dbHelper.deleteAllEvent();
                                Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "discarded", Toast.LENGTH_SHORT).show();
                            }
                        });
                // Create the AlertDialog object and return it
                final android.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



        passcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.about, null);
                Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/quick.ttf");
                final TextView sup=mView.findViewById(R.id.sup);
                final TextView dev=mView.findViewById(R.id.dev);
                final TextView abt=mView.findViewById(R.id.abt);
                final ImageView lg1=mView.findViewById(R.id.evry);
                final ImageView lg2=mView.findViewById(R.id.conlu);
                final ImageView lg3=mView.findViewById(R.id.doc);
                final ImageView lg4=mView.findViewById(R.id.sed);

                sup.setTypeface(font);
                dev.setTypeface(font);
                abt.setTypeface(font);

                lg1.setBackgroundResource(R.drawable.about);
                lg2.setBackgroundResource(R.drawable.conlu);
                lg3.setBackgroundResource(R.drawable.doc);
                lg4.setBackgroundResource(R.drawable.sed);
                mBuilder.setView(mView);
                final android.app.AlertDialog dialog = mBuilder.create();
                dialog.show();

                dialog.getWindow().setLayout(700, 700);
            }
        });

        theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ThemeItem.class);
                startActivityForResult(intent,1);

            }
        });
        return rootView;
    }



}
