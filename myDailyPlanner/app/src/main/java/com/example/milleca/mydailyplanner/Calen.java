package com.example.milleca.mydailyplanner;

/**
 * Created by milleca on 2/8/2018.
 */

public class Calen {
    int ID;
    String cDate;
    String cTitle;
    String cType;

    public Calen(){
    }

    public Calen(int id,String date,String title,String type){
        ID=id;
        cDate=date;
        cTitle=title;
        cType=type;
    }
    public Calen(String date,String title,String type){
        cDate=date;
        cTitle=title;
        cType=type;
    }
    // getting ID
    public int getID() {
        return this.ID;
    }
    public void setID(int id) {
        this.ID = id;
    }

    public String getDate() {
        return this.cDate;
    }
    public void setDate(String date) {
        this.cDate = date;
    }

    public String getTitle() {return this.cTitle;}
    public void setTitle(String title) {
        this.cTitle = title;
    }

    public String getType() {
        return this.cType;
    }
    public void setType(String type) {
        this.cType = type;
    }
}
