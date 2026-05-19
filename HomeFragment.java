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

public class HomeFragment extends Fragment {

    private SaplingViewModel saplingViewModel;
    private TextView tvSaplingsCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvSaplingsCount = view.findViewById(R.id.tv_home_saplings_count);
        View cardAdd = view.findViewById(R.id.card_action_add);

        saplingViewModel = new ViewModelProvider(this).get(SaplingViewModel.class);
        saplingViewModel.getAllSaplings().observe(getViewLifecycleOwner(), saplings -> {
            if (tvSaplingsCount != null) {
                tvSaplingsCount.setText(String.valueOf(saplings.size()));
            }
        });

        if (cardAdd != null) {
            cardAdd.setOnClickListener(v -> {
                // Dummy add action to show live update
                Sapling newSapling = new Sapling("New Sapling", "Green Zone", "Species X", "Healthy", "Just now");
                saplingViewModel.insert(newSapling);
                Toast.makeText(getActivity(), "Sapling planted successfully!", Toast.LENGTH_SHORT).show();
            });
        }

        return view;
    }
}
