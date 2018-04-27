package com.example.milleca.mydailyplanner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by milleca on 2/5/2018.
 */

public class NoteAddActivity extends AppCompatActivity implements View.OnClickListener {
    TextView date_created;
    EditText content_note;
    Calendar nCalendar;
    int nYear,nMonth,nDay;
    String nMonth1,nDay1;
    String nDate,noteContent;
    Toolbar nToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_add);

        date_created=findViewById(R.id.date);
        content_note=findViewById(R.id.content);
        nToolbar=findViewById(R.id.ntoolbar);
        date_created.setOnClickListener(this);

        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.my_statusbar_color));

        // Setup Toolbar
        setSupportActionBar(nToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<small>Add New Note</small>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        nCalendar=Calendar.getInstance();
        nYear=nCalendar.get(Calendar.YEAR);
        nMonth=nCalendar.get(Calendar.MONTH)+1;
        nDay=nCalendar.get(Calendar.DAY_OF_MONTH);

        if(nDay<10){
            nDay1="0"+nDay;
        }else{
           nDay1=""+nDay;
        }
        if(nMonth<10){
            nMonth1="0"+nMonth;
        }
        else {
            nMonth1=""+nMonth;
        }
        //date format
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String month_name = month_date.format(nCalendar.getTime());
        String[] days = new String[]{"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        String day = days[nCalendar.get(Calendar.DAY_OF_WEEK)];
        date_created.setText(month_name + " " + nDay1 + ", " + nYear);
        nDate=nYear+"-"+nMonth1+"-"+nDay1;

        content_note.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                noteContent = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    @Override
    public void onClick(View v) {
        if(v==date_created){
            DatePickerDialog datePickerDialog=new DatePickerDialog(this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    monthOfYear++;
                    nDay=dayOfMonth;
                    nYear=year;
                    nMonth=monthOfYear;
                    if(nDay<10){
                        nDay1="0"+nDay;
                    }else{
                        nDay1=""+nDay;
                    }
                    if(nMonth<10){
                        nMonth1="0"+nMonth;
                    }
                    else {
                        nMonth1=""+nMonth;
                    }
                    Calendar cal = new GregorianCalendar(nYear, nMonth, nDay);
                    int monThName=cal.get(Calendar.MONTH);
                    String[] months=new String[]{"","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                    nDate=nYear+"-"+nMonth1+"-"+nDay1;

                    date_created.setText(months[monThName] +" " + nDay1 + ", " + nYear);
                }
            }
                    ,nYear,
                    nMonth-1,
                    nDay);
            datePickerDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    // On clicking menu buttons
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // On clicking the back arrow
            // Discard any changes
            case android.R.id.home:
                onBackPressed();
                Toast.makeText(getApplicationContext(), "Discarded",
                        Toast.LENGTH_SHORT).show();
                return true;

            // On clicking save reminder button
            // Update reminder
            case R.id.save_reminder:
                    saveNote();

                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        EverydayDBHelper rb = new EverydayDBHelper(this);
        int id= (int) System.currentTimeMillis();
        // Creating Reminder
        rb.insertNote(new Note(id,nDate,noteContent));
        rb.insertCalen(new Calen(id,nDate,noteContent,"note"));

        Toast.makeText(getApplicationContext(), "Saved",
                Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(NoteAddActivity.this,ActivityFour.class);
        startActivity(intent);
    }
}
