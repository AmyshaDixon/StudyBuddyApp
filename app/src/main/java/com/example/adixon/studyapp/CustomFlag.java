package com.example.adixon.studyapp;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.FlagView;

public class CustomFlag extends FlagView {

    private TextView textView;
    private View view;

    public CustomFlag(Context context, int layout) {
        super(context, layout);
        textView = findViewById(R.id.flag_color_code);
        view = findViewById(R.id.flag_color_layout);
    }

    @Override
    public void onRefresh(ColorEnvelope c) {
        textView.setText("#" + String.format("%06X", (0xFFFFFF & c.getColor())));
        view.setBackgroundColor(c.getColor());
    }

    /* Original code below is not working, trying above code instead */
    /*@Override
    public void onRefresh(int color) {
        textView.setText("#" + String.format("%06X", (0xFFFFFF & color)));
        view.setBackgroundColor(color);
    }*/
}