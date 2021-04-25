package com.example.olioht;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    Button logoutButton, setpersonal;
    EditText height, weight;
    TextView showBMI;
    String BMI;
    String heighti, weighti;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_settings, container, false);


        height = view.findViewById(R.id.height);
        weight = view.findViewById(R.id.weight);
        logoutButton = view.findViewById(R.id.logout_button);
        setpersonal = view.findViewById(R.id.button2);
        showBMI = view.findViewById(R.id.showBMI);

        logoutButton.setOnClickListener(v -> {
            logoutUser();
        });

        setpersonal.setOnClickListener(v -> {
            App App1 = new App(getContext());
            try {
                heighti = height.getText().toString();
                weighti = weight.getText().toString();
                BMI = App1.getBMI(heighti, weighti);
                showBMI.setText(BMI);
            } catch (NumberFormatException e) {
                AlertDialog.Builder notValid = new AlertDialog.Builder(getContext());
                notValid.setMessage("Height and/or weight not given");
                notValid.setCancelable(true);
                notValid.setPositiveButton("OK",
                        (dialog, which) -> dialog.dismiss());
                AlertDialog validError = notValid.create();
                validError.show();
            }

        });

        return view;




    }


    public void logoutUser(){

        // After logout redirect user to MainActivity
        Intent i = new Intent(Objects.requireNonNull(getActivity()).getBaseContext(), MainActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        startActivity(i);

    }
}
