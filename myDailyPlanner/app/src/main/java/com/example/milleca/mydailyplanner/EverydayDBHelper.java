package com.example.milleca.mydailyplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by milleca on 1/6/2018.
 */

public class EverydayDBHelper extends SQLiteOpenHelper {

    public EverydayDBHelper(Context context) {
        super(context, "EverydayDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE TASK_TABLE( ID INTEGER PRIMARY KEY, CATEGORY TEXT, TITLE TEXT, DATE TEXT, TIME INTEGER, REPEAT BOOLEAN, REPEAT_NO INTEGER, REPEAT_TYPE TEXT, ACTIVE BOOLEAN, DUEDATE TEXT, COMPLETEDTASK INTEGER, PRIORITY TEXT, DUE TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE EVENT_TABLE( ID INTEGER PRIMARY KEY, TITLE TEXT, NOTE TEXT, ICON TEXT, ALLDAY BOOLEAN, SDATE TEXT, EDATE TEXT, STIME INTEGER, ETIME INTEGER, LOCATION TEXT, NOTIFICATION TEXT, NOTIF INTEGER, ACTIVE BOOLEAN, AVAIL TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE NOTE_TABLE( ID INTEGER PRIMARY KEY, DATE_CREATED TEXT, NOTE TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE CALEN_TABLE( ID INTEGER, DATE TEXT, TITLE TEXT, TYPE TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE THEME_TABLE(ID INTEGER PRIMARY KEY, TITLE TEXT, TOOLBAR TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TASK_TABLE");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS EVENT_TABLE");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NOTE_TABLE");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CALEN_TABLE");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THEME_TABLE");
        onCreate(sqLiteDatabase);
    }

    public void insertTheme(int id,String title,String toolbar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("TITLE",title);
        contentValues.put("TOOLBAR", toolbar);
        db.insert("THEME_TABLE", null,contentValues);
    }

    public void updateTheme(int ID, String title, String toolbar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE",title);
        contentValues.put("TOOLBAR", toolbar);

        // Updating row
        db.update("THEME_TABLE", contentValues, "ID" + "=?",
                new String[]{String.valueOf(ID)});
    }


    // Getting single Reminder
    public Theme getTheme(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("THEME_TABLE", new String[]
                        {
                                "ID",
                                "TITLE",
                                "TOOLBAR",
                        }, "ID=?",

                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Theme theme = new Theme(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2));

        return theme;
    }

    public int getThemeCount() {
        String countQuery = "SELECT * FROM THEME_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }




    public int insertTask(Reminder reminder) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",reminder.getID());
        contentValues.put("CATEGORY",reminder.getCategory());
        contentValues.put("TITLE", reminder.getTitle());
        contentValues.put("DATE", reminder.getDate());
        contentValues.put("TIME", reminder.getTime());
        contentValues.put("REPEAT", reminder.getRepeat());
        contentValues.put("REPEAT_NO", reminder.getRepeatNo());
        contentValues.put("REPEAT_TYPE", reminder.getRepeatType());
        contentValues.put("ACTIVE", reminder.getActive());
        contentValues.put("DUEDATE", reminder.getDueDate());
        contentValues.put("COMPLETEDTASK",reminder.getCompletedTask());
        contentValues.put("PRIORITY",reminder.getPriotity());
        contentValues.put("DUE",reminder.getDue());

        int ID = (int) this.getReadableDatabase().insertOrThrow("TASK_TABLE", "", contentValues);
        return ID;

    }

    // Getting single Reminder
    public Reminder getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("TASK_TABLE", new String[]
                        {
                                "ID",
                                "CATEGORY",
                                "TITLE",
                                "DATE",
                                "TIME",
                                "REPEAT",
                                "REPEAT_NO",
                                "REPEAT_TYPE",
                                "ACTIVE",
                                "DUEDATE",
                                "COMPLETEDTASK",
                                "PRIORITY",
                                "DUE"
                        }, "ID=?",

                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Reminder reminder = new Reminder(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getString(7),cursor.getString(8),
                cursor.getString(9),Integer.parseInt(cursor.getString(10)),cursor.getString(11),cursor.getString(12));

        return reminder;
    }

    // Getting all Reminders based on priority
    public List<Reminder> getAllTask(String filter) {
        String query;
        if (filter.equals("")) {
            //regular query
            query = "SELECT * FROM TASK_TABLE";
        } else {
            //filter results by filter option provided
            query = "SELECT * FROM TASK_TABLE ORDER BY PRIORITY "+filter;

        }

        List<Reminder> ReminderLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Reminder reminder;
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                reminder = new Reminder();
                reminder.setID(Integer.parseInt(cursor.getString(0)));
                reminder.setCategory(cursor.getString(1));
                reminder.setTitle(cursor.getString(2));
                reminder.setDate(cursor.getString(3));
                reminder.setTime(cursor.getString(4));
                reminder.setRepeat(cursor.getString(5));
                reminder.setRepeatNo(cursor.getString(6));
                reminder.setRepeatType(cursor.getString(7));
                reminder.setActive(cursor.getString(8));
                reminder.setDueDate(cursor.getString(9));
                reminder.setCompletedTask(Integer.parseInt(cursor.getString(10)));
                reminder.setPriority(cursor.getString(11));
                reminder.setDue(cursor.getString(12));

                // Adding Reminders to list
                ReminderLinkedList.add(reminder);
            } while (cursor.moveToNext());
        }
        return ReminderLinkedList;
    }

    // Getting all Reminders based on date
    public List<Reminder> getAllTaskDue(String filter) {
        String query;
        if (filter.equals("")) {
            //regular query
            query = "SELECT * FROM TASK_TABLE";
        } else {
            //filter results by filter option provided
            query = "SELECT * FROM TASK_TABLE ORDER BY date(DATE) "+filter;

        }

        List<Reminder> ReminderLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Reminder reminder;
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                reminder = new Reminder();
                reminder.setID(Integer.parseInt(cursor.getString(0)));
                reminder.setCategory(cursor.getString(1));
                reminder.setTitle(cursor.getString(2));
                reminder.setDate(cursor.getString(3));
                reminder.setTime(cursor.getString(4));
                reminder.setRepeat(cursor.getString(5));
                reminder.setRepeatNo(cursor.getString(6));
                reminder.setRepeatType(cursor.getString(7));
                reminder.setActive(cursor.getString(8));
                reminder.setDueDate(cursor.getString(9));
                reminder.setCompletedTask(Integer.parseInt(cursor.getString(10)));
                reminder.setPriority(cursor.getString(11));
                reminder.setDue(cursor.getString(12));

                // Adding Reminders to list
                ReminderLinkedList.add(reminder);
            } while (cursor.moveToNext());
        }
        return ReminderLinkedList;
    }

    // Getting all Reminders based on Title
    public List<Reminder> getAllTaskTitle(String filter) {
        String query;
        if (filter.equals("")) {
            //regular query
            query = "SELECT * FROM TASK_TABLE";
        } else {
            //filter results by filter option provided
            query = "SELECT * FROM TASK_TABLE ORDER BY TITLE "+filter;

        }

        List<Reminder> ReminderLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Reminder reminder;
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                reminder = new Reminder();
                reminder.setID(Integer.parseInt(cursor.getString(0)));
                reminder.setCategory(cursor.getString(1));
                reminder.setTitle(cursor.getString(2));
                reminder.setDate(cursor.getString(3));
                reminder.setTime(cursor.getString(4));
                reminder.setRepeat(cursor.getString(5));
                reminder.setRepeatNo(cursor.getString(6));
                reminder.setRepeatType(cursor.getString(7));
                reminder.setActive(cursor.getString(8));
                reminder.setDueDate(cursor.getString(9));
                reminder.setCompletedTask(Integer.parseInt(cursor.getString(10)));
                reminder.setPriority(cursor.getString(11));
                reminder.setDue(cursor.getString(12));
                // Adding Reminders to list
                ReminderLinkedList.add(reminder);
            } while (cursor.moveToNext());
        }
        return ReminderLinkedList;
    }

    // Getting all Reminders based on Category Due Date
    public List<Reminder> getAllPersonalDue(String cat,String filter) {
        String query;

            //filter results by filter option provided
            query = "SELECT * FROM TASK_TABLE WHERE CATEGORY=="+cat+" ORDER BY date(DATE) "+filter;

        List<Reminder> ReminderLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Reminder reminder;
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                reminder = new Reminder();
                reminder.setID(Integer.parseInt(cursor.getString(0)));
                reminder.setCategory(cursor.getString(1));
                reminder.setTitle(cursor.getString(2));
                reminder.setDate(cursor.getString(3));
                reminder.setTime(cursor.getString(4));
                reminder.setRepeat(cursor.getString(5));
                reminder.setRepeatNo(cursor.getString(6));
                reminder.setRepeatType(cursor.getString(7));
                reminder.setActive(cursor.getString(8));
                reminder.setDueDate(cursor.getString(9));
                reminder.setCompletedTask(Integer.parseInt(cursor.getString(10)));
                reminder.setPriority(cursor.getString(11));
                reminder.setDue(cursor.getString(12));

                // Adding Reminders to list
                ReminderLinkedList.add(reminder);
            } while (cursor.moveToNext());
        }
        return ReminderLinkedList;
    }

    // Getting all Reminders based on Category Priority
    public List<Reminder> getAllPersonalPriority(String cat,String filter) {
        String query;

        //filter results by filter option provided
        query = "SELECT * FROM TASK_TABLE WHERE CATEGORY=="+cat+" ORDER BY PRIORITY "+filter;

        List<Reminder> ReminderLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Reminder reminder;
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                reminder = new Reminder();
                reminder.setID(Integer.parseInt(cursor.getString(0)));
                reminder.setCategory(cursor.getString(1));
                reminder.setTitle(cursor.getString(2));
                reminder.setDate(cursor.getString(3));
                reminder.setTime(cursor.getString(4));
                reminder.setRepeat(cursor.getString(5));
                reminder.setRepeatNo(cursor.getString(6));
                reminder.setRepeatType(cursor.getString(7));
                reminder.setActive(cursor.getString(8));
                reminder.setDueDate(cursor.getString(9));
                reminder.setCompletedTask(Integer.parseInt(cursor.getString(10)));
                reminder.setPriority(cursor.getString(11));
                reminder.setDue(cursor.getString(12));
                // Adding Reminders to list
                ReminderLinkedList.add(reminder);
            } while (cursor.moveToNext());
        }
        return ReminderLinkedList;
    }

    // Getting all Reminders based on Category Alphabet
    public List<Reminder> getAllPersonalAlphabet(String cat,String filter) {
        String query;

        //filter results by filter option provided
        query = "SELECT * FROM TASK_TABLE WHERE CATEGORY=="+cat+" ORDER BY TITLE "+filter;
        System.out.println(query);

        List<Reminder> ReminderLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Reminder reminder;
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                reminder = new Reminder();
                reminder.setID(Integer.parseInt(cursor.getString(0)));
                reminder.setCategory(cursor.getString(1));
                reminder.setTitle(cursor.getString(2));
                reminder.setDate(cursor.getString(3));
                reminder.setTime(cursor.getString(4));
                reminder.setRepeat(cursor.getString(5));
                reminder.setRepeatNo(cursor.getString(6));
                reminder.setRepeatType(cursor.getString(7));
                reminder.setActive(cursor.getString(8));
                reminder.setDueDate(cursor.getString(9));
                reminder.setCompletedTask(Integer.parseInt(cursor.getString(10)));
                reminder.setPriority(cursor.getString(11));
                reminder.setDue(cursor.getString(12));
                // Adding Reminders to list
                ReminderLinkedList.add(reminder);
            } while (cursor.moveToNext());
        }
        return ReminderLinkedList;
    }


    // Getting Reminders Count
    public int getRemindersCount() {
        String countQuery = "SELECT * FROM TASK_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
    // Getting Category Count
    public int getRemindersCount(String cat) {
        String countQuery = "SELECT * FROM TASK_TABLE WHERE CATEGORY=="+cat;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    // Updating single Reminder
    public int updateReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CATEGORY",reminder.getCategory());
        values.put("TITLE", reminder.getTitle());
        values.put("DATE", reminder.getDate());
        values.put("TIME", reminder.getTime());
        values.put("REPEAT", reminder.getRepeat());
        values.put("REPEAT_NO", reminder.getRepeatNo());
        values.put("REPEAT_TYPE", reminder.getRepeatType());
        values.put("ACTIVE", reminder.getActive());
        values.put("DUEDATE", reminder.getDueDate());
        values.put("COMPLETEDTASK",reminder.getCompletedTask());
        values.put("PRIORITY",reminder.getPriotity());
        values.put("DUE",reminder.getDue());

        // Updating row
        return db.update("TASK_TABLE", values, "ID" + "=?",
                new String[]{String.valueOf(reminder.getID())});
    }


    public String completedTask(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("TASK_TABLE", new String[]
                        {
                                "ID",
                                "CATEGORY",
                                "TITLE",
                                "DATE",
                                "TIME",
                                "REPEAT",
                                "REPEAT_NO",
                                "REPEAT_TYPE",
                                "ACTIVE",
                                "DUEDATE",
                                "COMPLETEDTASK",
                                "PRIORITY",
                                "DUE"
                        }, "ID=?",

                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("COMPLETEDTASK"));
    }
    //update completed task
    public void updateCompletedTask(int id,int no){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        Reminder reminder=new Reminder();
        values.put("COMPLETEDTASK",no);

        db.update("TASK_TABLE",values,"ID" + "=?",
                new String[]{String.valueOf(id)});
    }

    // Deleting single Reminder
    public void deleteReminder(int reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TASK_TABLE", "ID" + " = ?",
                new String[]{String.valueOf(reminder)});
        db.close();
    }

    //Insert Event
    public int insertEvent(Event event) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",event.getID());
        contentValues.put("TITLE", event.getTitle());
        contentValues.put("NOTE", event.getNote());
        contentValues.put("ICON", event.getIcon());
        contentValues.put("ALLDAY", event.getAllDay());
        contentValues.put("SDATE", event.getSDate());
        contentValues.put("EDATE", event.getEDate());
        contentValues.put("STIME", event.getSTime());
        contentValues.put("ETIME", event.geteETime());
        contentValues.put("LOCATION", event.getLocation());
        contentValues.put("NOTIFICATION", event.getNotification());
        contentValues.put("NOTIF",event.getNotif());
        contentValues.put("ACTIVE", event.getActive());
        contentValues.put("AVAIL", event.getAvail());

        int ID= (int) this.getReadableDatabase().insertOrThrow("EVENT_TABLE", "", contentValues);
            return ID;
    }

    //get single event

    public Event getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("EVENT_TABLE", new String[]
                        {
                                "ID",
                                "TITLE",
                                "NOTE",
                                "ICON",
                                "ALLDAY",
                                "SDATE",
                                "EDATE",
                                "STIME",
                                "ETIME",
                                "LOCATION",
                                "NOTIFICATION",
                                "NOTIF",
                                "ACTIVE",
                                "AVAIL"
                        }, "ID" + "=?",

                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Event event = new Event(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10),
                Integer.parseInt(cursor.getString(11)), cursor.getString(12), cursor.getString(13));

        return event;
    }

    // Getting all Reminders with filter
    public List<Event> getAllEvent(String filter) {
        String query;
        if (filter.equals("")) {
            //regular query
            query = "SELECT  * FROM EVENT_TABLE";
        } else {
            //filter results by filter option provided
            query = "SELECT  * FROM EVENT_TABLE ORDER BY " + filter;

        }

        List<Event> EventLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setID(Integer.parseInt(cursor.getString(0)));
                event.setTitle(cursor.getString(1));
                event.setNote(cursor.getString(2));
                event.setIcon(cursor.getString(3));
                event.seteAllDay(cursor.getString(4));
                event.setSDate(cursor.getString(5));
                event.setEDate(cursor.getString(6));
                event.setSTime(cursor.getString(7));
                event.setETime(cursor.getString(8));
                event.setLocation(cursor.getString(9));
                event.setNotification(cursor.getString(10));
                event.setNotif(Integer.parseInt(cursor.getString(11)));
                event.setActive(cursor.getString(12));
                event.setAvail(cursor.getString(13));

                // Adding events to list
                EventLinkedList.add(event);
            } while (cursor.moveToNext());
        }
        return EventLinkedList;
    }



    //Updating single event
    public int updateEvent(int ID, String Title, String Note, String Icon, String AllDay, String SDate, String EDate,
                           String STime, String ETime, String Location, String Notification, int Notif,
                           String Active, String Avail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE" , Title);
        values.put("NOTE", Note);
        values.put("ICON", Icon);
        values.put("ALLDAY" , AllDay);
        values.put("SDATE", SDate);
        values.put("EDATE" , EDate);
        values.put("STIME" ,STime);
        values.put("ETIME", ETime);
        values.put("LOCATION",Location);
        values.put("NOTIFICATION",Notification);
        values.put("NOTIF",Notif);
        values.put("ACTIVE",Active);
        values.put("AVAIL",Avail);

        // Updating row
        return db.update("EVENT_TABLE", values, "ID" + "=?",
                new String[]{String.valueOf(ID)});
    }
    //Deleting single event
    public void deleteEvent(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("EVENT_TABLE", "ID" + " = ?",
                new String[] { String.valueOf(id) });
    }

    //Adding Note
    void insertNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ID",note.getID());
        values.put("DATE_CREATED",note.getDate());
        values.put("NOTE",note.getNoteContent());

        // Inserting Row
        db.insert("NOTE_TABLE", null, values);
         // Closing database connection
    }
    // Updating single Reminder
    public int updateNote(int id,String date_created, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DATE_CREATED",date_created);
        values.put("NOTE",content);

        // Updating row
        return db.update("NOTE_TABLE", values, "ID" + "=?",
                new String[]{String.valueOf(id)});
    }
    // Getting single NOTE
    Note getNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("NOTE_TABLE", new String[] {
                        "ID",
                        "DATE_CREATED",
                        "NOTE"
                }, "ID" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Note note = new Note(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2));

        // return contact
        return note;

    }

    // Getting All Note
    public List<Note> getAllNote(String date) {
        String query;
        if (date.equals("")) {
            //regular query
            query = "SELECT  * FROM NOTE_TABLE";
        } else {
            //filter results by filter option provided
            query = "SELECT  * FROM NOTE_TABLE WHERE DATE_CREATED==" + date;

        }
        List<Note> noteList = new ArrayList<Note>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setID(Integer.parseInt(cursor.getString(0)));
                note.setDate(cursor.getString(1));
                note.setNoteContent(cursor.getString(2));
                // Adding contact to list
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return noteList;
    }
    //Deleting single note
    public void deleteNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("NOTE_TABLE", "ID" + " = ?",
                new String[] { String.valueOf(id) });
    }
//insert in calen table
    public void insertCalen(Calen calen) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",calen.getID());
        contentValues.put("DATE", calen.getDate());
        contentValues.put("TITLE", calen.getTitle());
        contentValues.put("TYPE", calen.getType());

        db.insert("CALEN_TABLE", null, contentValues);

    }
    // Updating single Reminder
    public int updateCalen(int id,String date, String title,String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DATE",date);
        values.put("TITLE",title);
        values.put("TYPE",type);

        // Updating row
        return db.update("CALEN_TABLE", values, "ID" + "=?",
                new String[]{String.valueOf(id)});
    }
    public void deleteCalen(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("CALEN_TABLE", "ID" + " = ?",
                new String[] { String.valueOf(id) });
    }
    // Getting All Calen based on date and type
    public List<Calen> getAllCalen(String date,String type) {
        String query;
        if (date.equals("")) {
            //regular query
            query = "SELECT  * FROM CALEN_TABLE";
        } else {
            //filter results by filter option provided
            query = "SELECT  * FROM CALEN_TABLE WHERE DATE==" +"\""+ date +"\"" +" AND TYPE=="+"\""+type+"\"";

        }
        List<Calen> calenList = new ArrayList<Calen>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Calen calen=new Calen();
                calen.setID(Integer.parseInt(cursor.getString(0)));
                calen.setDate(cursor.getString(1));
                calen.setTitle(cursor.getString(2));
                calen.setType(cursor.getString(3));
                // Adding contact to list
                calenList.add(calen);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return calenList;
    }
    // Getting All Calen based on date and type
    public List<Calen> getAllCalenType(String date) {
        String query;
        if (date.equals("")) {
            //regular query
            query = "SELECT  * FROM CALEN_TABLE";
        } else {
            //filter results by filter option provided
            query = "SELECT  * FROM CALEN_TABLE WHERE TYPE in('event','holiday') AND DATE==" +"\""+ date +"\"";

        }
        List<Calen> calenList = new ArrayList<Calen>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Calen calen=new Calen();
                calen.setID(Integer.parseInt(cursor.getString(0)));
                calen.setDate(cursor.getString(1));
                calen.setTitle(cursor.getString(2));
                calen.setType(cursor.getString(3));
                // Adding contact to list
                calenList.add(calen);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return calenList;
    }
    // Getting All Calen based on date and type
    public List<Calen> getAllCalenTypeV2(String date) {
        String query;
        if (date.equals("")) {
            //regular query
            query = "SELECT  * FROM CALEN_TABLE";
        } else {
            //filter results by filter option provided
            query = "SELECT  * FROM CALEN_TABLE WHERE DATE==" +"\""+ date +"\"";

        }
        List<Calen> calenList = new ArrayList<Calen>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Calen calen=new Calen();
                calen.setID(Integer.parseInt(cursor.getString(0)));
                calen.setDate(cursor.getString(1));
                calen.setTitle(cursor.getString(2));
                calen.setType(cursor.getString(3));
                // Adding contact to list
                calenList.add(calen);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return calenList;
    }
    public List<String> getAllDate() {
        String query;
        query = "SELECT DISTINCT DATE FROM CALEN_TABLE";
        List<String> calenList = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        int contentpathColumn = cursor.getColumnIndex("DATE");
        if (cursor.moveToFirst()) {
            do {
                String contentpath=cursor.getString(contentpathColumn);
                // Adding contact to list
                calenList.add(contentpath);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return calenList;
    }
    // Getting Date Count
    public int getDateCount(String date) {
        String countQuery = "SELECT * FROM CALEN_TABLE WHERE DATE=="+"\""+date+"\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
    public int CalenCount() {
        String countQuery = "SELECT * FROM CALEN_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public void deleteAllCalen(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("CALEN_TABLE", null, null);
    }
    public void deleteAllTask(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("TASK_TABLE", null, null);
    }
    public void deleteAllEvent(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("EVENT_TABLE", null, null);
    }
    public void deleteAllNote(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("NOTE_TABLE", null, null);
    }



}



