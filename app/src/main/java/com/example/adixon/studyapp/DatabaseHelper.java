package com.example.adixon.studyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String tag = "DatabaseHelper";

    /* Create variables to hold table/column info */
    private static final String DBName = "StudySessions.db";
    private static final String tableStudy = "StudySessionsTable";
    private static final String colID = "ID";
    private static final String colTopic = "Topic";
    private static final String colMeasure = "Measurement";
    private static final String colFreq = "Frequency";
    private static final String colDura = "Duration";
    private static final String colDuraType = "DurationType";
    private static final String colWeekDay = "WeekDay";
    private static final String colDate = "Date";
    private static final String colColor = "Color";

    public DatabaseHelper(Context context) {
        super(context, DBName, null, 1); // 1 passed in for error handling; versions for every change
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + tableStudy + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                colTopic + " TEXT," +
                colMeasure + " TEXT," +
                colFreq + " TEXT," +
                colDura + " INTEGER," +
                colDuraType + " TEXT," +
                colWeekDay + " TEXT," +
                colDate + " TEXT," +
                colColor + " TEXT" + ")";

        db.execSQL(createTable); // Executes whatever query passed in
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableStudy);

        onCreate(db);
    }

    public boolean addSession (StudySession session) {
        SQLiteDatabase db = this.getWritableDatabase(); // Actually creates db

        ContentValues cv = new ContentValues(); // Helps write to the DB

        /* Add items from session object to each column in DB */
        cv.put(colTopic, session.getTopic());
        cv.put(colMeasure, session.getMeasurement());
        cv.put(colFreq, session.getFrequency());
        cv.put(colDura, session.getDuration());
        cv.put(colDuraType, session.getDurationType());
        cv.put(colWeekDay, session.getWeekDay());
        cv.put(colDate, session.getMonthlyDate());
        cv.put(colColor, session.getColor());


        /* If result > 0, then insert successful, else not */
        long result = db.insert(tableStudy, null, cv);

        /* Returns boolean to see if data returned correctly based on result */
        if(result == -1) {
            return false; // Not inserted correctly
        }
        else {
            return true; // Successful
        }
    }

    public Cursor getAllStudySessions() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM " + tableStudy, null); // Allows access to data

        return result;
    }

    public ArrayList<StudySession> getAllStudySessionsInList() {
        ArrayList<StudySession> ss = new ArrayList<>();
        Cursor result = getAllStudySessions();

        while (result.moveToNext()) {
            String col1 = result.getString(1); // Topic
            String col2 = result.getString(2); // Measurement
            String col3 = result.getString(3); // Frequency
            String col4 = result.getString(4); // Duration
            String col5 = result.getString(5); // Duration type
            String col6 = result.getString(6); // Weekday
            String col7 = result.getString(7); // Date
            String col8 = result.getString(8); // Color

            ss.add(new StudySession(col1, col2, (col3), Integer.parseInt(col4), col5, col6, col7, col8));
        }

        return ss;
    }
}
