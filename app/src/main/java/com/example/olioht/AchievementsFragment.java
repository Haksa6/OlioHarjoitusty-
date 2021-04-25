package com.example.olioht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class AchievementsFragment extends Fragment {

    App App = null;
    View view;
    EditText showInfo;
    Button show, showlast, allCal;
    String dataAll, datalast, dataCal;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_achievements, container, false);

        Bundle bundle = getArguments();
        App App = (App) bundle.getSerializable("achKey");

        showInfo = this.view.findViewById(R.id.showinfo);
        show = this.view.findViewById(R.id.button2);
        showlast = this.view.findViewById(R.id.button3);
        allCal = this.view.findViewById(R.id.button4);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dataAll = App.readEntries();
               showInfo.setText(dataAll);
               }
        });

        showlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datalast = App.readLast();
                showInfo.setText(datalast);
            }
        });

        allCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCal = App.getAllCal();
                showInfo.setText(dataCal);
            }
        });


        return view;
    }
}
