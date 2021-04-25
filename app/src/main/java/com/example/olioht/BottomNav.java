package com.example.olioht;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNav extends AppCompatActivity {

    App App = new App(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //Keeps the selected fragment when rotating the device
        if (savedInstanceState == null) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragments,
         new SettingsFragment()).commit();
        }
    }


    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch(item.getItemId()){
                    case R.id.nav_settings:
                        selectedFragment = new SettingsFragment();
                        Bundle bundle1 = new Bundle();
                        bundle1.putSerializable("settKey", App);
                        selectedFragment.setArguments(bundle1);
                        break;
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        Bundle bundle2 = new Bundle();
                        bundle2.putSerializable("homKey", App);
                        selectedFragment.setArguments(bundle2);
                        break;
                    case R.id.nav_results:
                        selectedFragment = new ResultsFragment();
                        Bundle bundle3 = new Bundle();
                        bundle3.putSerializable("achKey", App);
                        selectedFragment.setArguments(bundle3);
                        break;
                }
                assert selectedFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments,
                        selectedFragment).commit();
                return true;
            };

}
