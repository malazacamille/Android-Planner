package com.example.milleca.mydailyplanner;

/**
 * Created by milleca on 1/2/2018.
 */

public class Event {

    private int eID;
    private String eTitle;
    private String eNote;
    private String eIcon;
    private String eAllDay;
    private String eSDate;
    private String eEDate;
    private String eSTime;
    private String eETime;
    private String eLocation;
    private String eNotification;
    private int eNotif;
    private String eActive;
    private String eAvail;
    public Event(int ID, String Title, String Note, String Icon, String AllDay, String SDate, String EDate,
                 String STime, String ETime, String Location, String Notification, int Notif,
                 String Active, String Avail)
    {
        eID=ID;
        eTitle=Title;
        eNote=Note;
        eIcon=Icon;
        eAllDay=AllDay;
        eSDate=SDate;
        eEDate=EDate;
        eSTime=STime;
        eETime=ETime;
        eLocation=Location;
        eNotification=Notification;
        eNotif=Notif;
        eAvail=Avail;
        eActive=Active;

    }
    public Event( String Title, String Note, String Icon, String AllDay, String SDate, String EDate,
                 String STime, String ETime, String Location, String Notification, int Notif,
                 String Active, String Avail)
    {

        eTitle=Title;
        eNote=Note;
        eIcon=Icon;
        eAllDay=AllDay;
        eSDate=SDate;
        eEDate=EDate;
        eSTime=STime;
        eETime=ETime;
        eLocation=Location;
        eNotification=Notification;
        eNotif=Notif;
        eAvail=Avail;
        eActive=Active;

    }
    public Event(){

    }
    public int getID() {return eID;}
    public void setID(int ID) {
        eID = ID;
    }

    public String getTitle() {
        return eTitle;
    }
    public void setTitle(String Title) {
        eTitle = Title;
    }

    public String getNote() {
        return eNote;
    }
    public void setNote(String Note) {
        eNote = Note;
    }

    public String getIcon() {
        return eIcon;
    }
    public void setIcon(String Icon) {
        eIcon = Icon;
    }

    public String getAllDay() {
        return eAllDay;
    }
    public void seteAllDay(String AllDay) {
        eAllDay = AllDay;
    }

    public String getSDate() {
        return eSDate;
    }
    public void setSDate(String SDate) {
        eSDate = SDate;
    }

    public String getEDate() {
        return eEDate;
    }
    public void setEDate(String EDate) {
        eEDate = EDate;
    }

    public String getSTime() {
        return eSTime;
    }
    public void setSTime(String STime) {
        eSTime = STime;
    }

    public String geteETime() {return eETime;}
    public void setETime(String ETime) {
        eETime = ETime;
    }

    public void setLocation(String Location) {
        eLocation =Location;
    }
    public String getLocation() {return eLocation;}

    public void setNotification(String Notification) {
        eNotification = Notification;
    }
    public String getNotification() {
        return eNotification;
    }

    public void setNotif(int Notif){eNotif=Notif;}
    public int getNotif(){return eNotif;}

    public void setAvail(String Avail) {
        eAvail = Avail;
    }
    public String getAvail() {
        return eAvail;
    }

    public String getActive() {
        return eActive;
    }
    public void setActive(String Active) {
        eActive = Active;
    }
}
