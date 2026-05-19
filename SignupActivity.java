package com.internshipproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.internshipproject.R;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextInputEditText name = findViewById(R.id.full_name);
        TextInputEditText email = findViewById(R.id.email);
        TextInputEditText password = findViewById(R.id.password);
        MaterialButton signupBtn = findViewById(R.id.signup_btn);

        signupBtn.setOnClickListener(v -> {
            if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            else startActivity(new Intent(this, LoginActivity.class));
        });
        findViewById(R.id.login_link).setOnClickListener(v -> finish());
    }
}