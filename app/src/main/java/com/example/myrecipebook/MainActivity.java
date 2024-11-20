package com.example.myrecipebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myrecipebook.activities.LoginActivity;
import com.example.myrecipebook.activities.WelcomeActivity;
import com.example.myrecipebook.ui.map.MapFragment;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ImageView profileImage;

    @Override
    public void onBackPressed() {
        logout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLogin();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        checkLogin();
    }

    // Dummy method to simulate login check
    private void checkLogin() {
        // If the user is not logged in, navigate to the login screen
        // For now, simulate login check logic.
        // You can replace this with real login logic like checking SharedPreferences, etc.
        if (false) { // Replace with actual login check
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Set up navigation for drawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_categories, R.id.nav_my_recipes, R.id.nav_profile, R.id.nav_uploadRecipe, R.id.nav_map)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Fragment fragment = new MapFragment();

        profileImage = findViewById(R.id.userimage);

        // Update user data (you can add logic here to fetch user data from SharedPreferences or another source)
        updateUserData();

        // Logout button logic
        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    // Dummy logout function that simulates logging out and redirects to WelcomeActivity
    public void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Log out user (clear session data, preferences, etc.)
                // For now, we just go to WelcomeActivity as if the user logged out
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
            }
        });

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Update user data (this can be done from SharedPreferences or some other source)
    private void updateUserData() {
        // Example logic to update profile image (replace with actual user data fetching)
        String imageUrl = "https://example.com/profile-image.jpg"; // Replace with actual URL
        if (profileImage != null && !imageUrl.isEmpty()) {
            // Use Picasso or any other library to load the image
            Picasso.get().load(imageUrl).into(profileImage);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
