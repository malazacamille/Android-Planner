package com.example.milleca.mydailyplanner;

/**
 * Created by milleca on 1/18/2018.
 */

public class SpinnerData {
    int icon;
    String iconName;

    public SpinnerData(int icon, String iconName){
        this.icon=icon;
        this.iconName=iconName;
    }
    public int getIcon(){
        return icon;
    }
    public String getIconName(){
        return iconName;
    }
}
