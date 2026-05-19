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

public class ProfileFragment extends Fragment {

    private SaplingViewModel saplingViewModel;
    private TextView tvTreesPlantedCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvTreesPlantedCount = view.findViewById(R.id.tv_trees_planted_count);
        MaterialButton btnEditProfile = view.findViewById(R.id.btn_edit_profile);
        MaterialButton btnLogout = view.findViewById(R.id.btn_logout);

        saplingViewModel = new ViewModelProvider(this).get(SaplingViewModel.class);
        saplingViewModel.getAllSaplings().observe(getViewLifecycleOwner(), saplings -> {
            if (tvTreesPlantedCount != null) {
                tvTreesPlantedCount.setText(String.valueOf(saplings.size()));
            }
        });

        btnEditProfile.setOnClickListener(v -> 
            Toast.makeText(getActivity(), "Edit Profile coming soon!", Toast.LENGTH_SHORT).show());

        btnLogout.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Logged out", Toast.LENGTH_SHORT).show();
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        return view;
    }
}
