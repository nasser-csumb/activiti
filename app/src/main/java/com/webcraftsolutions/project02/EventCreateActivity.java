/**
 * Title: Project 02: Activiti - Create Event Page
 * @author Noah deFer
 * Date Created: 4/9/2025
 * Description: Allows the user to create/edit events and save those changes to the database.
 */
package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.Event;
import com.webcraftsolutions.project02.databinding.ActivityEventCreateBinding;

public class EventCreateActivity extends AppCompatActivity {

    // CLASS FIELDS

    // INSTANCE FIELDS
    private ActivityEventCreateBinding binding;

    // The repository.
    private ActivitiRepository repository;

    // INSTANCE METHODS

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

        // Get binding
        binding = ActivityEventCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get repository
        repository = ActivitiRepository.getRepository(getApplication());

        // Set OnClickListener for eventCreateSaveEventButton
        binding.eventCreateSaveEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.eventCreateNameEditText.getText().toString();
                String description = binding.eventCreateDescEditText.getText().toString();
                String date = binding.eventCreateDateEditText.getText().toString();
                String time = binding.eventCreateTimeEditText.getText().toString();
                Event event = new Event(name, description, date, time, -1);
                repository.insertEvent(event);
            }
        });
    }

    // STATIC METHODS

    /**
     * Intent factory for EventCreateActivity.
     * @param context The application context.
     * @return The EventCreateActivity Intent.
     */
    static Intent eventCreateActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, EventCreateActivity.class);
        return intent;
    }

    // GETTERS AND SETTERS
}