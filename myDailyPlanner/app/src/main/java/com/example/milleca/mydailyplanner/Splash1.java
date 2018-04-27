package com.example.milleca.mydailyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

/**
 * Created by milleca on 2/13/2018.
 */

public class Splash1 extends AppCompatActivity {
    private Handler mHandler=new Handler();
    LinearLayout l1,l2;
    Animation uptodown,downtoup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        l1=findViewById(R.id.everyday);
        l2=findViewById(R.id.daily);

        uptodown=AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup=AnimationUtils.loadAnimation(this,R.anim.downtoup);

        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash1.this,Splash2.class);
                startActivity(intent);
            }
        },1000);
    }
}
