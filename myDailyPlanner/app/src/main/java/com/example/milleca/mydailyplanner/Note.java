package com.example.milleca.mydailyplanner;

/**
 * Created by milleca on 1/11/2018.
 */

public class Note {
   int id;
    String date_created;
    String note_content;
    // Empty constructor
    public Note() {

    }

    // constructor
    public Note(int keyId, String date_created,String note_content) {
        this.id = keyId;
        this.date_created=date_created;
        this.note_content = note_content;

    }
    public Note(String date_created,String note_content) {
        this.date_created=date_created;
        this.note_content = note_content;

    }
    public Note(int keyId) {
        this.id = keyId;

    }


    // getting ID
    public int getID() {
        return this.id;
    }

    // setting id
    public void setID(int keyId) {
        this.id = keyId;
    }

    public String getDate() {
        return this.date_created;
    }


    public void setDate(String date_created) {
        this.date_created = date_created;
    }


    public String getNoteContent() {
        return this.note_content;
    }


    public void setNoteContent(String note_content) {
        this.note_content = note_content;
    }


}


