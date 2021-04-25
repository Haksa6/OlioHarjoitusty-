package com.example.olioht;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class HomeFragment extends Fragment {
    App App = null;
    View view;
    EditText barcode, amountml;
    Button logcalories;
    String aconv, bconv;
    int status;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle bundle = getArguments();
        App = (App) bundle.getSerializable("homKey");

        logcalories = this.view.findViewById(R.id.enter);
        barcode = this.view.findViewById(R.id.writeyourbarcode);
        amountml = this.view.findViewById(R.id.writeyouramount);

        //Takes barcode and amount, if they're valid
        logcalories.setOnClickListener(v -> {
            bconv = barcode.getText().toString();
            aconv = amountml.getText().toString();
            status = App.SaveEntry(bconv, aconv);
            if (status == 1) {
                barcode.getText().clear();
                amountml.getText().clear();
            } else if (status == 0) {
                AlertDialog.Builder notValid = new AlertDialog.Builder(getContext());
                notValid.setMessage("Give valid barcode and/or amount");
                notValid.setCancelable(true);
                notValid.setPositiveButton("OK",
                        (dialog, which) -> dialog.dismiss());
                AlertDialog validError = notValid.create();
                validError.show();
            }

        });

        return view;

    }
}
