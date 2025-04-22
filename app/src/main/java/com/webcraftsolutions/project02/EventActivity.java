/**
 * Title: Project 02: Activiti - Event Activity
 * Author: Noah deFer
 * Date Created: 4/8/2025
 * Description: The main activity for the Events section of the application.
 *      Will allow users to view, create, edit, and delete events.
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webcraftsolutions.project02.viewHolders.EventAdapter;
import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityEventBinding;

public class EventActivity extends AppCompatActivity {

    // CLASS FIELDS

    // INSTANCE FIELDS

    private ActivityEventBinding binding;

    // The repository.
    private ActivitiRepository repository;

    // The logged in user.
    User user;

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

        // Get repository
        repository = ActivitiRepository.getRepository(getApplication());

        // Get User
        assert repository != null;
        LiveData<User> userObserver = repository.getUserByUserId(getIntent()
                        .getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT));
        userObserver.observe(this, user -> {
            this.user = user;
            if (user != null) {
                // Update Text
                binding.eventTopMenu.topMenuUserTextView.setText(String
                        .format("%s", user.getUsername()));

                // Setup Recycler
                setupRecycler();
            }
        });

        // Set OnClickListener for logout button
        binding.eventTopMenu.topMenuUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog(EventActivity.this);
            }
        });

        // Set OnClickListener for back button
        binding.eventTopMenu.topMenuBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity
                        .mainActivityIntentFactory(getApplicationContext(), user.getId());
                startActivity(intent);
            }
        });

        // Set OnClickListener for eventCreateEventButton
        binding.eventCreateEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EventCreateActivity
                        .eventCreateActivityIntentFactory(getApplicationContext(), user.getId());
                startActivity(intent);
            }
        });
    }

    /**
     * Sets up the recycler object.
     */
    private void setupRecycler() {
        // Get Recycler
        RecyclerView recyclerView = binding.eventRecyclerView;
        final EventAdapter adapter = new EventAdapter(new EventAdapter.EventDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Pass Event list to Recycler
        repository.getAllEventsByUserId(user.getId()).observe(this, adapter::submitList);
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
     * Intent factory for EventActivity.
     * @param context The application context.
     * @param userId The id of the logged in user.
     * @return The EventActivity Intent.
     */
    static Intent eventActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }

    // GETTERS AND SETTERS
}