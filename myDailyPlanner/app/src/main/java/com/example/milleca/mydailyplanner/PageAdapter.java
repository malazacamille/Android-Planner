package com.example.milleca.mydailyplanner;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by milleca on 1/14/2018.
 */

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        switch (arg0) {
            case 0:

                return new FragmentOne();
            case 1:
                return new FragmentTwo();
            case 2:
                return new FragmentFive();
            case 3:
                return new FragmentFour();
            case 4:
                return new FragmentThree();

            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 5;
    }
}