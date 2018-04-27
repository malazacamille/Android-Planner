package com.example.milleca.mydailyplanner;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
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

public class NoteEditActivity extends AppCompatActivity implements View.OnClickListener {
    TextView date_created;
    EditText content_note;
    Calendar nCalendar;
    int nYear,nMonth,nDay;
    String nMonth1,nDay1;
    String nDate,noteContent;
    Toolbar nToolbar;
    private String[] nDateSplit;

    // Constant Intent String
    public static final String EXTRA_NOTE_ID = "Note_ID";

    private Note nReceivedNote;
    private int nReceivedID;
    private EverydayDBHelper rb;
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
        getSupportActionBar().setTitle(Html.fromHtml("<small>Edit Note</small>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Get note id from intent
        nReceivedID = Integer.parseInt(getIntent().getStringExtra(EXTRA_NOTE_ID));

        // Get note using note id
        rb = new EverydayDBHelper(this);
        nReceivedNote = rb.getNote(nReceivedID);

        // Get values from note
        nDate=nReceivedNote.getDate();
        noteContent=nReceivedNote.getNoteContent();

        content_note.setText(noteContent);

        nCalendar=Calendar.getInstance();
        nDateSplit = nDate.split("-");
        nYear = Integer.parseInt(nDateSplit[0]);
        nMonth = Integer.parseInt(nDateSplit[1]);
        nDay = Integer.parseInt(nDateSplit[2]);

        String mn= String.valueOf(nMonth);
        if(mn.equalsIgnoreCase("01")){
            nMonth=1;
        }
        if(mn.equalsIgnoreCase("02")){
            nMonth=2;
        }
        if(mn.equalsIgnoreCase("03")){
            nMonth=3;
        }
        if(mn.equalsIgnoreCase("04")){
            nMonth=4;
        }
        if(mn.equalsIgnoreCase("05")){
            nMonth=5;
        }
        if(mn.equalsIgnoreCase("06")){
            nMonth=6;
        }
        if(mn.equalsIgnoreCase("07")){
            nMonth=7;
        }
        if(mn.equalsIgnoreCase("08")){
            nMonth=8;
        }
        if(mn.equalsIgnoreCase("09")) {
            nMonth = 9;
        }
        String dy= String.valueOf(nDay);
        if(dy.equalsIgnoreCase("01")){
            nDay=1;
        }
        if(dy.equalsIgnoreCase("02")){
            nDay=2;
        }
        if(dy.equalsIgnoreCase("03")){
            nDay=3;
        }
        if(dy.equalsIgnoreCase("04")){
            nDay=4;
        }
        if(dy.equalsIgnoreCase("05")){
            nDay=5;
        }
        if(dy.equalsIgnoreCase("06")){
            nDay=6;
        }
        if(dy.equalsIgnoreCase("07")){
            nDay=7;
        }
        if(dy.equalsIgnoreCase("08")){
            nDay=8;
        }
        if(dy.equalsIgnoreCase("09")){
            nDay=9;
        }

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
                    date_created.setText(months[monThName] +" " + nDay1 + ", " + nYear);
                    nDate=nYear+"-"+nMonth1+"-"+nDay1;
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
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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

            case R.id.delete:
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                builder.setMessage("delete note?")
                        .setPositiveButton("delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                rb.deleteNote(nReceivedID);
                                Toast.makeText(getApplicationContext(), "Deleted",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(NoteEditActivity.this,ActivityFour.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                // Create the AlertDialog object and return it
                final android.app.AlertDialog dialog = builder.create();
                dialog.show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        EverydayDBHelper rb = new EverydayDBHelper(this);
        rb.updateNote(nReceivedID,nDate,noteContent);
        rb.deleteCalen(nReceivedID);
        rb.insertCalen(new Calen(nReceivedID,nDate,noteContent,"note"));

        Toast.makeText(getApplicationContext(), "Edited",
                Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(NoteEditActivity.this,ActivityFour.class);
        startActivity(intent);
    }
}
