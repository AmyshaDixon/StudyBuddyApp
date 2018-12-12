package com.example.adixon.studyapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditASessionActivity extends AppCompatActivity {
    /* Create DB helper */
    private DatabaseHelper dbHelp;

    /* Variables to display all data */
    ArrayList<StudySession> allStudySessions;
    ListView studyView;
    StudySessionAdapter adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_asession);

        /* Set up DB */
        dbHelp = new DatabaseHelper(this);

        /* Assign variables */
        allStudySessions = dbHelp.getAllStudySessionsInList();
        studyView = findViewById(R.id.studySessionList);

        /* Load db data to page */
        displayStudySessions();

        /* When one is selected */
        studyView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = studyView.getItemAtPosition(i).toString();

                Toast.makeText(EditASessionActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayStudySessions() {
        Cursor result = dbHelp.getAllStudySessions();

        if(result.getCount() == 0) { // If there is no data yet
            displayUserMessage("Hey!", "There are no sessions yet.");

            return;
        }

        /* Practice with buffer; used to display in message */
        //StringBuffer buffer = new StringBuffer();

//        while (result.moveToNext()) {
//            /* Practice with buffer; create string */
//            //buffer.append("ID : " + result.getString(0) + "\n");
//            //buffer.append("Topic : " + result.getString(1) + "\n");
//            //buffer.append("Frequency : " + result.getString(3) + "\n\n");
//
//            allStudySessions.add("Study " + result.getString(1) + " " +
//                    result.getString(3));
//        }

        //adapt = new ArrayAdapter<>(this, R.layout.list_row_layout, allStudySessions);
        adapt = new StudySessionAdapter(this, R.layout.list_row_layout, allStudySessions);

        /* Crashing with this line */
        studyView.setAdapter(adapt);

        /* Practice with buffer; show all data */
        //displayUserMessage("Yay!", buffer.toString());
    }

    private void displayUserMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true); // Cancels after used

        builder.setTitle(title);

        builder.setMessage(message);

        builder.show();
    }
}
