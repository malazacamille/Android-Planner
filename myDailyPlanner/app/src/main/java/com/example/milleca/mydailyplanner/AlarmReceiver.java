package com.example.milleca.mydailyplanner;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by milleca on 12/23/2017.
 */

public class AlarmReceiver extends WakefulBroadcastReceiver {
    AlarmManager mAlarmManager;
    PendingIntent mPendingIntent;
    @Override
    public void onReceive(Context context, Intent intent) {
        int mReceivedID = Integer.parseInt(intent.getStringExtra(ReminderEditActivity.EXTRA_REMINDER_ID));

        // Get notification title from Reminder Database
        EverydayDBHelper rb = new EverydayDBHelper(context);
        Reminder reminder = rb.getTask(mReceivedID);
        String mTitle = reminder.getTitle();

        String[] sDateSplit;
        int sYear,sMonth,sDay;

        sDateSplit = reminder.getDate().split("-");

        sYear = Integer.parseInt(sDateSplit[0]);
        sMonth = Integer.parseInt(sDateSplit[1]);
        sDay= Integer.parseInt(sDateSplit[2]);


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

        Calendar cal = new GregorianCalendar(sYear, sMonth-1, sDay);
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String month_name = month_date.format(cal.getTime());


        // Create intent to open ReminderEditActivity on notification click
        Intent editIntent = new Intent(context, ReminderEditActivity.class);
        editIntent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(mReceivedID));
        PendingIntent mClick = PendingIntent.getActivity(context, mReceivedID, editIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create Notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_logo))
                .setSmallIcon(R.mipmap.ic_stat_everydayfont)
                .setContentTitle(mTitle)
                .setTicker(mTitle)
                .setContentText(month_name+" "+sDay+", "+sYear)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(mClick)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true);

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(mReceivedID, mBuilder.build());
    }

    public void setAlarm(Context context, Calendar calendar, int ID) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Put Reminder ID in Intent Extra
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(ID));
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
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void setRepeatAlarm(Context context, Calendar calendar, int ID, long RepeatTime) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Put Reminder ID in Intent Extra
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(ID));
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
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void cancelAlarm(Context context, int ID) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Cancel Alarm using Reminder ID
        mPendingIntent = PendingIntent.getBroadcast(context, ID, new Intent(context, AlarmReceiver.class), 0);
        mAlarmManager.cancel(mPendingIntent);

        // Disable alarm
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

}
