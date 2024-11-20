package com.example.myrecipebook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.myrecipebook.MainActivity;
import com.example.myrecipebook.R;
import com.example.myrecipebook.models.RestApiThread;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        // If the user is logged in, skip the WelcomeActivity and go to MainActivity
        if (user != null) {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish(); // Close WelcomeActivity
            return; // Exit onCreate early if the user is logged in
        }

        // Set the window to fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome);

        // Start background API call (if needed)
        RestApiThread restApiThread = new RestApiThread();
        restApiThread.start();
    }

    // Navigate to the Register Activity when "Register" button is clicked
    public void register(View view) {
        startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class));
    }

    // Navigate to the Login Activity when "Login" button is clicked
    public void login(View view) {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
    }
}
