package com.example.olioht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class ResultsFragment extends Fragment {

    App App = null;
    View view;
    EditText showInfo;
    Button show10, showLast, allCal;
    String dataAll, dataLast, dataCal;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_results, container, false);

        Bundle bundle = getArguments();
        App = (App) bundle.getSerializable("achKey");

        showInfo = this.view.findViewById(R.id.showinfo);
        show10 = this.view.findViewById(R.id.show10);
        showLast = this.view.findViewById(R.id.showLast);
        allCal = this.view.findViewById(R.id.allCalories);

        show10.setOnClickListener(v -> {
            dataAll = App.readEntries();
            showInfo.setText(dataAll);
        });

        showLast.setOnClickListener(v -> {
            dataLast = App.readLast();
            showInfo.setText(dataLast);
        });

        allCal.setOnClickListener(v -> {
            dataCal = App.getAllCal();
            showInfo.setText(dataCal);
        });


        return view;
    }
}
