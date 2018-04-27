package com.example.milleca.mydailyplanner;

/**
 * Created by milleca on 1/11/2018.
 */

public class Theme {
    int id;
    String title;
    String toolbar;
    // Empty constructor
    public Theme() {

    }

    // constructor
    public Theme(int keyId, String title,String toolbar) {
        this.id = keyId;
        this.title=title;
        this.toolbar = toolbar;

    }
    public Theme(String title,String toolbar) {
        this.title=title;
        this.toolbar = toolbar;

    }
    public Theme(int keyId) {
        this.id = keyId;

    }


    // getting ID
    public int getID() {
        return this.id;
    }

    // setting id
    public void setID(int keyId) {
        this.id = keyId;
    }

    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getToolbar() {
        return this.toolbar;
    }


    public void setToolbar(String toolbar) {
        this.toolbar = toolbar;
    }


}


