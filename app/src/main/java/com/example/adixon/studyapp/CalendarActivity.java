package com.example.adixon.studyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {
    /* Create variables for buttons and text box */
    Button btnE;
    Button btnD;
    Button btnEd;
    TextView sessionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        /* Assign variables to buttons */
        btnE = findViewById(R.id.btnEdit);
        btnD = findViewById(R.id.btnDelete);
        btnEd = findViewById(R.id.btnEdit);
        sessionInfo = findViewById(R.id.studySessionInfo);

        /* Hide buttons and text until needed */
        btnE.setVisibility(View.GONE);
        btnD.setVisibility(View.GONE);
        sessionInfo.setVisibility(View.GONE);
    }

    public void clickBtnAdd(View view) {
        startActivity(new Intent(CalendarActivity.this, PlanASessionActivity.class));
    }

    // public void ifSessionIsClicked() {
    //     btnE & btnD visible, btnEd invisible
    // }
}
