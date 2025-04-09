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

import com.webcraftsolutions.project02.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // CLASS FIELDS

    // INSTANCE FIELDS

    private ActivityMainBinding binding;

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
                // TODO NASSER Start Wellness Activity
//                Intent intent = Wellness Activity Intent Factory
//                startActivity(intent);
            }
        });

        // Set OnClickListener for mainMenuTravelButton
        binding.mainMenuTravelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO JIAN Start Travel Activity
//                Intent intent = Travel Activity Intent Factory
//                startActivity(intent);

            }
        });
    }

    // STATIC METHODS

    /**
     * Intent factory for MainActivity.
     * @param context The application context.
     * @return The MainActivity Intent.
     */
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