package com.example.olioht;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    SettingsFragment sett = new SettingsFragment();
    HomeFragment hom = new HomeFragment();
    AchievementsFragment ach = new AchievementsFragment();
    App App = new App(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);



        //Keeps the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragments,
                    sett).commit();
        }

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    // in all send App object to the fragment, doesn't work on sett fragment
                    // as it opens to that fragment.
                    switch (item.getItemId()) {
                        case R.id.nav_settings:
                            selectedFragment = sett;
                            Bundle bundle1 = new Bundle();
                            bundle1.putSerializable("settKey", App);
                            sett.setArguments(bundle1);
                            break;
                        case R.id.nav_home:
                            selectedFragment = hom;
                            Bundle bundle2 = new Bundle();
                            bundle2.putSerializable("homKey", App);
                            hom.setArguments(bundle2);
                            break;
                        case R.id.nav_achievements:
                            selectedFragment = ach;
                            Bundle bundle3 = new Bundle();
                            bundle3.putSerializable("achKey", App);
                            ach.setArguments(bundle3);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragments,
                            selectedFragment).commit();
                    return true;
                }
            };


}