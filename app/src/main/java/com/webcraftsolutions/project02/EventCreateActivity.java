/**
 * Title: Project 02: Activiti - Create Event Page
 * @author Noah deFer
 * Date Created: 4/9/2025
 * Description: Allows the user to create/edit events and save those changes to the database.
 */
package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.Event;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityEventCreateBinding;

public class EventCreateActivity extends AppCompatActivity {

    // CLASS FIELDS

    private static final String EVENT_EXTRA = "com.webcraftsolutions.project02.EVENT_EXTRA";

    // INSTANCE FIELDS
    private ActivityEventCreateBinding binding;

    // The Event to be saved
    private Event event;

    // The repository.
    private ActivitiRepository repository;

    // The user.
    User user;

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

        // Get user.
        assert repository != null;
        LiveData<User> userObserver = repository.getUserByUserId(getIntent()
                .getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT));
        userObserver.observe(this, user -> {
            this.user = user;
            if (user != null) {
                // Update Text
                binding.eventCreateTopMenu.topMenuUserTextView.setText(String
                        .format("%s", user.getUsername()));
            }
        });

        // Get Intent Extra
        int extra = getIntent().getIntExtra(EVENT_EXTRA, -1);
        if(extra != -1) {
            repository.getEventByEventId(extra).observe(this, event -> {
                // Save event
                this.event = event;

                // Set TextView text
                binding.eventCreateNameEditText.setText(event.getName());
                binding.eventCreateDescEditText.setText(event.getDescription());
                binding.eventCreateDateEditText.setText(event.getDate());
                binding.eventCreateTimeEditText.setText(event.getTime());
                binding.eventCreateLocationEditText.setText(event.getLocation());
            });
        }

        // Set OnClickListener for logout button
        binding.eventCreateTopMenu.topMenuUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog(EventCreateActivity.this);
            }
        });

        // Set OnClickListener for back button
        binding.eventCreateTopMenu.topMenuBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EventActivity
                        .eventActivityIntentFactory(getApplicationContext(), user.getId());
                startActivity(intent);
            }
        });

        // Set OnClickListener for eventCreateSaveEventButton
        binding.eventCreateSaveEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get EditText text
                String name = binding.eventCreateNameEditText.getText().toString().trim();
                String description = binding.eventCreateDescEditText.getText().toString().trim();
                String date = binding.eventCreateDateEditText.getText().toString().trim();
                String time = binding.eventCreateTimeEditText.getText().toString().trim();
                String location = binding.eventCreateLocationEditText.getText().toString().trim();

                // Create new event or edit existing event
                if(event == null) {
                    event = new Event(name, description, date, time, location, user.getId());
                } else {
                    event.setName(name);
                    event.setDescription(description);
                    event.setDate(date);
                    event.setTime(time);
                    event.setLocation(location);
                }

                // Store event and display toast to user
                repository.insertEvent(event);
                MainActivity.toastMaker(getApplicationContext(), name + " saved!");
            }
        });
    }

    /**
     * Called when logoutMenuItem is clicked.
     * Displays an alert message to the user.
     * User clicks 'Logout': logout() is called.
     * User clicks 'Cancel': alert message is dismissed.
     */
    private void showLogoutDialog(Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Logout?");

        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(MainActivity
                        .mainActivityIntentFactory(getApplicationContext(), true));

            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();
    }

    // STATIC METHODS

    /**
     * Intent factory for EventCreateActivity.
     * @param context The application context.
     * @return The EventCreateActivity Intent.
     */
    public static Intent eventCreateActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, EventCreateActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }

    public static Intent eventCreateActivityIntentFactory(Context context, int userId, int eventId) {
        Intent intent = eventCreateActivityIntentFactory(context, userId);
        intent.putExtra(EventCreateActivity.EVENT_EXTRA, eventId);
        return intent;
    }

    // GETTERS AND SETTERS
}