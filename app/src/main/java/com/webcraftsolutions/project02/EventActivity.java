/**
 * Title: Project 02: Activiti - Event Activity
 * Author: Noah deFer
 * Date Created: 4/8/2025
 * Description: The main activity for the Events section of the application.
 *      Will allow users to view, create, edit, and delete events.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.databinding.ActivityEventBinding;

public class EventActivity extends AppCompatActivity {

    // CLASS FIELDS

    // INSTANCE FIELDS

    private ActivityEventBinding binding;

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
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set OnClickListener for eventCreateEventButton
        binding.eventCreateEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO NOAH Start Create Event activity
//                Intent intent = Create Event Activity Intent Factory
//                startActivity(intent);
            }
        });
    }

    // STATIC METHODS

    /**
     * Intent factory for EventActivity.
     * @param context The application context.
     * @return The EventActivity Intent.
     */
    static Intent mainActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    // GETTERS AND SETTERS
}