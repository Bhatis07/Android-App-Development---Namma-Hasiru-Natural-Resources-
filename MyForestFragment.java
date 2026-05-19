package com.internshipproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.app.Activity.RESULT_OK;

public class MyForestFragment extends Fragment {

    private SaplingViewModel saplingViewModel;
    private SaplingAdapter adapter;
    private TextView tvTotalSaplings;
    private TextView tvEmptyForest;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    openCamera();
                } else {
                    Toast.makeText(getActivity(), "Camera permission denied", Toast.LENGTH_SHORT).show();
                }
            });

    private final ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle extras = result.getData().getExtras();
                    if (extras != null && extras.containsKey("data")) {
                        // Image data is available in extras.get("data")
                        Toast.makeText(getActivity(), "Tree photo captured!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_forest, container, false);

        tvTotalSaplings = view.findViewById(R.id.tv_total_saplings_count);
        tvEmptyForest = view.findViewById(R.id.tv_empty_forest);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_saplings);
        FloatingActionButton fab = view.findViewById(R.id.fab_add_sapling);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setHasFixedSize(true);
            adapter = new SaplingAdapter();
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new SaplingAdapter.OnItemClickListener() {
                @Override
                public void onUpdateClick(Sapling sapling) {
                    startActivity(new Intent(getActivity(), GrowthUpdateActivity.class));
                }

                @Override
                public void onAlertClick(Sapling sapling) {
                    startActivity(new Intent(getActivity(), GrowthAlertActivity.class));
                }

                @Override
                public void onCaptureClick(Sapling sapling) {
                    if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_GRANTED) {
                        openCamera();
                    } else {
                        requestPermissionLauncher.launch(Manifest.permission.CAMERA);
                    }
                }
            });
        }

        saplingViewModel = new ViewModelProvider(this).get(SaplingViewModel.class);
        saplingViewModel.getAllSaplings().observe(getViewLifecycleOwner(), saplings -> {
            if (adapter != null) {
                adapter.setSaplings(saplings);
            }
            if (tvTotalSaplings != null) {
                tvTotalSaplings.setText(String.valueOf(saplings.size()));
            }
            
            if (saplings.isEmpty()) {
                if (tvEmptyForest != null) tvEmptyForest.setVisibility(View.VISIBLE);
                if (recyclerView != null) recyclerView.setVisibility(View.GONE);
            } else {
                if (tvEmptyForest != null) tvEmptyForest.setVisibility(View.GONE);
                if (recyclerView != null) recyclerView.setVisibility(View.VISIBLE);
            }
        });

        if (fab != null) {
            fab.setOnClickListener(v -> {
                Sapling newSapling = new Sapling("The Guardian Neem", "Cubbon Park", "Azadirachta indica", "Healthy", "Today");
                saplingViewModel.insert(newSapling);
                Toast.makeText(getActivity(), "New sapling added to your forest!", Toast.LENGTH_SHORT).show();
            });
        }

        return view;
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            cameraLauncher.launch(takePictureIntent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Camera error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
