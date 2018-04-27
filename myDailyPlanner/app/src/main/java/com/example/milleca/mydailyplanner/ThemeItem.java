package com.example.milleca.mydailyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by milleca on 2/13/2018.
 */

public class ThemeItem extends AppCompatActivity {
    public String theme,toolbar;
    EverydayDBHelper db;
    private Theme mReceivedTheme;
    String themeTitle,themeToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_item);

        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.toolbar_title));

        final ImageView sm1=findViewById(R.id.sm1);
        final SmoothCheckBox chck1=findViewById(R.id.chck1);
        final ImageView sm2=findViewById(R.id.sm2);
        final SmoothCheckBox chck2=findViewById(R.id.chck2);
        final ImageView sm3=findViewById(R.id.sm3);
        final SmoothCheckBox chck3=findViewById(R.id.chck3);
        final ImageView sm4=findViewById(R.id.sm4);
        final SmoothCheckBox chck4=findViewById(R.id.chck4);
        final ImageView sm5=findViewById(R.id.sm5);
        final SmoothCheckBox chck5=findViewById(R.id.chck5);
        final ImageView sm6=findViewById(R.id.sm6);
        final SmoothCheckBox chck6=findViewById(R.id.chck6);
        final ImageView sm7=findViewById(R.id.sm7);
        final SmoothCheckBox chck7=findViewById(R.id.chck7);
        final ImageView sm8=findViewById(R.id.sm8);
        final SmoothCheckBox chck8=findViewById(R.id.chck8);
        final ImageView sm9=findViewById(R.id.sm9);
        final SmoothCheckBox chck9=findViewById(R.id.chck9);
        final ImageView sm10=findViewById(R.id.sm10);
        final SmoothCheckBox chck10=findViewById(R.id.chck10);
        final ImageView sm11=findViewById(R.id.sm11);
        final SmoothCheckBox chck11=findViewById(R.id.chck11);
        final ImageView sm12=findViewById(R.id.sm12);
        final SmoothCheckBox chck12=findViewById(R.id.chck12);

        db=new EverydayDBHelper(this);
        mReceivedTheme = db.getTheme(1);
        themeTitle=mReceivedTheme.getTitle();
        themeToolbar=mReceivedTheme.getToolbar();

        switch (themeTitle){
            case "theme1":
                chck1.setVisibility(View.VISIBLE);
                break;
            case "theme2":
                chck2.setVisibility(View.VISIBLE);
                break;
            case "theme3":
                chck3.setVisibility(View.VISIBLE);
                break;
            case "theme4":
                chck4.setVisibility(View.VISIBLE);
                break;
            case "theme5":
                chck5.setVisibility(View.VISIBLE);
                break;
            case "theme6":
                chck6.setVisibility(View.VISIBLE);
                break;
            case "theme7":
                chck7.setVisibility(View.VISIBLE);
                break;
            case "theme8":
                chck8.setVisibility(View.VISIBLE);
                break;
            case "theme9":
                chck9.setVisibility(View.VISIBLE);
                break;
            case "theme10":
                chck10.setVisibility(View.VISIBLE);
                break;
            case "theme11":
                chck11.setVisibility(View.VISIBLE);
                break;
            case "theme12":
                chck12.setVisibility(View.VISIBLE);
                break;
        }

        chck1.setChecked(true);
        chck2.setChecked(true);
        chck3.setChecked(true);
        chck4.setChecked(true);
        chck5.setChecked(true);
        chck6.setChecked(true);
        chck7.setChecked(true);
        chck8.setChecked(true);
        chck9.setChecked(true);
        chck10.setChecked(true);
        chck11.setChecked(true);
        chck12.setChecked(true);

        chck1.setEnabled(false);
        chck2.setEnabled(false);
        chck3.setEnabled(false);
        chck4.setEnabled(false);
        chck5.setEnabled(false);
        chck6.setEnabled(false);
        chck7.setEnabled(false);
        chck8.setEnabled(false);
        chck9.setEnabled(false);
        chck10.setEnabled(false);
        chck11.setEnabled(false);
        chck12.setEnabled(false);


        db=new EverydayDBHelper(ThemeItem.this);
        sm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme1";
                chck1.setVisibility(View.VISIBLE);
                chck2.setVisibility(View.INVISIBLE);
                chck3.setVisibility(View.INVISIBLE);
                chck4.setVisibility(View.INVISIBLE);
                chck5.setVisibility(View.INVISIBLE);
                chck6.setVisibility(View.INVISIBLE);
                chck7.setVisibility(View.INVISIBLE);
                chck8.setVisibility(View.INVISIBLE);
                chck9.setVisibility(View.INVISIBLE);
                chck10.setVisibility(View.INVISIBLE);
                chck11.setVisibility(View.INVISIBLE);
                chck12.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
        sm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme2";
                chck1.setVisibility(View.INVISIBLE);
                chck2.setVisibility(View.VISIBLE);
                chck3.setVisibility(View.INVISIBLE);
                chck4.setVisibility(View.INVISIBLE);
                chck5.setVisibility(View.INVISIBLE);
                chck6.setVisibility(View.INVISIBLE);
                chck7.setVisibility(View.INVISIBLE);
                chck8.setVisibility(View.INVISIBLE);
                chck9.setVisibility(View.INVISIBLE);
                chck10.setVisibility(View.INVISIBLE);
                chck11.setVisibility(View.INVISIBLE);
                chck12.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
        sm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme3";
                chck1.setVisibility(View.INVISIBLE);
                chck2.setVisibility(View.INVISIBLE);
                chck3.setVisibility(View.VISIBLE);
                chck4.setVisibility(View.INVISIBLE);
                chck5.setVisibility(View.INVISIBLE);
                chck6.setVisibility(View.INVISIBLE);
                chck7.setVisibility(View.INVISIBLE);
                chck8.setVisibility(View.INVISIBLE);
                chck9.setVisibility(View.INVISIBLE);
                chck10.setVisibility(View.INVISIBLE);
                chck11.setVisibility(View.INVISIBLE);
                chck12.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
        sm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme4";
                chck1.setVisibility(View.INVISIBLE);
                chck2.setVisibility(View.INVISIBLE);
                chck3.setVisibility(View.INVISIBLE);
                chck4.setVisibility(View.VISIBLE);
                chck5.setVisibility(View.INVISIBLE);
                chck6.setVisibility(View.INVISIBLE);
                chck7.setVisibility(View.INVISIBLE);
                chck8.setVisibility(View.INVISIBLE);
                chck9.setVisibility(View.INVISIBLE);
                chck10.setVisibility(View.INVISIBLE);
                chck11.setVisibility(View.INVISIBLE);
                chck12.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
        sm5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme5";
                chck1.setVisibility(View.INVISIBLE);
                chck2.setVisibility(View.INVISIBLE);
                chck3.setVisibility(View.INVISIBLE);
                chck4.setVisibility(View.INVISIBLE);
                chck5.setVisibility(View.VISIBLE);
                chck6.setVisibility(View.INVISIBLE);
                chck7.setVisibility(View.INVISIBLE);
                chck8.setVisibility(View.INVISIBLE);
                chck9.setVisibility(View.INVISIBLE);
                chck10.setVisibility(View.INVISIBLE);
                chck11.setVisibility(View.INVISIBLE);
                chck12.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
        sm6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme6";
                chck1.setVisibility(View.INVISIBLE);
                chck2.setVisibility(View.INVISIBLE);
                chck3.setVisibility(View.INVISIBLE);
                chck4.setVisibility(View.INVISIBLE);
                chck5.setVisibility(View.INVISIBLE);
                chck6.setVisibility(View.VISIBLE);
                chck7.setVisibility(View.INVISIBLE);
                chck8.setVisibility(View.INVISIBLE);
                chck9.setVisibility(View.INVISIBLE);
                chck10.setVisibility(View.INVISIBLE);
                chck11.setVisibility(View.INVISIBLE);
                chck12.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
        sm7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme7";
                chck1.setVisibility(View.INVISIBLE);
                chck2.setVisibility(View.INVISIBLE);
                chck3.setVisibility(View.INVISIBLE);
                chck4.setVisibility(View.INVISIBLE);
                chck5.setVisibility(View.INVISIBLE);
                chck6.setVisibility(View.INVISIBLE);
                chck7.setVisibility(View.VISIBLE);
                chck8.setVisibility(View.INVISIBLE);
                chck9.setVisibility(View.INVISIBLE);
                chck10.setVisibility(View.INVISIBLE);
                chck11.setVisibility(View.INVISIBLE);
                chck12.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
        sm8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme8";
                chck1.setVisibility(View.INVISIBLE);
                chck2.setVisibility(View.INVISIBLE);
                chck3.setVisibility(View.INVISIBLE);
                chck4.setVisibility(View.INVISIBLE);
                chck5.setVisibility(View.INVISIBLE);
                chck6.setVisibility(View.INVISIBLE);
                chck7.setVisibility(View.INVISIBLE);
                chck8.setVisibility(View.VISIBLE);
                chck9.setVisibility(View.INVISIBLE);
                chck10.setVisibility(View.INVISIBLE);
                chck11.setVisibility(View.INVISIBLE);
                chck12.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
        sm9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme9";
                chck1.setVisibility(View.INVISIBLE);
                chck2.setVisibility(View.INVISIBLE);
                chck3.setVisibility(View.INVISIBLE);
                chck4.setVisibility(View.INVISIBLE);
                chck5.setVisibility(View.INVISIBLE);
                chck6.setVisibility(View.INVISIBLE);
                chck7.setVisibility(View.INVISIBLE);
                chck8.setVisibility(View.INVISIBLE);
                chck9.setVisibility(View.VISIBLE);
                chck10.setVisibility(View.INVISIBLE);
                chck11.setVisibility(View.INVISIBLE);
                chck12.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
        sm10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme10";
                chck1.setVisibility(View.INVISIBLE);
                chck2.setVisibility(View.INVISIBLE);
                chck3.setVisibility(View.INVISIBLE);
                chck4.setVisibility(View.INVISIBLE);
                chck5.setVisibility(View.INVISIBLE);
                chck6.setVisibility(View.INVISIBLE);
                chck7.setVisibility(View.INVISIBLE);
                chck8.setVisibility(View.INVISIBLE);
                chck9.setVisibility(View.INVISIBLE);
                chck10.setVisibility(View.VISIBLE);
                chck11.setVisibility(View.INVISIBLE);
                chck12.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
        sm11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme11";
                chck1.setVisibility(View.INVISIBLE);
                chck2.setVisibility(View.INVISIBLE);
                chck3.setVisibility(View.INVISIBLE);
                chck4.setVisibility(View.INVISIBLE);
                chck5.setVisibility(View.INVISIBLE);
                chck6.setVisibility(View.INVISIBLE);
                chck7.setVisibility(View.INVISIBLE);
                chck8.setVisibility(View.INVISIBLE);
                chck9.setVisibility(View.INVISIBLE);
                chck10.setVisibility(View.INVISIBLE);
                chck11.setVisibility(View.VISIBLE);
                chck12.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
        sm12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeTitle="theme12";
                chck1.setVisibility(View.INVISIBLE);
                chck2.setVisibility(View.INVISIBLE);
                chck3.setVisibility(View.INVISIBLE);
                chck4.setVisibility(View.INVISIBLE);
                chck5.setVisibility(View.INVISIBLE);
                chck6.setVisibility(View.INVISIBLE);
                chck7.setVisibility(View.INVISIBLE);
                chck8.setVisibility(View.INVISIBLE);
                chck9.setVisibility(View.INVISIBLE);
                chck10.setVisibility(View.INVISIBLE);
                chck11.setVisibility(View.INVISIBLE);
                chck12.setVisibility(View.VISIBLE);
                onBackPressed();
            }
        });
       System.out.println(themeTitle);
    }

    @Override
    public void onBackPressed() {
        db.updateTheme(1,themeTitle,themeTitle);
        Intent intent= new Intent(ThemeItem.this,ActivityThree.class);
        startActivityForResult(intent,1);
    }
}
