package com.example.milleca.mydailyplanner;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by milleca on 1/9/2018.
 */

public class EventReceiver extends WakefulBroadcastReceiver {
    AlarmManager mAlarmManager;
    PendingIntent mPendingIntent;
    @Override
    public void onReceive(Context context, Intent intent) {
        int mReceivedID = Integer.parseInt(intent.getStringExtra(EventEditActivity.EXTRA_EVENT_ID));

        // Get notification title from Reminder Database
        EverydayDBHelper rb = new EverydayDBHelper(context);
        Event event = rb.getEvent(mReceivedID);
        String mTitle = event.getTitle();

        String[] sDateSplit;
        String[] eDateSplit;
        int sYear,sMonth,sDay,eYear,eMonth,eDay;

        sDateSplit = event.getSDate().split("-");
        eDateSplit = event.getEDate().split("-");

        sYear = Integer.parseInt(sDateSplit[0]);
        sMonth = Integer.parseInt(sDateSplit[1]);
        sDay= Integer.parseInt(sDateSplit[2]);

        eYear = Integer.parseInt(eDateSplit[0]);
        eMonth = Integer.parseInt(eDateSplit[1]);
        eDay= Integer.parseInt(eDateSplit[2]);

        String mn= String.valueOf(sMonth);
        if(mn.equalsIgnoreCase("01")){
            sMonth=1;
        }
        if(mn.equalsIgnoreCase("02")){
            sMonth=2;
        }
        if(mn.equalsIgnoreCase("03")){
            sMonth=3;
        }
        if(mn.equalsIgnoreCase("04")){
            sMonth=4;
        }
        if(mn.equalsIgnoreCase("05")){
            sMonth=5;
        }
        if(mn.equalsIgnoreCase("06")){
            sMonth=6;
        }
        if(mn.equalsIgnoreCase("07")){
            sMonth=7;
        }
        if(mn.equalsIgnoreCase("08")){
            sMonth=8;
        }
        if(mn.equalsIgnoreCase("09")){
            sMonth=9;
        }

        String dy= String.valueOf(sDay);
        if(dy.equalsIgnoreCase("01")){
            sDay=1;
        }
        if(dy.equalsIgnoreCase("02")){
            sDay=2;
        }
        if(dy.equalsIgnoreCase("03")){
            sDay=3;
        }
        if(dy.equalsIgnoreCase("04")){
            sDay=4;
        }
        if(dy.equalsIgnoreCase("05")){
            sDay=5;
        }
        if(dy.equalsIgnoreCase("06")){
            sDay=6;
        }
        if(dy.equalsIgnoreCase("07")){
            sDay=7;
        }
        if(dy.equalsIgnoreCase("08")){
            sDay=8;
        }
        if(dy.equalsIgnoreCase("09")){
            sDay=9;
        }

        String smn= String.valueOf(eMonth);
        if(smn.equalsIgnoreCase("01")){
            eMonth=1;
        }
        if(smn.equalsIgnoreCase("02")){
            eMonth=2;
        }
        if(smn.equalsIgnoreCase("03")){
            eMonth=3;
        }
        if(smn.equalsIgnoreCase("04")){
            eMonth=4;
        }
        if(smn.equalsIgnoreCase("05")){
            eMonth=5;
        }
        if(smn.equalsIgnoreCase("06")){
            eMonth=6;
        }
        if(smn.equalsIgnoreCase("07")){
            eMonth=7;
        }
        if(smn.equalsIgnoreCase("08")){
            eMonth=8;
        }
        if(smn.equalsIgnoreCase("09")){
            eMonth=9;
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

        Calendar cal = new GregorianCalendar(sYear, sMonth-1, sDay);
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String month_name = month_date.format(cal.getTime());

        Calendar cal2 = new GregorianCalendar(eYear, eMonth-1, eDay);
        SimpleDateFormat month_date2 = new SimpleDateFormat("MMM");
        String month_name2 = month_date2.format(cal2.getTime());
        String content="";

        if(sMonth==eMonth){
            content=month_name+" "+sDay+"-"+eDay+" "+sYear;
        }
        if(sDay==eDay){
            content=month_name+" "+sDay+", "+sYear;
        }
        if(sMonth!=eMonth) {
            content=month_name + " " + sDay + "-" + month_name2 + " " + eDay + " " + sYear;
        }

        // Create intent to open ReminderEditActivity on notification click
        Intent editIntent = new Intent(context,EventEditActivity.class);
        editIntent.putExtra(EventEditActivity.EXTRA_EVENT_ID, Integer.toString(mReceivedID));
        PendingIntent mClick = PendingIntent.getActivity(context, mReceivedID, editIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create Notification
        @SuppressLint("ResourceAsColor") NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_logo))
                .setSmallIcon(R.mipmap.ic_stat_everydayfont)
                .setContentTitle(mTitle)
                .setTicker(mTitle)
                .setContentText(content)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(mClick)
                .setAutoCancel(true);


        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(mReceivedID, mBuilder.build());
    }

    public void setAlarm(Context context, Calendar calendar, int ID) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Put Reminder ID in Intent Extra
        Intent intent = new Intent(context, EventReceiver.class);
        intent.putExtra(EventEditActivity.EXTRA_EVENT_ID, Integer.toString(ID));
        mPendingIntent = PendingIntent.getBroadcast(context, ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Calculate notification time
        Calendar c = Calendar.getInstance();
        long currentTime = c.getTimeInMillis();
        long diffTime = calendar.getTimeInMillis() - currentTime;

        // Start alarm using notification time
        mAlarmManager.set(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + diffTime,
                mPendingIntent);

        // Restart alarm if device is rebooted
        ComponentName receiver = new ComponentName(context, EventBootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
    public void setEndAlarm(Context context, Calendar calendar, int ID, int sid) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Put Reminder ID in Intent Extra
        Intent intent = new Intent(context, EventReceiver.class);
        intent.putExtra(EventEditActivity.EXTRA_EVENT_ID, Integer.toString(sid));
        mPendingIntent = PendingIntent.getBroadcast(context, ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Calculate notification time
        Calendar c = Calendar.getInstance();
        long currentTime = c.getTimeInMillis();
        long diffTime = calendar.getTimeInMillis() - currentTime;

        // Start alarm using notification time
        mAlarmManager.set(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + diffTime,
                mPendingIntent);

        // Restart alarm if device is rebooted
        ComponentName receiver = new ComponentName(context, EventBootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void setRepeatAlarm(Context context, Calendar calendar, int ID, long RepeatTime) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Put Reminder ID in Intent Extra
        Intent intent = new Intent(context, EventReceiver.class);
        intent.putExtra(EventEditActivity.EXTRA_EVENT_ID, Integer.toString(ID));
        mPendingIntent = PendingIntent.getBroadcast(context, ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Calculate notification timein
        Calendar c = Calendar.getInstance();
        long currentTime = c.getTimeInMillis();
        long diffTime = calendar.getTimeInMillis() - currentTime;

        // Start alarm using initial notification time and repeat interval time
        mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + diffTime,
                RepeatTime , mPendingIntent);

        // Restart alarm if device is rebooted
        ComponentName receiver = new ComponentName(context, EventBootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void cancelAlarm(Context context, int ID) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Cancel Alarm using Reminder ID
        mPendingIntent = PendingIntent.getBroadcast(context, ID, new Intent(context, EventReceiver.class), 0);
        mAlarmManager.cancel(mPendingIntent);

        // Disable alarm
        ComponentName receiver = new ComponentName(context, EventBootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}


