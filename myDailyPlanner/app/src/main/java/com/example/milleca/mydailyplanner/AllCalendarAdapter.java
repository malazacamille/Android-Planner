package com.example.milleca.mydailyplanner;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by milleca on 1/14/2018.
 */

public class AllCalendarAdapter extends FragmentPagerAdapter {

    public AllCalendarAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        switch (arg0) {
            case 0:
                return new FragmentCalenTask();
            case 1:
                return new FragmentCalenEvent();
            case 2:
                return new FragmentCalenNote();

            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3;
    }
}