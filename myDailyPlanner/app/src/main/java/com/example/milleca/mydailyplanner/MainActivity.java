package com.example.milleca.mydailyplanner;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Theme mReceivedTheme;
    TextView year1,month1,day1,quotes,txt;
    BottomNavigationView btm;
    EverydayDBHelper db;
    int bg;
    int img1;
    String color1,color2;
    ConstraintLayout main;
    String themeTitle;
    private List<String> quote;
    @Override
    public void onBackPressed() {

        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        year1=findViewById(R.id.year1);
        month1=findViewById(R.id.month1);
        day1=findViewById(R.id.day1);
        main=findViewById(R.id.main_background);
        quotes=findViewById(R.id.quotes);
        txt=findViewById(R.id.text);
        btm=findViewById(R.id.bottomNavView_Bar);
        db=new EverydayDBHelper(this);
        if(db.getThemeCount()==0) {
            db.insertTheme(1, "theme1","theme1");
        }

        mReceivedTheme = db.getTheme(1);
        themeTitle=mReceivedTheme.getTitle();
        System.out.println(themeTitle);

        switch (themeTitle){
            case "theme1":
                img1=R.color.theme1;
                bg=R.drawable.bckgrd1;
                color1="#ffffff";
                color2="#ffffff";
                break;
            case "theme2":
                img1=R.color.toolbar_title;
                bg=R.drawable.bckgrd2;
                color1="#a94153";
                color2="#a94153";
                break;
            case "theme3":
                img1=R.color.theme3;
                bg=R.drawable.bckgrd3;
                color1="#333333";
                color2="#333333";
                break;
            case "theme4":
                img1=R.color.theme4;
                bg=R.drawable.bckgrd4;
                color1="#333333";
                color2="#333333";
                break;
            case "theme5":
                img1=R.color.toolbar_title;
                bg=R.drawable.bckgrd5;
                color1="#333333";
                color2="#333333";
                break;
            case "theme6":
                img1=R.color.theme6;
                bg=R.drawable.bckgrd6;
                color1="#333333";
                color2="#333333";
                break;
            case "theme7":
                img1=R.color.theme7;
                bg=R.drawable.bckgrd7;
                color1="#81815d";
                color2="#333333";
                break;
            case "theme8":
                img1=R.color.theme8;
                bg=R.drawable.bckgrd8;
                color1="#8f959b";
                color2="#8f959b";
                break;
            case "theme9":
                img1=R.color.theme9;
                bg=R.drawable.bckgrd9;
                color1="#ffffff";
                color2="#ffffff";
                break;
            case "theme10":
                img1=R.color.toolbar_title;
                bg=R.drawable.bckgrd10;
                color1="#333333";
                color2="#333333";
                break;
            case "theme11":
                img1=R.color.theme11;
                bg=R.drawable.bckgrd11;
                color1="#79a7b7";
                color2="#9e9996";
                break;
            case "theme12":
                img1=R.color.theme12;
                bg=R.drawable.bckgrd12;
                color1="#1a2157";
                color2="#ffffff";
                break;
        }

        getWindow().setStatusBarColor(ContextCompat.getColor(this ,img1));
        main.setBackgroundResource(bg);
        year1.setTextColor(Color.parseColor(color1));
        month1.setTextColor(Color.parseColor(color1));
        day1.setTextColor(Color.parseColor(color1));
        quotes.setTextColor(Color.parseColor(color1));
        txt.setTextColor(Color.parseColor(color2));
        btm.setItemIconTintList(ColorStateList.valueOf(Color.parseColor(color2)));

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfYear=calendar.get(Calendar.DAY_OF_YEAR);
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(calendar.getTime());

        quote=new ArrayList<String>(){};



        year1.setText(""+year);
        month1.setText(""+month_name);
        day1.setText(""+day);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_item1:
                        Intent intent1 = new Intent(MainActivity.this, ActivityFive.class);
                        startActivity(intent1);

                        break;

                    case R.id.action_item2:
                        Intent intent2 = new Intent(MainActivity.this, ActivityOne.class);
                        startActivity(intent2);
                        MainActivity.this.overridePendingTransition(0, 0);
                        break;

                    case R.id.action_item3:
                        Intent intent3 = new Intent(MainActivity.this, ActivityTwo.class);
                        startActivity(intent3);
                        MainActivity.this.overridePendingTransition(0, 0);
                        break;

                    case R.id.action_item4:
                        Intent intent4 = new Intent(MainActivity.this, ActivityFour.class);
                        startActivity(intent4);
                        MainActivity.this.overridePendingTransition(0, 0);
                        break;

                    case R.id.action_item5:
                        Intent intent5 = new Intent(MainActivity.this, ActivityThree.class);
                        startActivity(intent5);
                        MainActivity.this.overridePendingTransition(0, 0);
                        break;
                }


                return false;

            }
        });

    }
}
