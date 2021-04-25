package com.example.olioht;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.util.Objects;

public class RegisterTabFragment extends Fragment {

    Button  btnRegister;
    EditText etUsername, etPassword, etConfirmPass;
    String userName, password, confirmPassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //@Override
    //public void onAttach(@NonNull Context context) {
        //sharedPreferences = context.getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        //editor = sharedPreferences.edit();
        //super.onAttach(context);
    //}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.signup_tab_fragment, container, false);
        etUsername = view.findViewById(R.id.username2);
        etPassword = view.findViewById(R.id.password2);
        etConfirmPass = view.findViewById(R.id.confirm_password);
        btnRegister = view.findViewById(R.id.register_button2);
        PasswordStrength strength = new PasswordStrength();

        btnRegister.setOnClickListener(v -> {
            userName = etUsername.getText().toString();
            password = etPassword.getText().toString();
            confirmPassword = etConfirmPass.getText().toString();
            switch (strength.getStrongNess(password, confirmPassword)) {
                case "TOO SHORT":
                    Toast.makeText(getContext(), "The password needs to be at least 12 characters long", Toast.LENGTH_SHORT).show();
                    break;
                case "NO LOWER":
                    Toast.makeText(getContext(), "The password needs to contain a lowercase letter", Toast.LENGTH_SHORT).show();
                    break;
                case "NO UPPER":
                    Toast.makeText(getContext(), "The password needs to contain an uppercase letter", Toast.LENGTH_SHORT).show();
                    break;
                case "NO NUMBA":
                    Toast.makeText(getContext(), "The password needs to contain a number", Toast.LENGTH_SHORT).show();
                    break;
                case "NO SPESSY":
                    Toast.makeText(getContext(), "The password needs to contain a special character", Toast.LENGTH_SHORT).show();
                    break;
                case "NO MATCH":
                    Toast.makeText(getContext(), "The passwords don't match", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("User: "+ userName, Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("userName", userName);
                    editor.putString("password", password);
                    editor.apply();
                    Toast.makeText(getContext(), "Registered", Toast.LENGTH_SHORT).show();
                    getFragmentManager().popBackStack();
                    break;
            }
        });

        return view;
    }

}
