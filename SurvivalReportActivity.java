package com.internshipproject;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.internshipproject.R;

public class SurvivalReportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survival_report);
        MaterialButton pdf = findViewById(R.id.btn_pdf);
        MaterialButton csv = findViewById(R.id.btn_csv);
        pdf.setOnClickListener(v -> Toast.makeText(this, "PDF generated (demo)", Toast.LENGTH_SHORT).show());
        csv.setOnClickListener(v -> Toast.makeText(this, "CSV exported (demo)", Toast.LENGTH_SHORT).show());
    }
}