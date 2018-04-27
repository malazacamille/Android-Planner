package com.example.milleca.mydailyplanner;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityOne extends FragmentActivity {
    ViewPager viewpager;
    ImageButton btnAddTask,btnBack;
    TextView name;
    LinearLayout taskLinear;
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ActivityOne.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        viewpager = (ViewPager) findViewById(R.id.pager);
       btnBack=findViewById(R.id.btnBack);
        btnAddTask = (ImageButton)findViewById(R.id.btnAddTask);
        name=findViewById(R.id.name);
        taskLinear=findViewById(R.id.taskLinear);

        PageAdapter padapter = new PageAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);
        viewpager.setCurrentItem(1);
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/quick.ttf");

        name.setText("Event");
        name.setTypeface(font);

        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.my_statusbar_color));

        name.setTextColor(Color.parseColor("#ffffff"));
        taskLinear.setBackgroundColor(Color.parseColor("#336b87"));
        btnAddTask.setImageResource(R.drawable.add1);
        btnBack.setImageResource(R.drawable.back);


        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ActivityOne.this,EventAddActivity.class);
                intent1.putExtra(ReminderAddActivity.EXTRA_CATEGORY, Integer.toString(0));
                startActivity(intent1);
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityOne.this,MainActivity.class);
                startActivity(intent);
            }
        });

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                btnAction(position);

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });


    }

    private void btnAction(int position) {
        if(position==0){
            name.setText("Task");
            btnAddTask.setVisibility(View.VISIBLE);
            btnAddTask.setImageResource(R.drawable.add1);
            btnAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1=new Intent(ActivityOne.this,ReminderAddActivity.class);
                    intent1.putExtra(ReminderAddActivity.EXTRA_CATEGORY, Integer.toString(0));
                    startActivity(intent1);
                }
            });
        }
        if(position==1){
            name.setText("Event");
            btnAddTask.setVisibility(View.VISIBLE);
            btnAddTask.setImageResource(R.drawable.add1);
            btnAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ActivityOne.this,EventAddActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(position==2){
            name.setText("Calendar");
            btnAddTask.setVisibility(View.INVISIBLE);
                }

        if(position==3){
            name.setText("Note");
            btnAddTask.setVisibility(View.VISIBLE);
            btnAddTask.setImageResource(R.drawable.add1);
            btnAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ActivityOne.this,NoteAddActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(position==4){
            name.setText("More");
            btnAddTask.setVisibility(View.INVISIBLE);
        }

    }
}
