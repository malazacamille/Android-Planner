package com.example.milleca.mydailyplanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by milleca on 12/23/2017.
 */

public class BootReceiver extends BroadcastReceiver {
    private String mDueDate,mDue;
    private String mTime;
    private String mDate;
    private String mRepeatNo;
    private String mRepeatType;
    private String mActive;
    private String mRepeat;
    private String[] mDateSplit;
    private String[] mDueSplit;
    private String[] mTimeSplit;
    private int mYear, mMonth, mHour, mMinute, mDay, mReceivedID;
    private long  mRepeatTime;

    private Calendar mCalendar;
    private AlarmReceiver mAlarmReceiver;

    // Constant values in milliseconds
    private static final long milMinute = 60000L;
    private static final long milHour = 3600000L;
    private static final long milDay = 86400000L;
    private static final long milWeek = 604800000L;
    private static final long milMonth = 2592000000L;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            EverydayDBHelper rb = new EverydayDBHelper(context);
            mCalendar = Calendar.getInstance();
            mAlarmReceiver = new AlarmReceiver();

            List<Reminder> reminders = rb.getAllTask("");

            for (Reminder rm : reminders) {
                mReceivedID = rm.getID();
                mRepeat = rm.getRepeat();
                mRepeatNo = rm.getRepeatNo();
                mRepeatType = rm.getRepeatType();
                mActive = rm.getActive();
                mDate = rm.getDate();
                mTime = rm.getTime();

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

                mCalendar.set(Calendar.MONTH, --mMonth);
                mCalendar.set(Calendar.YEAR, mYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
                mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
                mCalendar.set(Calendar.MINUTE, mMinute);
                mCalendar.set(Calendar.SECOND, 0);

                // Cancel existing notification of the reminder by using its ID
                // mAlarmReceiver.cancelAlarm(context, mReceivedID);

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
                        mAlarmReceiver.setRepeatAlarm(context, mCalendar, mReceivedID, mRepeatTime);
                    } else if (mRepeat.equals("false")) {
                        mAlarmReceiver.setAlarm(context, mCalendar, mReceivedID);
                    }
                }
            }
        }
    }
}
