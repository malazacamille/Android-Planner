package com.example.milleca.mydailyplanner;

/**
 * Created by milleca on 12/23/2017.
 */

public class Reminder {
    private int mID;
    private String mTitle;
    private String mDate;
    private String mTime;
    private String mRepeat;
    private String mRepeatNo;
    private String mRepeatType;
    private String mActive;
    private String mCategory;
    private String mDueDate;
    private int mCompletedTask;
    private String mPriority;
    private String mDue;



    public Reminder(int ID,String Category, String Title, String Date, String Time, String Repeat, String RepeatNo, String RepeatType, String Active, String DueDate, int CompletedTask,String Priority,String Due){
        mID = ID;
        mCategory=Category;
        mTitle = Title;
        mDate = Date;
        mTime = Time;
        mRepeat = Repeat;
        mRepeatNo = RepeatNo;
        mRepeatType = RepeatType;
        mActive = Active;
        mDueDate=DueDate;
        mCompletedTask=CompletedTask;
        mPriority=Priority;
        mDue=Due;
    }

    public Reminder(String Title,String Category, String Date, String Time, String Repeat, String RepeatNo, String RepeatType, String Active, String DueDate, int CompletedTask,String Priority, String Due){
        mTitle = Title;
        mCategory=Category;
        mDate = Date;
        mTime = Time;
        mRepeat = Repeat;
        mRepeatNo = RepeatNo;
        mRepeatType = RepeatType;
        mActive = Active;
        mDueDate=DueDate;
        mCompletedTask=CompletedTask;
        mPriority=Priority;
        mDue=Due;
    }

    public Reminder(){}

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String Category) {
        mCategory = Category;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getRepeatType() {
        return mRepeatType;
    }

    public void setRepeatType(String repeatType) {
        mRepeatType = repeatType;
    }

    public String getRepeatNo() {
        return mRepeatNo;
    }

    public void setRepeatNo(String repeatNo) {
        mRepeatNo = repeatNo;
    }

    public String getRepeat() {
        return mRepeat;
    }

    public void setRepeat(String repeat) {
        mRepeat = repeat;
    }

    public String getActive() {
        return mActive;
    }

    public void setActive(String active) {
        mActive = active;
    }

    public String getDueDate() {
        return mDueDate;
    }

    public void setDueDate(String DueDate) {
        mDueDate = DueDate;
    }

    public int getCompletedTask() {
        return mCompletedTask;
    }

    public void setCompletedTask(int CompletedTask) {
        mCompletedTask = CompletedTask;
    }

    public String getPriotity() {
        return mPriority;
    }

    public void setPriority(String Priority) {
        mPriority = Priority;
    }

    public String getDue() {return mDue;
    }

    public void setDue(String Due) {
        mDue = Due;
    }
}