/**
 * Title: Project 02: Activiti - Main Menu Activity
 * Author(s): Noah deFer
 * Date Created: 4/8/2025
 * Description: The main menu for the Activiti application.
 *      Contains buttons that lead to the other sections of the Activiti application.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // CLASS FIELDS

    // The identifier used for Logcat.
    public static final String TAG = "JNNS_Activiti";

    // INSTANCE FIELDS

    private ActivityMainBinding binding;

    // The repository.
    private ActivitiRepository repository;

    // METHODS

    /**
     * Called when this activity is created.
     * Sets an OnClickListener for each button.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get repository
        repository = ActivitiRepository.getRepository(getApplication());

        // Add Default Users
        User admin = new User("admin1", "admin1");
        admin.setId(1);
        admin.setAdmin(true);
        User testUser = new User("testuser1", "testuser1");
        testUser.setId(2);
        repository.insertUser(admin, testUser);

        // Navigate to LoginActivity
        startActivity(LoginActivity.loginActivityIntentFactory(getApplicationContext()));

        // TODO Set OnClickListener for logoutButton

        // Set OnClickListener for mainMenuEventButton
        binding.mainMenuEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EventActivity.eventActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        // Set OnClickListener for mainMenuExerciseButton
        binding.mainMenuExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO SUHAIB Start Exercise Activity
//                Intent intent = Exercise Activity Intent Factory
//                startActivity(intent);

            }
        });

        // Set OnClickListener for mainMenuWellnessButton
        binding.mainMenuWellnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WellnessActivity.wellnessActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        // Set OnClickListener for mainMenuTravelButton
        binding.mainMenuTravelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TravelAndExplorationActivity.travelAndExplorationActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    // STATIC METHODS

    /**
     * Intent factory for MainActivity.
     * @param context The application context.
     * @return The MainActivity Intent.
     */
    // TODO Update method to store userId.
    static Intent mainActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    /**
     * Creates a toast and displays it to the screen.
     * @param message The message to be displayed.
     */
    public static void toastMaker(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // GETTERS & SETTERS
}