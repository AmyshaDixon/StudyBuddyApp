package com.example.adixon.studyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class StudySessionAdapter extends ArrayAdapter<StudySession> {
    private int resourceLayout;
    private Context mContext;

    public StudySessionAdapter(Context context, int resource, List<StudySession> ss) {
        super(context, resource, ss);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        StudySession s = super.getItem(position);

        if (s != null) {
            TextView tvDT = v.findViewById(R.id.tvDisplayTopic);
            TextView tvDF = v.findViewById(R.id.tvDisplayFrequency);

            if (tvDT != null) {
                tvDT.setText(s.getTopic());
            }

            if (tvDF != null) {
                tvDF.setText(s.getFrequency());
            }
        }

        return v;
    }
}
