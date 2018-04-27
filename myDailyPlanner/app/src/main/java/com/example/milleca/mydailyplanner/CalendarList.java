package com.example.milleca.mydailyplanner;

import android.annotation.SuppressLint;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by milleca on 2/6/2018.
 */

public class CalendarList extends AppCompatActivity {
    ViewPager viewpager;
    TextView task, event, note;
    TextView date;
    ImageView close,weeks,daily;
    AllCalendarAdapter adapter;
    LinearLayout weekly;
    TextView d1,d2,d3,d4,d5,d6,d7;
    RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5,recyclerView6,recyclerView7;
    private RecyclerView.LayoutManager mLayoutManager1,mLayoutManager2,mLayoutManager3,mLayoutManager4,mLayoutManager5,mLayoutManager6,
    mLayoutManager7;
    private EverydayDBHelper dbHelper;
    private WeeklyAdapter adapter1,adapter2,adapter3,adapter4,adapter5,adapter6,adapter7;
    public static final String EXTRA_CALEN_ID = "Calen_ID";
    private String[] cDateSplit;
    private int cYear, cDay, cM;
    private String cMonth1, cMonth2;
    public static String dt;
    private String fdate;
    private String cMonth;
    private String c1,c2,c3,c4,c5,c6,c7;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calen_list);

        task = findViewById(R.id.tsk);
        event = findViewById(R.id.evnt);
        note = findViewById(R.id.nt);
        date = findViewById(R.id.date);
        close = findViewById(R.id.back);
        weeks=findViewById(R.id.week);
        daily=findViewById(R.id.daily);
        weekly=findViewById(R.id.weekly_view);
        viewpager = findViewById(R.id.mPager);

        d1=findViewById(R.id.d1);
        d2=findViewById(R.id.d2);
        d3=findViewById(R.id.d3);
        d4=findViewById(R.id.d4);
        d5=findViewById(R.id.d5);
        d6=findViewById(R.id.d6);
        d7=findViewById(R.id.d7);

        recyclerView1 = (RecyclerView)findViewById(R.id.recyclerView1);
        recyclerView2 = (RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView3 = (RecyclerView)findViewById(R.id.recyclerView3);
        recyclerView4 = (RecyclerView)findViewById(R.id.recyclerView4);
        recyclerView5 = (RecyclerView)findViewById(R.id.recyclerView5);
        recyclerView6 = (RecyclerView)findViewById(R.id.recyclerView6);
        recyclerView7 = (RecyclerView)findViewById(R.id.recyclerView7);

        recyclerView1.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);
        recyclerView3.setHasFixedSize(true);
        recyclerView4.setHasFixedSize(true);
        recyclerView5.setHasFixedSize(true);
        recyclerView6.setHasFixedSize(true);
        recyclerView7.setHasFixedSize(true);

        mLayoutManager1 = new LinearLayoutManager(CalendarList.this);
        recyclerView1.setLayoutManager(mLayoutManager1);
        mLayoutManager2 = new LinearLayoutManager(CalendarList.this);
        recyclerView2.setLayoutManager(mLayoutManager2);
        mLayoutManager3 = new LinearLayoutManager(CalendarList.this);
        recyclerView3.setLayoutManager(mLayoutManager3);
        mLayoutManager4 = new LinearLayoutManager(CalendarList.this);
        recyclerView4.setLayoutManager(mLayoutManager4);
        mLayoutManager5 = new LinearLayoutManager(CalendarList.this);
        recyclerView5.setLayoutManager(mLayoutManager5);
        mLayoutManager6 = new LinearLayoutManager(CalendarList.this);
        recyclerView6.setLayoutManager(mLayoutManager6);
        mLayoutManager7 = new LinearLayoutManager(CalendarList.this);
        recyclerView7.setLayoutManager(mLayoutManager7);

        // Get note id from intent
        dt = getIntent().getStringExtra(EXTRA_CALEN_ID);
        cDateSplit = dt.split("-");
        cYear = Integer.parseInt(cDateSplit[0]);
        cM = Integer.parseInt(cDateSplit[1]);
        cDay = Integer.parseInt(cDateSplit[2]);
        cMonth1 = String.valueOf(dt.charAt(5));
        cMonth2 = String.valueOf(dt.charAt(6));
        cMonth = cMonth1 + cMonth2;
        String mn = String.valueOf(cDay);
        if (mn.equalsIgnoreCase("01")) {
            cDay = 1;
        }
        if (mn.equalsIgnoreCase("02")) {
            cDay = 2;
        }
        if (mn.equalsIgnoreCase("03")) {
            cDay = 3;
        }
        if (mn.equalsIgnoreCase("04")) {
            cDay = 4;
        }
        if (mn.equalsIgnoreCase("05")) {
            cDay = 5;
        }
        if (mn.equalsIgnoreCase("06")) {
            cDay = 6;
        }
        if (mn.equalsIgnoreCase("07")) {
            cDay = 7;
        }
        if (mn.equalsIgnoreCase("08")) {
            cDay = 8;
        }
        if (mn.equalsIgnoreCase("09")) {
            cDay = 9;
        }
        Calendar cal = new GregorianCalendar(cYear, cM - 1, cDay);
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String month_name = month_date.format(cal.getTime());
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        System.out.println(dt);
        fdate = cYear + "-" + cMonth + "-" + cDay;
        date.setText(month_name + " " + cDay + ", " + cYear + " Week " + week);

        adapter = new AllCalendarAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);

        task.setTextColor(Color.parseColor("#335c7d"));
        event.setTextColor(R.color.Black);
        note.setTextColor(R.color.Black);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarList.this, ActivityTwo.class);
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
        weeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weeks.setVisibility(View.INVISIBLE);
                daily.setVisibility(View.VISIBLE);
                weekly.setVisibility(View.VISIBLE);
                task.setVisibility(View.INVISIBLE);
                event.setVisibility(View.INVISIBLE);
                note.setVisibility(View.INVISIBLE);
                viewpager.setVisibility(View.INVISIBLE);
            }
        });
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weeks.setVisibility(View.VISIBLE);
                daily.setVisibility(View.INVISIBLE);
                weekly.setVisibility(View.INVISIBLE);
                task.setVisibility(View.VISIBLE);
                event.setVisibility(View.VISIBLE);
                note.setVisibility(View.VISIBLE);
                viewpager.setVisibility(View.VISIBLE);
            }
        });

        Calendar cc1 = new GregorianCalendar(cYear,cM-1,cDay);
        Calendar cc2 = new GregorianCalendar(cYear,cM-1,cDay);
        Calendar cc3 = new GregorianCalendar(cYear,cM-1,cDay);
        Calendar cc4 = new GregorianCalendar(cYear,cM-1,cDay);
        Calendar cc5 = new GregorianCalendar(cYear,cM-1,cDay);
        Calendar cc6 = new GregorianCalendar(cYear,cM-1,cDay);
        Calendar cc7 = new GregorianCalendar(cYear,cM-1,cDay);
        int cDayYear=cc1.get(Calendar.DAY_OF_YEAR);
        int cDayYearF=cDayYear%7;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(cDayYearF==1){
            //c1
            c1=format.format(cc1.getTime());
            d1.setText(""+cc1.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc1.getTime()));
            //c2
            cc2.add(Calendar.DAY_OF_MONTH,1);
            c2=format.format(cc2.getTime());
            d2.setText(""+cc2.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc2.getTime()));
            //c3
            cc3.add(Calendar.DAY_OF_MONTH,2);
            c3=format.format(cc3.getTime());
            d3.setText(""+cc3.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc3.getTime()));
            //c4
            cc4.add(Calendar.DAY_OF_MONTH,3);
            c4=format.format(cc4.getTime());
            d4.setText(""+cc4.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc4.getTime()));
            //c5
            cc5.add(Calendar.DAY_OF_MONTH,4);
            c5=format.format(cc5.getTime());
            d5.setText(""+cc5.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc5.getTime()));
            //c6
            cc6.add(Calendar.DAY_OF_MONTH,5);
            c6=format.format(cc6.getTime());
            d6.setText(""+cc6.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc6.getTime()));
            //c7
            cc7.add(Calendar.DAY_OF_MONTH,6);
            c7=format.format(cc7.getTime());
            d7.setText(""+cc7.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc7.getTime()));
        }
        if(cDayYearF==2){
            //c1
            cc1.add(Calendar.DAY_OF_MONTH,-1);
            c1=format.format(cc1.getTime());
            d1.setText(""+cc1.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc1.getTime()));
            //c2
            c2=format.format(cc2.getTime());
            d2.setText(""+cc2.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc2.getTime()));
            //c3
            cc3.add(Calendar.DAY_OF_MONTH,1);
            c3=format.format(cc3.getTime());
            d3.setText(""+cc3.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc3.getTime()));
            //c4
            cc4.add(Calendar.DAY_OF_MONTH,2);
            c4=format.format(cc4.getTime());
            d4.setText(""+cc4.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc4.getTime()));
            //c5
            cc5.add(Calendar.DAY_OF_MONTH,3);
            c5=format.format(cc5.getTime());
            d5.setText(""+cc5.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc5.getTime()));
            //c6
            cc6.add(Calendar.DAY_OF_MONTH,4);
            c6=format.format(cc6.getTime());
            d6.setText(""+cc6.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc6.getTime()));
            //c7
            cc7.add(Calendar.DAY_OF_MONTH,5);
            c7=format.format(cc7.getTime());
            d7.setText(""+cc7.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc7.getTime()));
        }
        if(cDayYearF==3){
            //c1
            cc1.add(Calendar.DAY_OF_MONTH,-2);
            c1=format.format(cc1.getTime());
            d1.setText(""+cc1.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc1.getTime()));
            //c2
            cc2.add(Calendar.DAY_OF_MONTH,-1);
            c2=format.format(cc2.getTime());
            d2.setText(""+cc2.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc2.getTime()));
            //c3
            c3=format.format(cc3.getTime());
            d3.setText(""+cc3.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc3.getTime()));
            //c4
            cc4.add(Calendar.DAY_OF_MONTH,1);
            c4=format.format(cc4.getTime());
            d4.setText(""+cc4.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc4.getTime()));
            //c5
            cc5.add(Calendar.DAY_OF_MONTH,2);
            c5=format.format(cc5.getTime());
            d5.setText(""+cc5.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc5.getTime()));
            //c6
            cc6.add(Calendar.DAY_OF_MONTH,3);
            c6=format.format(cc6.getTime());
            d6.setText(""+cc6.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc6.getTime()));
            //c7
            cc7.add(Calendar.DAY_OF_MONTH,4);
            c7=format.format(cc7.getTime());
            d7.setText(""+cc7.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc7.getTime()));
        }
        if(cDayYearF==4){
            //c1
            cc1.add(Calendar.DAY_OF_MONTH,-3);
            c1=format.format(cc1.getTime());
            d1.setText(""+cc1.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc1.getTime()));
            //c2
            cc2.add(Calendar.DAY_OF_MONTH,-2);
            c2=format.format(cc2.getTime());
            d2.setText(""+cc2.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc2.getTime()));
            //c3
            cc3.add(Calendar.DAY_OF_MONTH,-1);
            c3=format.format(cc3.getTime());
            d3.setText(""+cc3.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc3.getTime()));
            //c4
            c4=format.format(cc4.getTime());
            d4.setText(""+cc4.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc4.getTime()));
            //c5
            cc5.add(Calendar.DAY_OF_MONTH,1);
            c5=format.format(cc5.getTime());
            d5.setText(""+cc5.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc5.getTime()));
            //c6
            cc6.add(Calendar.DAY_OF_MONTH,2);
            c6=format.format(cc6.getTime());
            d6.setText(""+cc6.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc6.getTime()));
            //c7
            cc7.add(Calendar.DAY_OF_MONTH,3);
            c7=format.format(cc7.getTime());
            d7.setText(""+cc7.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc7.getTime()));
        }
        if(cDayYearF==5){
            //c1
            cc1.add(Calendar.DAY_OF_MONTH,-4);
            c1=format.format(cc1.getTime());
            d1.setText(""+cc1.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc1.getTime()));
            //c2
            cc2.add(Calendar.DAY_OF_MONTH,-3);
            c2=format.format(cc2.getTime());
            d2.setText(""+cc2.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc2.getTime()));
            //c3
            cc3.add(Calendar.DAY_OF_MONTH,-2);
            c3=format.format(cc3.getTime());
            d3.setText(""+cc3.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc3.getTime()));
            //c4
            cc4.add(Calendar.DAY_OF_MONTH,-1);
            c4=format.format(cc4.getTime());
            d4.setText(""+cc4.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc4.getTime()));
            //c5
            c5=format.format(cc5.getTime());
            d5.setText(""+cc5.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc5.getTime()));
            //c6
            cc6.add(Calendar.DAY_OF_MONTH,1);
            c6=format.format(cc6.getTime());
            d6.setText(""+cc6.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc6.getTime()));
            //c7
            cc7.add(Calendar.DAY_OF_MONTH,2);
            c7=format.format(cc7.getTime());
            d7.setText(""+cc7.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc7.getTime()));
        }

        if(cDayYearF==6){
            //c1
            cc1.add(Calendar.DAY_OF_MONTH,-5);
            c1=format.format(cc1.getTime());
            d1.setText(""+cc1.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc1.getTime()));
            //c2
            cc2.add(Calendar.DAY_OF_MONTH,-4);
            c2=format.format(cc2.getTime());
            d2.setText(""+cc2.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc2.getTime()));
            //c3
            cc3.add(Calendar.DAY_OF_MONTH,-3);
            c3=format.format(cc3.getTime());
            d3.setText(""+cc3.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc3.getTime()));
            //c4
            cc4.add(Calendar.DAY_OF_MONTH,-2);
            c4=format.format(cc4.getTime());
            d4.setText(""+cc4.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc4.getTime()));
            //c5
            cc5.add(Calendar.DAY_OF_MONTH,-1);
            c5=format.format(cc5.getTime());
            d5.setText(""+cc5.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc5.getTime()));
            //c6
            c6=format.format(cc6.getTime());
            d6.setText(""+cc6.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc6.getTime()));
            //c7
            cc7.add(Calendar.DAY_OF_MONTH,1);
            c7=format.format(cc7.getTime());
            d7.setText(""+cc7.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc7.getTime()));
        }
        if(cDayYearF==0){
            //c1
            cc1.add(Calendar.DAY_OF_MONTH,-6);
            c1=format.format(cc1.getTime());
            d1.setText(""+cc1.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc1.getTime()));
            //c2
            cc2.add(Calendar.DAY_OF_MONTH,-5);
            c2=format.format(cc2.getTime());
            d2.setText(""+cc2.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc2.getTime()));
            //c3
            cc3.add(Calendar.DAY_OF_MONTH,-4);
            c3=format.format(cc3.getTime());
            d3.setText(""+cc3.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc3.getTime()));
            //c4
            cc4.add(Calendar.DAY_OF_MONTH,-3);
            c4=format.format(cc4.getTime());
            d4.setText(""+cc4.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc4.getTime()));
            //c5
            cc5.add(Calendar.DAY_OF_MONTH,-2);
            c5=format.format(cc5.getTime());
            d5.setText(""+cc5.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc5.getTime()));
            //c6
            cc6.add(Calendar.DAY_OF_MONTH,-1);
            c6=format.format(cc6.getTime());
            d6.setText(""+cc6.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc6.getTime()));
            //c7;
            c7=format.format(cc7.getTime());
            d7.setText(""+cc7.get(Calendar.DAY_OF_MONTH));
            System.out.println(format.format(cc7.getTime()));
        }


        dbHelper = new EverydayDBHelper(CalendarList.this);
        adapter1 = new WeeklyAdapter(dbHelper.getAllCalenTypeV2 (c1),CalendarList.this, recyclerView1);
        recyclerView1.setAdapter(adapter1);
        adapter2 = new WeeklyAdapter(dbHelper.getAllCalenTypeV2(c2),CalendarList.this, recyclerView2);
        recyclerView2.setAdapter(adapter2);
        adapter3 = new WeeklyAdapter(dbHelper.getAllCalenTypeV2(c3),CalendarList.this, recyclerView3);
        recyclerView3.setAdapter(adapter3);
        adapter4 = new WeeklyAdapter(dbHelper.getAllCalenTypeV2(c4),CalendarList.this, recyclerView4);
        recyclerView4.setAdapter(adapter4);
        adapter5 = new WeeklyAdapter(dbHelper.getAllCalenTypeV2(c5),CalendarList.this, recyclerView5);
        recyclerView5.setAdapter(adapter5);
        adapter6 = new WeeklyAdapter(dbHelper.getAllCalenTypeV2(c6),CalendarList.this, recyclerView6);
        recyclerView6.setAdapter(adapter6);
        adapter7 = new WeeklyAdapter(dbHelper.getAllCalenTypeV2(c7),CalendarList.this, recyclerView7);
        recyclerView7.setAdapter(adapter7);


    }

    @SuppressLint("ResourceAsColor")
    private void btnAction(int position) {
        if (position == 0) {
            task.setTextColor(Color.parseColor("#335c7d"));
            event.setTextColor(R.color.Black);
            note.setTextColor(R.color.Black);
        }
        if (position == 1) {
            task.setTextColor(R.color.Black);
            event.setTextColor(Color.parseColor("#335c7d"));
            note.setTextColor(R.color.Black);
        }
        if (position == 2) {
            task.setTextColor(R.color.Black);
            event.setTextColor(R.color.Black);
            note.setTextColor(Color.parseColor("#335c7d"));
        }


    }
    public static String getdt(){
        return dt;
    }
}
