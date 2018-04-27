package com.example.milleca.mydailyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by milleca on 1/24/2018.
 */

public class ContentTask extends AppCompatActivity  {
    AllTaskAdapter mAdapter;
    ViewPager mPager;
    ImageView back,add;
    Button bt1,bt2,bt3;
    TextView title;

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ContentTask.this,ActivityFive.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        bt1=(Button) findViewById(R.id.btn1);
        bt2=(Button) findViewById(R.id.btn2);
        bt3=(Button) findViewById(R.id.btn3);

        mAdapter = new AllTaskAdapter(getSupportFragmentManager());
        title=findViewById(R.id.date);
        back=findViewById(R.id.back);
        add=findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ContentTask.this,ReminderAddActivity1.class);
                startActivityForResult(intent1,1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ContentTask.this,ActivityFive.class);
                startActivity(intent1);
            }
        });
        mPager = (ViewPager)findViewById(R.id.viewPager);
        mPager.setAdapter(mAdapter);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                btnAction(position);

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
    private void btnAction(int action){
        switch(action){
            case 0:
                setButton(bt2,4,15,R.drawable.circle2);
                setButton(bt1,4,15,R.drawable.circle);
                setButton(bt3,4,15,R.drawable.circle);

                break;

            case 1:  setButton(bt1,4,15,R.drawable.circle2);
                setButton(bt2,4,15,R.drawable.circle);
                setButton(bt3,4,15,R.drawable.circle);

                break;

            case 2:  setButton(bt3,4,15,R.drawable.circle2);
                setButton(bt2,4,15,R.drawable.circle);
                setButton(bt1,4,15,R.drawable.circle);


                break;
        }
    }
    private void setButton(Button btn,int w,int h,int c){

        btn.setWidth(w);
        btn.setHeight(h);
        btn.setBackgroundResource(c);
    }


}
