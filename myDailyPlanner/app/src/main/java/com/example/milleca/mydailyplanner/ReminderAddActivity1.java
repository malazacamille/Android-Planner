package com.example.milleca.mydailyplanner;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
 * Created by milleca on 1/18/2018.
 */

public class ReminderAddActivity1 extends AppCompatActivity implements View.OnClickListener{
    private Spinner spinner1;
    private EditText mTitleText;
    private TextView mDateText,mTimeText,repeat,notification,priority;
    private Toolbar mToolbar;
    private ImageButton clear;

    private Calendar mCalendar,eCalendar;
    private int eYear,eMonth,eDay;
    private int mHour,mMinute,mYear,mMonth,mDay;
    private String mMonth1,mDayl,eMonth1,eDay1;
    private long mRepeatTime;
    private String word,mPriority;
    private int num,wordNum;
    private String mTitle;
    private String mRepeat;
    private String mRepeatNo;
    private String mRepeatType;
    private String mActive,mCategory;
    private String mDate,mTime,dDate,dueDt,mFormatDay,mFormatTime,mDueDate,mDue;


    // Constant values in milliseconds
    private static final long milMinute = 60000L;
    private static final long milHour = 3600000L;
    private static final long milDay = 86400000L;
    private static final long milWeek = 604800000L;
    private static final long milMonth = 2592000000L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_add);

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

        // Setup Toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<small>Add New Task</small>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.my_statusbar_color));

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


        //Initialize default values
        mActive = "false";
        mRepeat = "false";
        mRepeatNo = Integer.toString(1);
        mRepeatType ="Hour";
        dDate="";
        mDueDate="";
        mPriority="A1";
        num=1;
        wordNum=0;
        repeat.setText("One-time Task");

        //set Calendar
        mCalendar = Calendar.getInstance();
        mMonth = mCalendar.get(Calendar.MONTH)+1;
        mYear = mCalendar.get(Calendar.YEAR);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);

        if(mDay<10){
            mDayl="0"+mDay;
        }else{
            mDayl=""+mDay;
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

        //date format
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String month_name = month_date.format(mCalendar.getTime());
        String[] days = new String[]{"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        String day = days[mCalendar.get(Calendar.DAY_OF_WEEK)];
        mFormatDay=day + ", " + month_name + " " + mDayl + ", " + mYear;
        mDate=mYear+"-"+mMonth1+"-"+mDayl;
        mDue=eYear+"-"+eMonth1+"-"+eDay1;
        mTime=mHour+":"+mMinute;
        mFormatTime=mHour+":"+mMinute;


        //set Values in TextViews
        mDateText.setText(mFormatDay);
        mTimeText.setText(mFormatTime);
        priority.setText("A1");
        //Date and Time picker


        //Task Category
        final ArrayList<SpinnerData> customList1 = new ArrayList<>();
        customList1.add(new SpinnerData(0, "Choose a category"));
        customList1.add(new SpinnerData(R.drawable.sam2, "Personal Errands"));
        customList1.add(new SpinnerData(R.drawable.sam3, "Work"));
        customList1.add(new SpinnerData(R.drawable.sam4, "Health"));
        customList1.add(new SpinnerData(R.drawable.sam5, "School"));
        customList1.add(new SpinnerData(R.drawable.sam6, "Others"));

        final CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(ReminderAddActivity1.this,
                R.layout.spinner_layout, R.id.textView, customList1);
        spinner1.setAdapter(adapter);



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
        //priority
        priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ReminderAddActivity1.this);
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



        //Repeat category
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ReminderAddActivity1.this);
                View mView = getLayoutInflater().inflate(R.layout.repeat_dialog, null);

                eCalendar = Calendar.getInstance();
                eMonth = eCalendar.get(Calendar.MONTH)+1;
                eYear = eCalendar.get(Calendar.YEAR);
                eDay = eCalendar.get(Calendar.DAY_OF_MONTH);

                dueDt = eMonth + "/" + eDay + "/" + eYear;

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

                final ArrayAdapter<String> catAdapter = new ArrayAdapter<>(ReminderAddActivity1.this, R.layout.support_simple_spinner_dropdown_item, catList);
                final ArrayAdapter<String> dueAdapter = new ArrayAdapter<>(ReminderAddActivity1.this, R.layout.support_simple_spinner_dropdown_item, dueList);
                RepeatType.setAdapter(catAdapter);
                RepeatDue.setAdapter(dueAdapter);

                //set values
                RepeatNo.setText(mRepeatNo);
                dueDate.setText(dueDt);
                every.setTextColor(Color.parseColor("#808080"));
                evry.setTextColor(Color.parseColor("#808080"));
                RepeatNo.setTextColor(Color.parseColor("#808080"));
                RepeatNo.setEnabled(false);
                RepeatType.setEnabled(false);
                RepeatDue.setEnabled(false);



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
                        DatePickerDialog datePickerDialog = new DatePickerDialog(ReminderAddActivity1.this, new DatePickerDialog.OnDateSetListener() {
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
                                    eMonth1=""+eMonth;
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
                            dueDate.setVisibility(View.INVISIBLE);
                            RepeatDue.setSelection(0);
                            mRepeat="false";
                            mDue="0000-00-00";
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
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.getWindow().setLayout(700, 550);

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mRepeatNo=RepeatNo.getText().toString();
                        if(Repeat.isChecked()){
                            repeatTitle("repeat every "+mRepeatNo+" "+mRepeatType+"(s)");
                        }else {
                            repeatTitle("One-time Task");
                        }
                        output(mRepeat,mRepeatType,mRepeatNo,dDate,mDueDate,mDue);

                        dialog.dismiss();



                    }
                });
            }
        });
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
                mTitleText.setText(mTitle);

                if (mTitleText.getText().toString().length() == 0)
                    mTitleText.setError("Reminder Title cannot be blank!");

                else {
                    saveReminder();
                }
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public  void prior(String mPriority){
        this.mPriority=mPriority;
        priority.setText(mPriority);

    }

    public void output (String repeat1,String mRepeatType, String mRepeatNo, String dueDt, String mDueDate,String mDue){
        this.mRepeat=repeat1;
        this.mRepeatType=mRepeatType;
        this.mRepeatNo=mRepeatNo;
        this.dueDt=dueDt;
        this.mDueDate=mDueDate;
        this.mDue=mDue;
    }
    public void repeatTitle(String txt){
        repeat.setText(txt);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ReminderAddActivity1.this, ContentTask.class);
        startActivityForResult(intent, 1);
    }

    private void saveReminder() {
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
        System.out.println("mPriority:"+mPriority);
        System.out.println("mDue"+mDue);

        Log.e("nskdnds", String.valueOf(mDay));
        Log.e("nskdnds", String.valueOf(mMonth));
        Log.e("nskdnds", String.valueOf(mYear));
        Log.e("nskdnds", String.valueOf(mHour));
        Log.e("nskdnds", String.valueOf(mMinute));

        EverydayDBHelper rb = new EverydayDBHelper(this);
        int id= (int) System.currentTimeMillis();
        // Creating Reminder
        int ID = rb.insertTask(new Reminder(id,mCategory,mTitle, mDate, mTime, mRepeat, mRepeatNo, mRepeatType, mActive,mDueDate,0,mPriority,mDue));

        if(mDueDate.equalsIgnoreCase("")&& mActive.equalsIgnoreCase("true")) {
            rb.insertCalen(new Calen(id, mDate, mTitle, "task"));
        }if(mDueDate.equalsIgnoreCase("Until a date")&& mActive.equalsIgnoreCase("true")){
            List<String> dates = getDates(mDate, mDue);
            for(String date:dates) {
                rb.insertCalen(new Calen(id,date,mTitle,"task"));
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
                new AlarmReceiver().setRepeatAlarm(getApplicationContext(), mCalendar, ID, mRepeatTime);
            } else if (mRepeat.equals("false")) {
                new AlarmReceiver().setAlarm(getApplicationContext(), mCalendar, ID);
            }
        }

        // Create toast to confirm new reminder
        Toast.makeText(getApplicationContext(), "Saved",
                Toast.LENGTH_SHORT).show();

        onBackPressed();

    }


    @Override
    public void onClick(View v) {
        if(v==mDateText){
            DatePickerDialog datePickerDialog=new DatePickerDialog(this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    monthOfYear++;
                    mDay=dayOfMonth;
                    mYear=year;
                    mMonth=monthOfYear;
                    if(mDay<10){
                        mDayl="0"+mDay;
                    }else{
                        mDayl=""+mDay;
                    }
                    if(mMonth<10){
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
                    mDate=mYear+"-"+mMonth1+"-"+mDayl;
                    mDateText.setText(days[dayOfWeek] + ", " + months[monThName] +" " + mDayl + ", " + mYear);
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
                        mTimeText.setText("0"+hourOfDay+":0"+minute);
                    }else
                        mTime=hourOfDay+":"+minute;
                    mTimeText.setText(hourOfDay+":"+minute);
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
}




