package com.internshipproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;

public class GrowthAlertActivity extends AppCompatActivity {
    private ImageView ivCapturedTree;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    openCamera();
                } else {
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
                }
            });

    private final ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle extras = result.getData().getExtras();
                    if (extras != null) {
                        Object data = extras.get("data");
                        if (data instanceof Bitmap) {
                            Bitmap imageBitmap = (Bitmap) data;
                            if (ivCapturedTree != null) {
                                ivCapturedTree.setImageBitmap(imageBitmap);
                                ivCapturedTree.setVisibility(View.VISIBLE);
                            }
                            Toast.makeText(this, "Photo captured!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth_alert);

        ivCapturedTree = findViewById(R.id.iv_captured_tree);
        MaterialButton capture = findViewById(R.id.capture_progress);
        
        if (capture != null) {
            capture.setOnClickListener(v -> {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA);
                }
            });
        }
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            cameraLauncher.launch(takePictureIntent);
        } catch (Exception e) {
            Toast.makeText(this, "Camera not available: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
