package com.example.olioht;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

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
        App App = (App) bundle.getSerializable("homKey");

        logcalories = this.view.findViewById(R.id.button);
        barcode = this.view.findViewById(R.id.editTextNumber);
        amountml = this.view.findViewById(R.id.editTextNumber2);

        logcalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

            }
        });

        return view;

    }
}
