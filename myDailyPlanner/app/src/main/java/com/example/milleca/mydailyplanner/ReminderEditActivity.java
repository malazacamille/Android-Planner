package com.example.milleca.mydailyplanner;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
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

/**
 * Created by milleca on 12/23/2017.
 */

public class ReminderEditActivity extends AppCompatActivity implements  View.OnClickListener {
    private Reminder mReceivedReminder;
    private int mReceivedID;
    private EverydayDBHelper rb;
    private AlarmReceiver mAlarmReceiver;

    private Spinner spinner1;
    private EditText mTitleText;
    private TextView mDateText,mTimeText,repeat,notification,priority;
    private Toolbar mToolbar;
    private ImageButton clear;

    private Calendar mCalendar;
    private int eYear,eMonth,eDay;
    private int mHour,mMinute,mYear,mMonth,mDay;
    private String mMonth1,mDay1,eMonth1,eDay1;
    private String[] eDateSplit;
    private String[] mDateSplit;
    private String[] mTimeSplit;
    private long mRepeatTime;
    private String word,mPriority;
    private int num,wordNum;
    private String mTitle;
    private String mRepeat;
    private String mRepeatNo;
    private String mRepeatType;
    private String mActive,mCategory;
    private String mDate,mTime,dDate,dueDt,mFormatDay,mFormatTime,mDueDate,mDue;


    // Constant Intent String
    public static final String EXTRA_REMINDER_ID = "Reminder_ID";

    // Values for orientation change
    private static final String KEY_TITLE = "title_key";
    private static final String KEY_TIME = "time_key";
    private static final String KEY_DATE = "date_key";
    private static final String KEY_REPEAT = "repeat_key";
    private static final String KEY_REPEAT_NO = "repeat_no_key";
    private static final String KEY_REPEAT_TYPE = "repeat_type_key";
    private static final String KEY_ACTIVE = "active_key";

    // Constant values in milliseconds
    private static final long milMinute = 60000L;
    private static final long milHour = 3600000L;
    private static final long milDay = 86400000L;
    private static final long milWeek = 604800000L;
    private static final long milMonth = 2592000000L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_edit);

        // Initialize Views
        spinner1 = findViewById(R.id.spinnerCategory);
        repeat = findViewById(R.id.repeat);
        mTitleText = findViewById(R.id.taskTitle);
        mDateText = findViewById(R.id.date);
        mTimeText = findViewById(R.id.time);
        mToolbar = (Toolbar) findViewById(R.id.ntoolbar);
        notification=findViewById(R.id.notif);
        clear=findViewById(R.id.clear);
        priority=findViewById(R.id.priority);
        mDateText.setOnClickListener(this);
        mTimeText.setOnClickListener(this);

        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.my_statusbar_color));

        // Setup Toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<small>Edit Task</small>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Setup Reminder Title EditText
        mTitleText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTitle = s.toString().trim();
                mTitleText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });



        // Get reminder id from intent
        mReceivedID = Integer.parseInt(getIntent().getStringExtra(EXTRA_REMINDER_ID));

        // Get reminder using reminder id
        rb = new EverydayDBHelper(this);
        mReceivedReminder = rb.getTask(mReceivedID);

        // Get values from reminder
        mCategory=mReceivedReminder.getCategory();
        mTitle=mReceivedReminder.getTitle();
        mDate=mReceivedReminder.getDate();
        mTime=mReceivedReminder.getTime();
        mRepeat=mReceivedReminder.getRepeat();
        mRepeatNo=mReceivedReminder.getRepeatNo();
        mRepeatType=mReceivedReminder.getRepeatType();
        mActive=mReceivedReminder.getActive();
        mDueDate=mReceivedReminder.getDueDate();
        mPriority=mReceivedReminder.getPriotity();
        mDue=mReceivedReminder.getDue();



        mCalendar = Calendar.getInstance();
        eDateSplit = mDue.split("-");
        eYear = Integer.parseInt(eDateSplit[0]);
        eMonth = Integer.parseInt(eDateSplit[1]);
        eDay = Integer.parseInt(eDateSplit[2]);


        mDateSplit = mDate.split("-");
        mTimeSplit = mTime.split(":");

        mYear = Integer.parseInt(mDateSplit[0]);
        mMonth = Integer.parseInt(mDateSplit[1]);
        mDay= Integer.parseInt(mDateSplit[2]);
        mHour = Integer.parseInt(mTimeSplit[0]);
        mMinute = Integer.parseInt(mTimeSplit[1]);


        String mn= String.valueOf(mMonth);
        if(mn.equalsIgnoreCase("01")){
            mMonth=1;
        }
        if(mn.equalsIgnoreCase("02")){
            mMonth=2;
        }
        if(mn.equalsIgnoreCase("03")){
            mMonth=3;
        }
        if(mn.equalsIgnoreCase("04")){
            mMonth=4;
        }
        if(mn.equalsIgnoreCase("05")){
            mMonth=5;
        }
        if(mn.equalsIgnoreCase("06")){
            mMonth=6;
        }
        if(mn.equalsIgnoreCase("07")){
            mMonth=7;
        }
        if(mn.equalsIgnoreCase("08")){
            mMonth=8;
        }
        if(mn.equalsIgnoreCase("09")){
            mMonth=9;
        }

        String emn= String.valueOf(eMonth);
        if(emn.equalsIgnoreCase("01")){
            eMonth=1;
        }
        if(emn.equalsIgnoreCase("02")){
            eMonth=2;
        }
        if(emn.equalsIgnoreCase("03")){
            eMonth=3;
        }
        if(emn.equalsIgnoreCase("04")){
            eMonth=4;
        }
        if(emn.equalsIgnoreCase("05")){
            eMonth=5;
        }
        if(emn.equalsIgnoreCase("06")){
            eMonth=6;
        }
        if(emn.equalsIgnoreCase("07")){
            eMonth=7;
        }
        if(emn.equalsIgnoreCase("08")){
            eMonth=8;
        }
        if(emn.equalsIgnoreCase("09")){
            eMonth=9;
        }

        String dy= String.valueOf(mDay);
        if(dy.equalsIgnoreCase("01")){
            mDay=1;
        }
        if(dy.equalsIgnoreCase("02")){
            mDay=2;
        }
        if(dy.equalsIgnoreCase("03")){
            mDay=3;
        }
        if(dy.equalsIgnoreCase("04")){
            mDay=4;
        }
        if(dy.equalsIgnoreCase("05")){
            mDay=5;
        }
        if(dy.equalsIgnoreCase("06")){
            mDay=6;
        }
        if(dy.equalsIgnoreCase("07")){
            mDay=7;
        }
        if(dy.equalsIgnoreCase("08")){
            mDay=8;
        }
        if(dy.equalsIgnoreCase("09")){
            mDay=9;
        }
        String edy= String.valueOf(eDay);
        if(edy.equalsIgnoreCase("01")){
            eDay=1;
        }
        if(edy.equalsIgnoreCase("02")){
            eDay=2;
        }
        if(edy.equalsIgnoreCase("03")){
            eDay=3;
        }
        if(edy.equalsIgnoreCase("04")){
            eDay=4;
        }
        if(edy.equalsIgnoreCase("05")){
            eDay=5;
        }
        if(edy.equalsIgnoreCase("06")){
            eDay=6;
        }
        if(edy.equalsIgnoreCase("07")){
            eDay=7;
        }
        if(edy.equalsIgnoreCase("08")){
            eDay=8;
        }
        if(edy.equalsIgnoreCase("09")){
            eDay=9;
        }

        if(mDay<10){
            mDay1="0"+mDay;
        }else{
            mDay1=""+mDay;
        }
        if(mMonth<10){
            mMonth1="0"+mMonth;
        }
        else {
            mMonth1=""+mMonth;
        }

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

        Log.e("nskdnds", String.valueOf(mDay));
        Log.e("month", String.valueOf(mMonth));
        Log.e("nskdnds", String.valueOf(mYear));
        Log.e("nskdnds", String.valueOf(mHour));
        Log.e("nskdnds", String.valueOf(mMinute));
        Log.e("nskdnds", String.valueOf(eMonth));
        Log.e("nskdnds", String.valueOf(eDay));


        Calendar cal = new GregorianCalendar(mYear, mMonth-1, mDay);
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String month_name = month_date.format(cal.getTime());
        String[] days = new String[]{"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        String day = days[cal.get(Calendar.DAY_OF_WEEK)];
        mFormatDay=day + ", " + month_name + " " + mDay1 + ", " + mYear;
        mDate=mYear+"-"+mMonth1+"-"+mDay1;
        mDue=eYear+"="+eMonth1+"-"+eDay1;

        mTime=mHour+":"+mMinute;
        mFormatTime=mHour+":"+mMinute;
        dueDt=eMonth1+"/"+eDay1;
        // Setup TextViews using reminder values
        mTitleText.setText(mTitle);
        mDateText.setText(mFormatDay);
        mTimeText.setText(mFormatTime);
        priority.setText(mPriority);


        mAlarmReceiver = new AlarmReceiver();

        if(mActive.equalsIgnoreCase("true")){
            mDateText.setVisibility(View.VISIBLE);
            mTimeText.setVisibility(View.VISIBLE);
            clear.setVisibility(View.VISIBLE);
            notification.setVisibility(View.INVISIBLE);
        }
        if(mActive.equalsIgnoreCase("false"))
        {
            notification.setVisibility(View.VISIBLE);
            mDateText.setVisibility(View.INVISIBLE);
            mTimeText.setVisibility(View.INVISIBLE);
            clear.setVisibility(View.INVISIBLE);
        }

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDateText.setVisibility(View.VISIBLE);
                mTimeText.setVisibility(View.VISIBLE);
                clear.setVisibility(View.VISIBLE);
                notification.setVisibility(View.INVISIBLE);
                mActive="true";
            }
        });



        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification.setVisibility(View.VISIBLE);
                mDateText.setVisibility(View.INVISIBLE);
                mTimeText.setVisibility(View.INVISIBLE);
                clear.setVisibility(View.INVISIBLE);
                mActive="false";
            }
        });

        if(mRepeat.equalsIgnoreCase("true")){
            repeat.setText("repeat every "+mRepeatNo+" "+mRepeatType+"(s)");
        }
        else {
            repeat.setText("One-time Task");
        }



        //Priority
        priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ReminderEditActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.priority_dialog, null);

                final NumberPicker numberPicker=mView.findViewById(R.id.numberPicker);
                final NumberPicker letterPicker=mView.findViewById(R.id.letterPicker);
                final TextView textView=mView.findViewById(R.id.save);

                numberPicker.setMinValue(1);
                numberPicker.setMaxValue(100);
                numberPicker.setWrapSelectorWheel(false);
                numberPicker.setValue(1);

                final String[] values= {"A","B", "C", "D", "E"};
                letterPicker.setMinValue(0);
                letterPicker.setValue(0);
                letterPicker.setMaxValue(values.length-1);
                letterPicker.setDisplayedValues(values);
                letterPicker.setWrapSelectorWheel(false);



                numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                        //Display the newly selected number from picker
                        num=newVal;
                        Log.i("value is",""+num);
                    }
                });

                letterPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                        //Display the newly selected value from picker
                        if (newVal == 0) {
                            word="A";
                            wordNum=0;
                        }
                        if (newVal == 1) {
                            word="B";
                            wordNum=1;
                        }
                        if (newVal == 2) {
                            word="C";
                            wordNum=2;
                        }
                        if (newVal == 3) {
                            wordNum=3;
                        }
                        if (newVal == 4) {
                            word="E";
                            wordNum=4;
                        }
                        Log.i("word is",""+word);

                        Log.i("wordnum is",""+wordNum);
                    }
                });

                numberPicker.setValue(num);
                letterPicker.setValue(wordNum);


                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.getWindow().setLayout(600, 540);



                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        mPriority=word+num;
                        Log.i("mPriority",mPriority);
                        prior(mPriority);
                    }
                });


            }
        });



        //Task Category
        final ArrayList<SpinnerData> customList1 = new ArrayList<>();
        customList1.add(new SpinnerData(0, "Choose a category"));
        customList1.add(new SpinnerData(R.drawable.sam2, "Personal Errands"));
        customList1.add(new SpinnerData(R.drawable.sam3, "Work"));
        customList1.add(new SpinnerData(R.drawable.sam4, "Health"));
        customList1.add(new SpinnerData(R.drawable.sam5, "School"));
        customList1.add(new SpinnerData(R.drawable.sam6, "Others"));

        final CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(ReminderEditActivity.this,
                R.layout.spinner_layout, R.id.textView, customList1);
        spinner1.setAdapter(adapter);
        if (mCategory.equalsIgnoreCase("Personal Errands")){
            spinner1.setSelection(1);
        }
        if(mCategory.equalsIgnoreCase("Work")){
            spinner1.setSelection(2);
        }
        if (mCategory.equalsIgnoreCase("Health")){
            spinner1.setSelection(3);
        }
        if(mCategory.equalsIgnoreCase("School")){
            spinner1.setSelection(4);
        }
        if(mCategory.equalsIgnoreCase("Others")){
            spinner1.setSelection(5);
        }


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    mCategory="";
                }else {
                    mCategory = customList1.get(i).getIconName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //Repeat category
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(ReminderEditActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.repeat_dialog, null);


                final List<String> catList = new ArrayList<String>();
                catList.add("Minute");
                catList.add("Hour");
                catList.add("Day");
                catList.add("Week");
                catList.add("Month");

                final List<String> dueList = new ArrayList<String>();
                dueList.add("Forever");
                dueList.add("Until a date");

                final Switch Repeat = mView.findViewById(R.id.switch1);
                final EditText RepeatNo = (EditText) mView.findViewById(R.id.RepeatNo);
                final Spinner RepeatType = mView.findViewById(R.id.repeat_type);
                final Spinner RepeatDue = mView.findViewById(R.id.repeat_due);
                final TextView dueDate = mView.findViewById(R.id.dueDate);
                final TextView evry=mView.findViewById(R.id.evry);
                final TextView every=mView.findViewById(R.id.repeat_type_text);
                final TextView done = mView.findViewById(R.id.done);

                final ArrayAdapter<String> catAdapter = new ArrayAdapter<>(ReminderEditActivity.this, R.layout.support_simple_spinner_dropdown_item, catList);
                final ArrayAdapter<String> dueAdapter = new ArrayAdapter<>(ReminderEditActivity.this, R.layout.support_simple_spinner_dropdown_item, dueList);
                RepeatType.setAdapter(catAdapter);
                RepeatDue.setAdapter(dueAdapter);

                //set values
                RepeatNo.setText(mRepeatNo);
                dueDate.setText(eMonth+"/"+eDay);

                if(mRepeat.equalsIgnoreCase("true")){
                    Repeat.setChecked(true);
                }else{
                    Repeat.setChecked(false);
                }

                int spinnerPosition1 = catAdapter.getPosition(mRepeatType);
                RepeatType.setSelection(spinnerPosition1);

                int spinnerPosition2 = dueAdapter.getPosition(mDueDate);
                RepeatDue.setSelection(spinnerPosition2);

                //dueDate date picker
                dueDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(ReminderEditActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                monthOfYear++;
                                eYear=year;
                                eMonth=monthOfYear;
                                eDay=dayOfMonth;
                                if(eDay<10){
                                    eDay1="0"+eDay;
                                }else{
                                    eDay1=""+eDay;
                                }
                                if(eMonth<10){
                                    eMonth1="0"+eMonth;
                                }
                                else {
                                    eMonth1 = "" + eMonth;
                                }
                                mDue=eYear+"-"+eMonth1+"-"+eDay1;
                                dDate = eMonth1 + "/" + eDay1;
                                dueDate.setText(dDate);

                            }
                        }, eYear,eMonth-1,eDay);
                        datePickerDialog.show();
                    }
                });


                Repeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Repeat.isChecked()){
                            every.setTextColor(Color.parseColor("#333333"));
                            evry.setTextColor(Color.parseColor("#333333"));
                            RepeatNo.setTextColor(Color.parseColor("#333333"));
                            RepeatNo.setEnabled(true);
                            RepeatType.setEnabled(true);
                            RepeatDue.setEnabled(true);
                            mRepeat="true";

                        } else {
                            every.setTextColor(Color.parseColor("#808080"));
                            evry.setTextColor(Color.parseColor("#808080"));
                            RepeatNo.setTextColor(Color.parseColor("#808080"));
                            RepeatNo.setEnabled(false);
                            RepeatType.setEnabled(false);
                            RepeatDue.setEnabled(false);
                            mRepeat="false";
                            mDue="";
                        }
                    }
                });


                RepeatType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(i==0){
                            mRepeatType="";
                        }else{
                            every.setText(catList.get(i)+"(s)");
                            mRepeatType=catList.get(i);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                RepeatDue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        mDueDate=dueList.get(i);
                        if (i == 0) {
                            dueDate.setVisibility(View.INVISIBLE);
                            dueDate.setText("");
                        } else if (i == 1) {
                            dueDate.setVisibility(View.VISIBLE);
                            dueDate.setText(dueDt);
                        } else {
                            dueDate.setVisibility(View.INVISIBLE);
                            dueDate.setText("");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }

                });


                mBuilder.setView(mView);
                final android.app.AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.getWindow().setLayout(700, 550);

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mRepeatNo=RepeatNo.getText().toString();
                        output(mRepeat,mRepeatType,mRepeatNo,dDate,mDueDate);
                        if(Repeat.isChecked()){
                            repeatTitle("repeat every "+mRepeatNo+" "+mRepeatType+"(s)");
                        }else {
                            repeatTitle("One-time Task");
                        }
                        dialog.dismiss();



                    }
                });
            }
        });

    }

    public  void prior(String mPriority){
        this.mPriority=mPriority;
        priority.setText(mPriority);

    }


    public void output (String repeat,String mRepeatType, String mRepeatNo, String dueDt, String mDueDate){
        this.mRepeat=repeat;
        this.mRepeatType=mRepeatType;
        this.mRepeatNo=mRepeatNo;
        this.dueDt=dueDt;
        this.mDueDate=mDueDate;
    }
    public void repeatTitle(String txt){
        repeat.setText(txt);
    }

    // On clicking the update button
    public void updateReminder(){
        mMonth=mMonth+1;
        System.out.println("Title: "+mTitle);
        System.out.println("Category: "+mCategory);
        System.out.println("Date: "+mDate);
        System.out.println("Time: "+mTime);
        System.out.println("Active: "+mActive);
        System.out.println("RepeaType: "+mRepeatType);
        System.out.println("RepeatNo: "+mRepeatNo);
        System.out.println("dueDate: "+dDate);
        System.out.println("Repeat: "+mRepeat);
        System.out.println("mDueDate:"+mDueDate);


        // Set new values in the reminder
        mReceivedReminder.setTitle(mTitle);
        mReceivedReminder.setCategory(mCategory);
        mReceivedReminder.setDate(mDate);
        mReceivedReminder.setTime(mTime);
        mReceivedReminder.setRepeat(mRepeat);
        mReceivedReminder.setRepeatNo(mRepeatNo);
        mReceivedReminder.setRepeatType(mRepeatType);
        mReceivedReminder.setActive(mActive);
        mReceivedReminder.setDueDate(mDueDate);

        // Update reminder
        rb.updateReminder(mReceivedReminder);
        rb.deleteCalen(mReceivedID);
        if(mDueDate.equalsIgnoreCase("")&& mActive.equalsIgnoreCase("true")) {
            rb.insertCalen(new Calen(mReceivedID, mDate, mTitle, "task"));
        }if(mDueDate.equalsIgnoreCase("Until a date")&& mActive.equalsIgnoreCase("true")){
            List<String> dates = getDates(mDate, mDue);
            for(String date:dates) {
                rb.insertCalen(new Calen(mReceivedID,date,mTitle,"task"));
                System.out.println(date);
            }
        }

        // Set up calender for creating the notification
        mCalendar.set(Calendar.MONTH, --mMonth);
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);

        System.out.println("Month"+mMonth);
        System.out.println("Year:"+mYear);
        System.out.println("Day:"+mDay);
        System.out.println("Hour:"+mHour);
        System.out.println("minute:"+mMinute);
        System.out.println("eMonth"+eMonth);
        System.out.println("eDay"+eDay);
        // Cancel existing notification of the reminder by using its ID
        mAlarmReceiver.cancelAlarm(getApplicationContext(), mReceivedID);

        // Check repeat type
        if (mRepeatType.equals("Minute")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milMinute;
        } else if (mRepeatType.equals("Hour")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milHour;
        } else if (mRepeatType.equals("Day")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milDay;
        } else if (mRepeatType.equals("Week")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milWeek;
        } else if (mRepeatType.equals("Month")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milMonth;
        }

        // Create a new notification
        if (mActive.equals("true")) {
            if (mRepeat.equals("true")) {
                mAlarmReceiver.setRepeatAlarm(getApplicationContext(), mCalendar, mReceivedID, mRepeatTime);
            } else if (mRepeat.equals("false")) {
                mAlarmReceiver.setAlarm(getApplicationContext(), mCalendar, mReceivedID);
            }
        }

        // Create toast to confirm update
        Toast.makeText(getApplicationContext(), "Edited",
                Toast.LENGTH_SHORT).show();
        onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
                mTitleText.setText(mTitle);

                if (mTitleText.getText().toString().length() == 0)
                    mTitleText.setError("Reminder Title cannot be blank!");

                else {
                    updateReminder();
                }
                return true;
            case R.id.delete:
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                builder.setMessage("delete task?")
                        .setPositiveButton("delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                rb.deleteReminder(mReceivedID);
                                Toast.makeText(getApplicationContext(), "Deleted",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ReminderEditActivity.this,ContentTask.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                // Create the AlertDialog object and return it
                final android.app.AlertDialog dialog = builder.create();
                dialog.show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        Calendar now=Calendar.getInstance();
        if(v==mDateText){
            DatePickerDialog datePickerDialog=new DatePickerDialog(this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    monthOfYear++;
                    mDay=dayOfMonth;
                    mYear=year;
                    mMonth=monthOfYear;
                    if(mDay<10){
                        mDay1="0"+mDay;
                    }else{
                        mDay1=""+mDay;
                    }
                    if(eMonth<10){
                        mMonth1="0"+mMonth;
                    }
                    else {
                        mMonth1=""+mMonth;
                    }
                    Calendar cal = new GregorianCalendar(mYear, mMonth-1, mDay);
                    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                    int monThName=cal.get(Calendar.MONTH);
                    String[] months=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                    String[] days = new String[]{"","Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                    mDateText.setText(days[dayOfWeek] + ", " + months[monThName] +" " + mDay1 + ", " + mYear);
                    mDate=mYear+"-"+mMonth1+"-"+mDay1;
                }
            }
                    ,mYear,
                    mMonth-1,
                    mDay);
            datePickerDialog.show();
        }
        if(v==mTimeText){
            TimePickerDialog timePickerDialog=new TimePickerDialog(this,R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    mHour=hourOfDay;
                    mMinute=minute;

                    if(minute<10){
                        mTime=hourOfDay+":0"+minute;
                        mTimeText.setText(mTime);
                    }else
                        mTime=hourOfDay+":"+minute;
                    mTimeText.setText(mTime);
                }
            },mHour,mMinute,true);
            timePickerDialog.show();
        }
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
    @Override
    public void onBackPressed() {
        if(mCategory.equalsIgnoreCase("")){
            Intent intent=new Intent(ReminderEditActivity.this,ContentTask.class);
            startActivityForResult(intent,1);
        }
        if(mCategory.equalsIgnoreCase("Personal Errands")){
            Intent intent=new Intent(ReminderEditActivity.this,ContentPersonal.class);
            startActivityForResult(intent,1);
        }
        if(mCategory.equalsIgnoreCase("Work")){
            Intent intent=new Intent(ReminderEditActivity.this,ContentWork.class);
            startActivityForResult(intent,1);
        }
        if(mCategory.equalsIgnoreCase("Health")){
            Intent intent=new Intent(ReminderEditActivity.this,ContentHealth.class);
            startActivityForResult(intent,1);
        }
        if(mCategory.equalsIgnoreCase("School")){
            Intent intent=new Intent(ReminderEditActivity.this,ContentSchool.class);
            startActivityForResult(intent,1);
        }
        if(mCategory.equalsIgnoreCase("Others")){
            Intent intent=new Intent(ReminderEditActivity.this,ContentOthers.class);
            startActivityForResult(intent,1);
        }
    }
    }


