package com.example.adixon.studyapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;


public class PlanASessionActivity extends AppCompatActivity {
    /* Create DB helper */
    private DatabaseHelper dbHelp;

    /* Create object variables */
    private EditText etTopic;

    private TextView tvMeasurement;
    private Spinner measurement;

    private TextView tvFrequency;
    private Spinner frequency;

    private TextView tvDuration;
    private EditText etDuration;
    private Spinner duration;

    //private ColorPickerView cp;


    private Button btnAddNewSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_asession);

        /* Set up color picker */
        //cp = findViewById(R.id.colorPicker);
        //cp.setFlagView(new CustomFlag(this, R.layout.flag_layout));



        /* Set up DB */
        dbHelp = new DatabaseHelper(this);

        /* Set variables */
        etTopic = findViewById(R.id.etStudyTopic);

        tvMeasurement = findViewById(R.id.tvStudyMeasurement);
        measurement = findViewById(R.id.spStudyMeasurement);

        tvFrequency = findViewById(R.id.tvStudyFrequency);
        frequency = findViewById(R.id.spStudyFrequency);

        tvDuration = findViewById(R.id.tvStudyDuration);
        etDuration = findViewById(R.id.etStudyDuration);
        duration = findViewById(R.id.spStudyDuration);

        btnAddNewSession = findViewById(R.id.btnAddNewSession);

        /* Create adapters for spinners */
        ArrayAdapter<CharSequence> adaptM = ArrayAdapter.createFromResource(this,
                R.array.arrayStudyMeasurements, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adaptF = ArrayAdapter.createFromResource(this,
                R.array.arrayStudyFrequencies, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adaptD = ArrayAdapter.createFromResource(this,
                R.array.arrayStudyDurations, android.R.layout.simple_spinner_item);

        /* Fill spinners on page */
        fillSpinners(measurement, adaptM);
        fillSpinners(frequency, adaptF);
        fillSpinners(duration, adaptD);

        /* Hide buttons, spinners, labels, and lines until previous input filled */
//        tvMeasurement.setVisibility(View.GONE);
//        measurement.setVisibility(View.GONE);
//
//        tvFrequency.setVisibility(View.GONE);
//        frequency.setVisibility(View.GONE);
//
//        tvDuration.setVisibility(View.GONE);
//        etDuration.setVisibility(View.GONE);
//        duration.setVisibility(View.GONE);
//
//        btnAddNewSession.setVisibility(View.GONE);
    }

    private void fillSpinners (Spinner s, ArrayAdapter a) {
        // Specify the layout to use when the list of choices appears
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        s.setAdapter(a);
    }

    public void clickAddSessionDB(View view) {
        if(etTopic.getText() != null || etDuration.getText().toString() != "") {
            StudySession ss = new StudySession(etTopic.getText().toString(),
                    measurement.getSelectedItem().toString(),
                    frequency.getSelectedItem().toString(),
                    Integer.parseInt(etDuration.getText().toString()),
                    duration.getSelectedItem().toString());

            addSessionDB(ss);
        }
        else {
            Toast.makeText(this, "Input is empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void addSessionDB(StudySession ss) {
        if(dbHelp.addSession(ss)) {
            startActivity(new Intent(PlanASessionActivity.this, EditASessionActivity.class));

            Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Terrible news...", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickOpenColorPicker(View view) {
        ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this,
                AlertDialog.THEME_DEVICE_DEFAULT_DARK);

        builder.setTitle("ColorPicker Dialog");
        builder.setPreferenceName("ColorPickerDialog");
        builder.setFlagView(new CustomFlag(this, R.layout.flag_layout));

        builder.setPositiveButton("Submit", new ColorListener() {
            @Override
            public void onColorSelected(ColorEnvelope colorEnvelope) {
                TextView selectedColor = findViewById(R.id.tvDialogSelectedColor);
                selectedColor.setText("#" + colorEnvelope.getColorHtml());

                LinearLayout dColorBox = findViewById(R.id.lyDialogColorBox);
                LinearLayout pColorBox = findViewById(R.id.lyPASColorBox);
                dColorBox.setBackgroundColor(colorEnvelope.getColor());
                pColorBox.setBackgroundColor(colorEnvelope.getColor());
            }
        });
        builder.setNegativeButton("Nevermind", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
