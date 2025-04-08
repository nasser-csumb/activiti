/**
 * Title: Project 02: Activiti - Event Activity
 * Author: Noah deFer
 * Date Created: 4/8/2025
 * Description: The main activity for the Events section of the application.
 *      Will allow users to view, create, edit, and delete events.
 */

package com.webcraftsolutions.project02;

import android.os.Bundle;

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
    }

    // STATIC METHODS

    // GETTERS AND SETTERS
}