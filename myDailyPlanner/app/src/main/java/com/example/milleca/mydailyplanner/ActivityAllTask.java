package com.example.milleca.mydailyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ActivityAllTask extends AppCompatActivity{
    ViewPager viewpager;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_category);
        viewpager = (ViewPager) findViewById(R.id.pager);
        PageAdapterTask padapter = new PageAdapterTask(getSupportFragmentManager());
        viewpager.setAdapter(padapter);




    }
}
