package com.example.olioht;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class LoginTabFragment extends Fragment {

    Button btnLogin, btnRegister;
    EditText etUsername, etPassword;
    CallbackFragment callbackFragment;
    String userName, password;
    SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.login_tab_fragment, container, false);
        etUsername = view.findViewById(R.id.username);
        etPassword = view.findViewById(R.id.password);
        btnLogin = view.findViewById(R.id.login_button);
        btnRegister = view.findViewById(R.id.register_button);


        btnLogin.setOnClickListener(v -> {
            userName = etUsername.getText().toString();
            password = etPassword.getText().toString();
            String uName, uPass;
            sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("User: "+ userName, Context.MODE_PRIVATE);
            uName = sharedPreferences.getString("userName", null);
            uPass = sharedPreferences.getString("password", null);

            //Check that username and password match each other in the xml file
            //If they match go to the main app, else display error message
            if(userName.equals(uName) && password.equals(uPass)){
                Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
                sendToActivity(userName);
            }else{
                Toast.makeText(getContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
            }

        });
        btnRegister.setOnClickListener(v -> {
            if(callbackFragment !=null){
                callbackFragment.changeFragment();
            }
        });
        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment){
        this.callbackFragment = callbackFragment;
    }

    public void sendToActivity(String s){
        Intent intent = new Intent(Objects.requireNonNull(getActivity()).getBaseContext(), BottomNav.class);
        startActivity(intent);
    }

}
