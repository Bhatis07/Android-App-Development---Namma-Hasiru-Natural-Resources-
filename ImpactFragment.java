package com.internshipproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.button.MaterialButton;

public class ImpactFragment extends Fragment {

    private SaplingViewModel saplingViewModel;
    private TextView tvSurvivalRate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_impact, container, false);
        
        tvSurvivalRate = view.findViewById(R.id.tv_survival_rate_percent);
        MaterialButton btnDonate = view.findViewById(R.id.btn_donate);
        
        saplingViewModel = new ViewModelProvider(this).get(SaplingViewModel.class);
        saplingViewModel.getAllSaplings().observe(getViewLifecycleOwner(), saplings -> {
            if (tvSurvivalRate != null) {
                // Dummy calculation for demonstration
                if (saplings.isEmpty()) {
                    tvSurvivalRate.setText("0%");
                } else {
                    tvSurvivalRate.setText("94.2%"); // Static for now as per prototype
                }
            }
        });

        if (btnDonate != null) {
            btnDonate.setOnClickListener(v -> 
                Toast.makeText(getActivity(), "Redirecting to donation page...", Toast.LENGTH_SHORT).show());
        }

        return view;
    }
}
