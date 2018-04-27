package com.example.milleca.mydailyplanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by milleca on 1/3/2018.
 */

public class EventBootReceiver extends BroadcastReceiver{
    private String eStartDate;
    private String eStartTime;
    private String eEndDate;
    private String eEndTime;
    private String eAllday;
    private int eNotif;
    private String eActive;
    private String[] sDateSplit;
    private String[] sTimeSplit;
    private String[] eDateSplit;
    private String[] eTimeSplit;
    int eYear,eDay,eMonth,eHour,eMinute;
    private int sYear, sMonth, sHour, sMinute, sDay, eReceivedID;

    private Calendar sCalendar,eCalendar;
    private EventReceiver eAlarmReceiver;

    public EventBootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            EverydayDBHelper rb = new EverydayDBHelper(context);
            sCalendar = Calendar.getInstance();
            eCalendar = Calendar.getInstance();
            eAlarmReceiver = new EventReceiver();

            List<Event> events = rb.getAllEvent("");

            for (Event rm : events) {
                eReceivedID = rm.getID();
                eActive = rm.getActive();
                eStartDate = rm.getSDate();
                eStartTime = rm.getSTime();
                eEndDate=rm.getEDate();
                eEndTime=rm.geteETime();
                eAllday=rm.getAllDay();
                eNotif=rm.getNotif();

                sDateSplit = eStartDate.split("-");
                sTimeSplit = eStartTime.split(":");
                eDateSplit = eEndDate.split("-");
                eTimeSplit = eEndTime.split(":");

                sYear = Integer.parseInt(sDateSplit[0]);
                sMonth = Integer.parseInt(sDateSplit[1]);
                sDay= Integer.parseInt(sDateSplit[2]);
                sHour = Integer.parseInt(sTimeSplit[0]);
                sMinute = Integer.parseInt(sTimeSplit[1]);

                eYear = Integer.parseInt(eDateSplit[0]);
                eMonth = Integer.parseInt(eDateSplit[1]);
                eDay= Integer.parseInt(eDateSplit[2]);
                eHour = Integer.parseInt(eTimeSplit[0]);
                eMinute = Integer.parseInt(eTimeSplit[1]);

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

                // Set up calender for creating the notification
                sCalendar.set(Calendar.MONTH, --sMonth);
                sCalendar.set(Calendar.YEAR, sYear);
                sCalendar.set(Calendar.DAY_OF_MONTH, sDay);
                if(eAllday.equalsIgnoreCase("true")) {
                    sCalendar.set(Calendar.HOUR_OF_DAY, 8);
                    sCalendar.set(Calendar.MINUTE, 0);
                    sCalendar.set(Calendar.AM_PM,0);
                }
                if(eAllday.equalsIgnoreCase("false")) {
                    sCalendar.set(Calendar.HOUR_OF_DAY, sHour);
                    sCalendar.set(Calendar.MINUTE, sMinute);
                }
                sCalendar.set(Calendar.SECOND,0);


                if(eNotif==15) {
                    sCalendar.add(Calendar.MINUTE, -15);
                }
                if(eNotif==30){
                    sCalendar.add(Calendar.MINUTE, -30);
                }
                if(eNotif==60){
                    sCalendar.add(Calendar.HOUR, -1);
                }
                if(eNotif==1){
                    sCalendar.add(Calendar.DAY_OF_MONTH, -1);
                }

                eCalendar.set(Calendar.MONTH,--eMonth);
                eCalendar.set(Calendar.YEAR,eYear);
                eCalendar.set(Calendar.DAY_OF_MONTH,eDay);
                if(eAllday.equalsIgnoreCase("true")) {
                    eCalendar.set(Calendar.HOUR_OF_DAY, 5);
                    eCalendar.set(Calendar.MINUTE, 0);
                    eCalendar.set(Calendar.AM_PM,1);
                }
                if(eAllday.equalsIgnoreCase("false")) {
                    eCalendar.set(Calendar.HOUR_OF_DAY, eHour);
                    eCalendar.set(Calendar.MINUTE, eMinute);
                }
                eCalendar.set(Calendar.SECOND,0);

                if (eActive.equals("true")) {
                  eAlarmReceiver.setAlarm(context, sCalendar, eReceivedID);
                  eAlarmReceiver.setEndAlarm(context, eCalendar, eReceivedID-50000,eReceivedID);
                }
                }
            }
        }
    }

