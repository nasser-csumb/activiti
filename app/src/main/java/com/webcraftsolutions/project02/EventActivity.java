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
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.Event;
import com.webcraftsolutions.project02.databinding.ActivityEventBinding;

import java.util.ArrayList;
import java.util.Locale;

public class EventActivity extends AppCompatActivity {

    // CLASS FIELDS

    // INSTANCE FIELDS

    private ActivityEventBinding binding;

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
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Add scrolling to eventEventsTextView
        binding.eventEventsTextView.setMovementMethod(new ScrollingMovementMethod());

        // Get repository
        repository = ActivitiRepository.getRepository(getApplication());

        // Update display with Event logs.
        // TODO: Fix updateDisplay
//        updateDisplay();

        // Set OnClickListener for eventCreateEventButton
        binding.eventCreateEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EventCreateActivity
                        .eventCreateActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    /**
     * Constructs a String using logged events.
     * Sets text of eventEventsTextView to constructed String.
     */
    private void updateDisplay() {
        ArrayList<Event> allEvents = repository.allEventLogs;
        if(allEvents == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(Event event : allEvents) {
            String str = String.format(Locale.US,
                    "Name: %s%nDesc: %s%nDate: %s%nTime: %s%n-=-=-=-=-=-=-=-=-%n%n",
                    event.getName(),
                    event.getDescription(),
                    event.getDate(),
                    event.getTime());
            sb.append(str);
        }
        binding.eventEventsTextView.setText(sb.toString());
    }

    // STATIC METHODS

    /**
     * Intent factory for EventActivity.
     * @param context The application context.
     * @return The EventActivity Intent.
     */
    static Intent eventActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, EventActivity.class);
        return intent;
    }

    // GETTERS AND SETTERS
}