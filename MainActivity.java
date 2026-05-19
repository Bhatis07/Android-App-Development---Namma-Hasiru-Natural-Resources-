package com.internshipproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                selected = new HomeFragment();
            } else if (id == R.id.nav_forest) {
                selected = new MyForestFragment();
            } else if (id == R.id.nav_impact) {
                selected = new ImpactFragment();
            } else if (id == R.id.nav_profile) {
                selected = new ProfileFragment();
            }
            if (selected != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selected)
                        .commit();
            }
            return true;
        });
        
        // Start with Impact tab as it has the most comprehensive prototype (Image 6)
        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.nav_impact);
        }
    }
}