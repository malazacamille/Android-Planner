package com.example.milleca.mydailyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.*;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FragmentFive extends Fragment{
    private String[] dat,dat1;
    private RecyclerView mRecyclerView;
    private List<String> CalenList,fCalenList;
    private List<Integer> count;
    private String[] cDateSplit;
    private int cDay,cMonth,cYear;
    private String cDate;
    private RecyclerView.LayoutManager mLayoutManager;
    private EverydayDBHelper dbHelper;
    private EventAdapter adapter;


    com.applandeo.materialcalendarview.CalendarView calendarView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_five, container, false);
        calendarView=rootView.findViewById(R.id.calendarView);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.eventRecyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        dbHelper = new EverydayDBHelper(getActivity());
        adapter = new EventAdapter(dbHelper.getAllEvent(""),getActivity(), mRecyclerView);
        mRecyclerView.setAdapter(adapter);

        String [] title=new String[]{"New Year's Day","Chinese New Year","Maunday Thursday","Good Friday","The Day of Valor","Labor Day",
                                        "Independence Day","Ninoy Aquino Day","National Heroes Day","All Saints' Day","All Souls' Day",
                                            "Bonifacio Day","Feast of the Immaculate Conception","Christmas Eve","Christmas Day","Rizal Day","New Year's Eve"};
        String []dates=new String[]{"2018-01-01","2018-02-16","2018-03-29","2018-03-30","2018-04-09","2018-05-01","2018-06-12","2018-08-21","2018-08-27",
                "2018-11-01","2018-11-01","2018-11-30","2018-12-08","2018-12-24","2018-12-25","2018-12-30","2018-12-31"};
        List<String> holDate=new ArrayList<String>();
        List<String> holName=new ArrayList<String>();
        holName.addAll(Arrays.asList(title));
        holDate.addAll(Arrays.asList(dates));
        if(dbHelper.CalenCount()==0){
            for (int i=0;i<holName.size()-1;i++) {
                dbHelper.insertCalen(new Calen(i+1, dates[i], title[i], "holiday"));
            }
        }

        //date count
        count=new ArrayList<>();
        fCalenList=new ArrayList<>();

        //distinct date
        CalenList=dbHelper.getAllDate();
        for(String calen:CalenList) {
            System.out.println(calen);
            cDateSplit = calen.split("-");
            cYear=Integer.parseInt(cDateSplit[0]);
            cMonth=Integer.parseInt(cDateSplit[1]);
            cDay = Integer.parseInt(cDateSplit[2]);
            cDate=cDay+"-"+cMonth+"-"+cYear;
            System.out.println(cDate);
            fCalenList.add(cDate);
            count.add(dbHelper.getDateCount(calen));
        }
        for (Integer counts:count){
            System.out.println(counts);
        }

        //set current month
        try {
            calendarView.setDate(Calendar.getInstance());
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

        List<EventDay> events = new ArrayList<>();

        for(int i=0;i<fCalenList.size();i++) {
            String str_date =fCalenList.get(i);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = null;
            try {
                date = sdf.parse(str_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int img=0;
            if(count.get(i)==1){
                img=R.drawable.one;
            }
            if(count.get(i)==2){
                img=R.drawable.two;
            }
            if(count.get(i)==3){
                img=R.drawable.three;
            }
            if(count.get(i)==4){
                img=R.drawable.four;
            }
            if(count.get(i)==5){
                img=R.drawable.five;
            }
            if(count.get(i)==6){
                img=R.drawable.six;
            }



            events.add(new EventDay(cal,img));

            calendarView.setEvents(events);

        }


        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {

                Calendar clickedDayCalendar = eventDay.getCalendar();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Intent intent=new Intent(getActivity(),CalendarList.class);
                intent.putExtra(CalendarList.EXTRA_CALEN_ID,format.format(clickedDayCalendar.getTime()));
                startActivity(intent);
                System.out.println(Calendar.getInstance());
            }
        });
        return rootView;

    }


}
