package com.internshipproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.internshipproject.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText email = findViewById(R.id.email);
        TextInputEditText password = findViewById(R.id.password);
        MaterialButton loginBtn = findViewById(R.id.login_btn);
        MaterialButton googleBtn = findViewById(R.id.google_btn);

        loginBtn.setOnClickListener(v -> {
            if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            else startActivity(new Intent(this, MainActivity.class));
        });
        googleBtn.setOnClickListener(v ->
                Toast.makeText(this, "Google Sign-In (demo)", Toast.LENGTH_SHORT).show());
        findViewById(R.id.signup_link).setOnClickListener(v ->
                startActivity(new Intent(this, SignupActivity.class)));
    }
}