package com.example.milleca.mydailyplanner;

import android.annotation.SuppressLint;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by milleca on 1/16/2018.
 */

public class FragmentOne extends Fragment {
    private EverydayDBHelper dbHelper;
    private String filter = "";
    int allSched,personalErrands,work,health,school,others;
    TextView allSchedTxt,allSchedCount,personalErrandsTxt,personalErrandCount,workTxt,workCount,healthTxt,healthCount,schoolTxt,
            schoolCount,OthersTxt,othersCount;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/quick.ttf");

        allSchedTxt=rootView.findViewById(R.id.allSchedTxt);
        allSchedCount=rootView.findViewById(R.id.allSchedCount);
        personalErrandsTxt=rootView.findViewById(R.id.personalErrandsTxt);
        personalErrandCount=rootView.findViewById(R.id.personalErrandCount);
        workTxt=rootView.findViewById(R.id.workTxt);
        workCount=rootView.findViewById(R.id.workCount);
        healthTxt=rootView.findViewById(R.id.healthTxt);
         healthCount=rootView.findViewById(R.id.healthCount);
       schoolTxt=rootView.findViewById(R.id.schoolTxt);
         schoolCount=rootView.findViewById(R.id.schoolCount);
        OthersTxt=rootView.findViewById(R.id.OthersTxt);
        othersCount=rootView.findViewById(R.id.othersCount);


        allSchedTxt.setTypeface(font);
        allSchedCount.setTypeface(font);
        personalErrandsTxt.setTypeface(font);
        personalErrandCount.setTypeface(font);
        workTxt.setTypeface(font);
        workCount.setTypeface(font);
        healthTxt.setTypeface(font);
        healthCount.setTypeface(font);
        schoolTxt.setTypeface(font);
        schoolCount.setTypeface(font);
        OthersTxt.setTypeface(font);
        othersCount.setTypeface(font);

        ImageView b1=rootView.findViewById(R.id.b1);
        ImageView b2=rootView.findViewById(R.id.b2);
        ImageView b3=rootView.findViewById(R.id.b3);
        ImageView b4=rootView.findViewById(R.id.b4);
        ImageView b5=rootView.findViewById(R.id.b5);
        ImageView b6=rootView.findViewById(R.id.b6);
        ImageView sam1=rootView.findViewById(R.id.imgAll);
        ImageView sam2=rootView.findViewById(R.id.imgPersonalErrands);
        ImageView sam3=rootView.findViewById(R.id.imgWork);
        ImageView sam4=rootView.findViewById(R.id.imgHealth);
        ImageView sam5=rootView.findViewById(R.id.imgSchool);
        ImageView sam6=rootView.findViewById(R.id.imgOthers);

        b1.setImageResource(R.drawable.forward);
        b2.setImageResource(R.drawable.forward);
        b3.setImageResource(R.drawable.forward);
        b4.setImageResource(R.drawable.forward);
        b5.setImageResource(R.drawable.forward);
        b6.setImageResource(R.drawable.forward);
        sam1.setImageResource(R.drawable.sam1);
        sam2.setImageResource(R.drawable.sam2);
        sam3.setImageResource(R.drawable.sam3);
        sam4.setImageResource(R.drawable.sam4);
        sam5.setImageResource(R.drawable.sam5);
        sam6.setImageResource(R.drawable.sam6);

        dbHelper=new EverydayDBHelper(getActivity());
        allSched=dbHelper.getRemindersCount();
        personalErrands=dbHelper.getRemindersCount("\"Personal Errands\"");
        work=dbHelper.getRemindersCount("\"Work\"");
        health=dbHelper.getRemindersCount("\"Health\"");
        school=dbHelper.getRemindersCount("\"School\"");
        others=dbHelper.getRemindersCount("\"Others\"");

        System.out.println(allSched);
        System.out.println(personalErrands);
        System.out.println(work);
        System.out.println(health);
        System.out.println(school);
        System.out.println(others);

        allSchedCount.setText(""+allSched+" tasks");
        personalErrandCount.setText(""+personalErrands+" tasks");
        workCount.setText(""+work+" tasks");
        healthCount.setText(""+health+" tasks");
        schoolCount.setText(""+school+" tasks");
        othersCount.setText(""+others+" tasks");

        LinearLayout btnAllSched=rootView.findViewById(R.id.btnAllSched);
        LinearLayout btnPersonalErrands=rootView.findViewById(R.id.btnPersonalErrands);
        LinearLayout btnWork=rootView.findViewById(R.id.btnWork);
        LinearLayout btnHealth=rootView.findViewById(R.id.btnHealth);
        LinearLayout btnSchool=rootView.findViewById(R.id.btnSchool);
        LinearLayout btnOthers=rootView.findViewById(R.id.btnOthers);

        btnAllSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ContentTask.class);
                startActivity(intent);
            }
        });
        btnPersonalErrands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ContentPersonal.class);
                startActivity(intent);
            }
        });
        btnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ContentWork.class);
                startActivity(intent);
            }
        });
        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ContentHealth.class);
                startActivity(intent);
            }
        });
        btnSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ContentSchool.class);
                startActivity(intent);
            }
        });
        btnOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ContentOthers.class);
                startActivity(intent);
            }
        });



        /*getActivity().getWindow()
                .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
*/



        return rootView;
    }

}
