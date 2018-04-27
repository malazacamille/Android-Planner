package com.example.milleca.mydailyplanner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class EventAddActivity extends AppCompatActivity implements View.OnClickListener
{
    android.support.v7.widget.Toolbar eToolbar;
    EditText eTitleTxt,eNoteTxt,eLocationTxt;
    ImageView icon_img;
    CheckBox allDay;
    TextView startDateTxt,endDateTxt,startTimeTxt,endTimeTxt,eNotificationTxt,eAvailableTxt,eIconTxt;
    String eTitle,eNote,eLocation,eIcon,eAllDay,eStartDate,eEndDate,eStartTime,eEndTime,eNotification,eAvailable,eActive;
    Calendar sCalendar,eCalendar;
    String eFormatStartDay,eFormatEndDay;
    int sYear,sDay,sMonth,sHour,sMinute;
    int eYear,eDay,eMonth,eHour,eMinute;
    int notif;
    int icon;
    String sMOnth1,sDay1,eMonth1,eDay1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_add);
        eTitleTxt=findViewById(R.id.eventTitle);
        eNoteTxt=findViewById(R.id.addNote);
        eLocationTxt=findViewById(R.id.location);
        icon_img=findViewById(R.id.icon_img);
        allDay=findViewById(R.id.allday_checkbox);
        startDateTxt=findViewById(R.id.start_date);
        endDateTxt=findViewById(R.id.end_date);
        startTimeTxt=findViewById(R.id.start_time);
        endTimeTxt=findViewById(R.id.end_time);
        eNotificationTxt=findViewById(R.id.notification);
        eAvailableTxt=findViewById(R.id.available);
        eToolbar=findViewById(R.id.ntoolbar);
        eIconTxt=findViewById(R.id.icon_txt);
        eIconTxt.setOnClickListener(this);
        startDateTxt.setOnClickListener(this);
        endDateTxt.setOnClickListener(this);
        startTimeTxt.setOnClickListener(this);
        endTimeTxt.setOnClickListener(this);
        eNotificationTxt.setOnClickListener(this);
        eAvailableTxt.setOnClickListener(this);

        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.my_statusbar_color));

        // Setup Toolbar
        setSupportActionBar(eToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<small>Add New Event</small>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Setup Event Title EditText
        eTitleTxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eTitle = s.toString().trim();
                eTitleTxt.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Setup addNote EditText
        eNoteTxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eNote = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Setup location EditText
        eLocationTxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eLocation = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Initialize default values
        eActive = "false";
        eAllDay="false";
        eIcon="icon1";
        eAvailable="available";

        //set start Calendar
        sCalendar = Calendar.getInstance();
        sMonth = sCalendar.get(Calendar.MONTH)+1;
        sYear = sCalendar.get(Calendar.YEAR);
        sDay = sCalendar.get(Calendar.DAY_OF_MONTH);
        sHour =sCalendar.get(Calendar.HOUR_OF_DAY);
        sMinute = sCalendar.get(Calendar.MINUTE);
        //set end Calendar
        eCalendar = Calendar.getInstance();
        eMonth = eCalendar.get(Calendar.MONTH)+1;
        eYear = eCalendar.get(Calendar.YEAR);
        eDay = eCalendar.get(Calendar.DAY_OF_MONTH);
        eHour =eCalendar.get(Calendar.HOUR_OF_DAY);
        eMinute = eCalendar.get(Calendar.MINUTE);

        if(sDay<10){
            sDay1="0"+sDay;
        }else{
            sDay1=""+sDay;
        }
        if(sMonth<10){
            sMOnth1="0"+sMonth;
        }
        else {
            sMOnth1=""+sMonth;
        }
        //start date format
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String month_name = month_date.format(sCalendar.getTime());
        String[] days = new String[]{"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        String day = days[sCalendar.get(Calendar.DAY_OF_WEEK)];
        eFormatStartDay=day + ", " + month_name + " " + sDay1 + ", " + sYear;
        eStartDate=sYear+"-"+sMOnth1+"-"+sDay1;

        if(eDay<10){
            eDay1="0"+eDay;
        }else{
            eDay1=""+eDay;
        }
        if(eMonth<10){
            eMonth1="0"+eMonth;
        }
        else {
            eMonth1=""+eMonth;
        }


        //end date format
        SimpleDateFormat month_date1 = new SimpleDateFormat("MMM");
        String month_name1 = month_date1.format(eCalendar.getTime());
        String[] days1 = new String[]{"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        String day1 = days1[eCalendar.get(Calendar.DAY_OF_WEEK)];
        eFormatEndDay=day1 + ", " + month_name1 + " " + eDay1 + ", " + eYear;
        eEndDate=eYear+"-"+eMonth1+"-"+eDay1;

        startDateTxt.setText(eFormatStartDay);
        endDateTxt.setText(eFormatEndDay);
        eStartTime=sHour+":"+sMinute;
        eEndTime=eHour+":"+eMinute;
        startTimeTxt.setText(eStartTime);
        endTimeTxt.setText(eEndTime);
        eIconTxt.setText(eIcon);

        //icon
        final ArrayList<SpinnerData> customList1 = new ArrayList<>();
        customList1.add(new SpinnerData(R.drawable.ic1, "Personal Errands"));
        customList1.add(new SpinnerData(R.drawable.sam3, "Work"));
        customList1.add(new SpinnerData(R.drawable.sam4, "Health"));
        customList1.add(new SpinnerData(R.drawable.sam5, "School"));
        customList1.add(new SpinnerData(R.drawable.sam6, "Others"));


        allDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allDay.isChecked()){
                    eAllDay="true";
                    startTimeTxt.setVisibility(View.INVISIBLE);
                    endTimeTxt.setVisibility(View.INVISIBLE);
                }
                else{
                    eAllDay="false";
                    startTimeTxt.setVisibility(View.VISIBLE);
                    endTimeTxt.setVisibility(View.VISIBLE);
                }
            }
        });

        System.out.println("title"+eTitle);
        System.out.println("note "+eNote);
        System.out.println("icon "+eIcon);
        System.out.println("allday "+eAllDay);
        System.out.println("sdate "+eStartDate);
        System.out.println("edate "+eEndDate);
        System.out.println("stime "+eStartTime);
        System.out.println("etime "+eEndTime);
        System.out.println("elocation "+eLocation);
        System.out.println("notification "+eNotification);
        System.out.println("eactive "+eActive);
        System.out.println("notif "+notif);
        System.out.println("available "+eAvailable);

    }

    @Override
    public void onClick(View v) {
        if(v==startDateTxt){
            DatePickerDialog datePickerDialog=new DatePickerDialog(this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    monthOfYear++;
                    sDay=dayOfMonth;
                    sYear=year;
                    sMonth=monthOfYear;
                    if(sDay<10){
                        sDay1="0"+sDay;
                    }else{
                        sDay1=""+sDay;
                    }
                    if(sMonth<10){
                        sMOnth1="0"+sMonth;
                    }
                    else {
                        sMOnth1=""+sMonth;
                    }

                    Calendar cal = new GregorianCalendar(sYear, sMonth-1, sDay);
                    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                    int monThName=cal.get(Calendar.MONTH);
                    String[] months=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                    String[] days = new String[]{"","Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                    eStartDate=sYear+"-"+sMOnth1+"-"+sDay1;
                    startDateTxt.setText(days[dayOfWeek] + ", " + months[monThName] +" " + sDay1 + ", " + sYear);
                }
            }
                    ,sYear,
                    sMonth-1,
                    sDay);
            datePickerDialog.show();
        }

        if(v==endDateTxt){
            DatePickerDialog datePickerDialog=new DatePickerDialog(this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    monthOfYear++;
                    eDay=dayOfMonth;
                    eYear=year;
                    eMonth=monthOfYear;
                    if(eDay<10){
                        eDay1="0"+eDay;
                    }else{
                        eDay1=""+eDay;
                    }
                    if(eMonth<10){
                        eMonth1="0"+eMonth;
                    }
                    else {
                        eMonth1=""+eMonth;
                    }

                    Calendar cal = new GregorianCalendar(eYear, eMonth-1, eDay);
                    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                    int monThName=cal.get(Calendar.MONTH);
                    String[] months=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                    String[] days = new String[]{"","Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                    eEndDate=eYear+"-"+eMonth1+"-"+eDay1;
                    endDateTxt.setText(days[dayOfWeek] + ", " + months[monThName] +" " + eDay1 + ", " + eYear);
                }
            }
                    ,eYear,
                    eMonth-1,
                    eDay);
            datePickerDialog.show();
        }

        if(v==startTimeTxt){
            TimePickerDialog timePickerDialog=new TimePickerDialog(this,R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    sHour=hourOfDay;
                    sMinute=minute;
                    if(minute<10){
                        eStartTime=hourOfDay+":0"+minute;
                        startTimeTxt.setText("0"+hourOfDay+":0"+minute);
                    }else
                        eStartTime=hourOfDay+":"+minute;
                    startTimeTxt.setText(hourOfDay+":"+minute);
                }
            },sHour,sMinute,true);
            timePickerDialog.show();
        }
        if(v==endTimeTxt){
            TimePickerDialog timePickerDialog=new TimePickerDialog(this,R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    eHour=hourOfDay;
                    eMinute=minute;
                    if(minute<10){
                        eEndTime=hourOfDay+":0"+minute;
                        endTimeTxt.setText("0"+hourOfDay+":0"+minute);
                    }else
                        eEndTime=hourOfDay+":"+minute;
                    endTimeTxt.setText(hourOfDay+":"+minute);
                }
            },eHour,eMinute,true);
            timePickerDialog.show();
        }
        if(v==eNotificationTxt){
            final String[] items = new String[6];

            items[0] = "no notification";
            items[1] = "At time of event";
            items[2] = "15 minutes before";
            items[3] = "30 minutes before";
            items[4] = "1 hour before";
            items[5] = "1 day before";

            // Create List Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setItems(items, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int item) {

                    eNotification = items[item];
                    eNotificationTxt.setText(eNotification);
                    if(item==0){
                        eActive="false";
                        notif=0;
                    }
                    if(item==1){
                        eActive="true";
                        notif=0;
                    }
                    if(item==2){
                        eActive="true";
                        notif=15;
                    }
                    if(item==3){
                        eActive="true";
                        notif=30;
                    }
                    if(item==4){
                        eActive="true";
                        notif=60;
                    }
                    if(item==4){
                        eActive="true";
                        notif=1;
                    }
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        if(v==eAvailableTxt){
            final String[] items = new String[2];

            items[0] = "busy";
            items[1] = "available";

            // Create List Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setItems(items, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int item) {

                    eAvailable = items[item];
                    eAvailableTxt.setText(eAvailable);
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        if(v==eIconTxt){
            android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(EventAddActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.icon_item, null);

            final ImageView ic1=mView.findViewById(R.id.ic1);
            final ImageView ic2=mView.findViewById(R.id.ic2);
            final ImageView ic3=mView.findViewById(R.id.ic3);
            final ImageView ic4=mView.findViewById(R.id.ic4);
            final ImageView ic5=mView.findViewById(R.id.ic5);
            final ImageView ic6=mView.findViewById(R.id.ic6);
            final ImageView ic7=mView.findViewById(R.id.ic7);
            final ImageView ic8=mView.findViewById(R.id.ic8);
            final ImageView ic9=mView.findViewById(R.id.ic9);
            final ImageView ic10=mView.findViewById(R.id.ic10);
            final ImageView ic11=mView.findViewById(R.id.ic11);
            final ImageView ic12=mView.findViewById(R.id.ic12);
            final ImageView ic13=mView.findViewById(R.id.ic13);
            final ImageView ic14=mView.findViewById(R.id.ic14);
            final ImageView ic15=mView.findViewById(R.id.ic15);
            final ImageView ic16=mView.findViewById(R.id.ic16);
            final ImageView ic17=mView.findViewById(R.id.ic17);
            final ImageView ic18=mView.findViewById(R.id.ic18);

            mBuilder.setView(mView);
            final android.app.AlertDialog dialog = mBuilder.create();
            dialog.show();
            dialog.getWindow().setLayout(650, 600);

            ic1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon1";
                    icon=R.drawable.ic1;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon2";
                    icon=R.drawable.ic2;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon3";
                    icon=R.drawable.ic3;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon4";
                    icon=R.drawable.ic4;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon5";
                    icon=R.drawable.ic5;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon6";
                    icon=R.drawable.ic6;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon7";
                    icon=R.drawable.ic7;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon8";
                    icon=R.drawable.ic8;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon9";
                    icon=R.drawable.ic9;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon10";
                    icon=R.drawable.ic10;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon11";
                    icon=R.drawable.ic11;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon12";
                    icon=R.drawable.ic12;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon13";
                    icon=R.drawable.ic13;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon14";
                    icon=R.drawable.ic14;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon15";
                    icon=R.drawable.ic15;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon16";
                    icon=R.drawable.ic16;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon17";
                    icon=R.drawable.ic17;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });
            ic18.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eIcon="icon18";
                    icon=R.drawable.ic18;
                    seticon(eIcon,icon);
                    dialog.dismiss();
                }
            });



        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    // On clicking menu buttons
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // On clicking the back arrow
            // Discard any changes
            case android.R.id.home:
                onBackPressed();
                Toast.makeText(getApplicationContext(), "Discarded",
                        Toast.LENGTH_SHORT).show();
                return true;

            // On clicking save reminder button
            // Update reminder
            case R.id.save_reminder:

                if (eTitleTxt.getText().toString().length() == 0)
                    eTitleTxt.setError("event name cannot be blank!");

                else {
                    saveEvent();
                }
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void seticon(String iconTxt,int img){
        eIcon=iconTxt;
        icon=img;
        eIconTxt.setText(eIcon);
        icon_img.setBackgroundResource(icon);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(EventAddActivity.this,ActivityOne.class);
        startActivity(intent);
    }

    public void saveEvent() {
        System.out.println("title"+eTitle);
        System.out.println("note "+eNote);
        System.out.println("icon "+eIcon);
        System.out.println("allday "+eAllDay);
        System.out.println("sdate "+eStartDate);
        System.out.println("edate "+eEndDate);
        System.out.println("stime "+eStartTime);
        System.out.println("etime "+eEndTime);
        System.out.println("elocation "+eLocation);
        System.out.println("notification "+eNotification);
        System.out.println("eactive "+eActive);
        System.out.println("notif "+notif);
        System.out.println("available "+eAvailable);
        System.out.println(sDay);
        System.out.println(sMonth);
        System.out.println(eHour);
        System.out.println(eDay);


        EverydayDBHelper rb = new EverydayDBHelper(this);
        int id= (int) System.currentTimeMillis();
        int ID=rb.insertEvent(new Event(id,eTitle,eNote,eIcon,eAllDay,eStartDate,eEndDate,eStartTime,eEndTime,eLocation,eNotification,notif,eActive,eAvailable));

        List<String> dates = getDates(eStartDate, eEndDate);
        for(String date:dates) {
           rb.insertCalen(new Calen(id,date,eTitle,"event"));
           System.out.println(date);
        }

        // Set up calender for creating the notification
        sCalendar.set(Calendar.MONTH, --sMonth);
        sCalendar.set(Calendar.YEAR, sYear);
        sCalendar.set(Calendar.DAY_OF_MONTH, sDay);
        if(eAllDay.equalsIgnoreCase("true")) {
            sCalendar.set(Calendar.HOUR_OF_DAY, 8);
            sCalendar.set(Calendar.MINUTE, 0);
            sCalendar.set(Calendar.AM_PM,0);
        }
        if(eAllDay.equalsIgnoreCase("false")) {
            sCalendar.set(Calendar.HOUR_OF_DAY, sHour);
            sCalendar.set(Calendar.MINUTE, sMinute);
        }
        sCalendar.set(Calendar.SECOND,0);


        if(notif==15) {
            sCalendar.add(Calendar.MINUTE, -15);
        }
        if(notif==30){
            sCalendar.add(Calendar.MINUTE, -30);
        }
        if(notif==60){
            sCalendar.add(Calendar.HOUR, -1);
        }
        if(notif==1){
            sCalendar.add(Calendar.DAY_OF_MONTH, -1);
        }

        eCalendar.set(Calendar.MONTH,--eMonth);
        eCalendar.set(Calendar.YEAR,eYear);
        eCalendar.set(Calendar.DAY_OF_MONTH,eDay);
        if(eAllDay.equalsIgnoreCase("true")) {
            eCalendar.set(Calendar.HOUR_OF_DAY, 5);
            eCalendar.set(Calendar.MINUTE, 0);
            eCalendar.set(Calendar.AM_PM,1);
        }
        if(eAllDay.equalsIgnoreCase("false")) {
            eCalendar.set(Calendar.HOUR_OF_DAY, eHour);
            eCalendar.set(Calendar.MINUTE, eMinute);
        }
        eCalendar.set(Calendar.SECOND,0);

        if (eActive.equals("true")) {
            new EventReceiver().setAlarm(getApplicationContext(), sCalendar, ID);
            new EventReceiver().setEndAlarm(getApplicationContext(), eCalendar, ID-50000,ID);
        }
        // Create toast to confirm new reminder
        Toast.makeText(getApplicationContext(), "Saved",
                Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
    private static List<String> getDates(String dateString1, String dateString2)
    {
        ArrayList<String> dates = new ArrayList<String>();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1 .parse(dateString1);
            date2 = df1 .parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        while(!cal1.after(cal2))
        {
            dates.add(String.valueOf(format.format(cal1.getTime())));
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }
}