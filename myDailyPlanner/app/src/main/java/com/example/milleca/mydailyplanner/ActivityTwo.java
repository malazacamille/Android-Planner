package com.example.milleca.mydailyplanner;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityTwo extends FragmentActivity {
    ViewPager viewpager;
    ImageButton btnAddTask;
    TextView name;
    LinearLayout taskLinear;

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ActivityTwo.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        viewpager = (ViewPager) findViewById(R.id.pager);
        PageAdapter padapter = new PageAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);
        viewpager.setCurrentItem(2);
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/quick.ttf");

        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.my_statusbar_color));

        name=findViewById(R.id.name);
        name.setText("Calendar");
        name.setTypeface(font);

        taskLinear=findViewById(R.id.taskLinear);
        taskLinear.setBackgroundColor(Color.parseColor("#336b87"));

        btnAddTask = (ImageButton)findViewById(R.id.btnAddTask);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ActivityTwo.this,ReminderAddActivity.class);
                intent1.putExtra(ReminderAddActivity.EXTRA_CATEGORY, Integer.toString(0));
                startActivity(intent1);
            }
        });

        ImageButton btnBack=findViewById(R.id.btnBack);
        btnBack.setImageResource(R.drawable.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityTwo.this,MainActivity.class);
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
                    Intent intent1=new Intent(ActivityTwo.this,ReminderAddActivity.class);
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
                    Intent intent=new Intent(ActivityTwo.this,EventAddActivity.class);
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
                    Intent intent=new Intent(ActivityTwo.this,NoteAddActivity.class);
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
