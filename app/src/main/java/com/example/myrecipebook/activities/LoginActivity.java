package com.example.myrecipebook.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myrecipebook.MainActivity;
import com.example.myrecipebook.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField, passwordField;
    private Button loginButton;

    // Hardcoded test credentials (email and password)
    private final String TEST_EMAIL = "testuser@example.com";
    private final String TEST_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        emailField = findViewById(R.id.login_email);
        passwordField = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);

        // Set up login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                // Validate input
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Call method to handle login
                    loginUser(email, password);
                }
            }
        });
    }

    // Method to handle login with hardcoded credentials
    private void loginUser(String email, String password) {
        if (email.equals(TEST_EMAIL) && password.equals(TEST_PASSWORD)) {
            // If login is successful, store login state in SharedPreferences
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isLoggedIn", true); // Set login state to true
            editor.apply();

            // Show success message and navigate to MainActivity
            Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
            navigateToMainActivity();
        } else {
            // If login fails, show error message
            Toast.makeText(LoginActivity.this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to navigate to MainActivity
    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear back stack
        startActivity(intent);  // Start MainActivity
        finish();  // Close LoginActivity to prevent going back to it
    }
}
