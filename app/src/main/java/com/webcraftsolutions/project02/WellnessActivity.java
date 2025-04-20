/**
 * Title: Project 02: Activiti - Wellness Activity
 * Author: Nasser Akhter
 * Description: The home page for the wellness activity
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
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityWellnessBinding;

public class WellnessActivity extends AppCompatActivity {

    ActivityWellnessBinding binding;
    ActivitiRepository repository;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // V <> C binding
        binding = ActivityWellnessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        setupHandlers();

        // Set OnClickListener for back button
        binding.topMenu.topMenuBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginActivity.loginActivityIntentFactory(getApplicationContext()); // Replace with intent factory for desired activity.
                startActivity(intent);
            }
        });

        // Set OnClickListener for logout button
        binding.topMenu.topMenuUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog(WellnessActivity.this);
            } // Replace 'EventActivity.this' with 'ActivityName.this'
        });

        LiveData<User> userObserver = repository.getUserByUserId(getIntent()
                .getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT));
        userObserver.observe(this, user -> {
            this.user = user;
            if (user != null) {
                // Update Text
                binding.topMenu.topMenuUserTextView.setText(String
                        .format("%s", user.getUsername()));
            }
        });
    }

    private void setupHandlers() {
        setupSleepClickEventHandler();
        setupMoodClickEventHandler();
        setupJournalClickEventHandler();
        setupSummaryClickEventHandler();
    }

    private void setupSleepClickEventHandler() {
        binding.sleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = WellnessSleep.wellnessSleepActivityIntentFactory(getApplicationContext(), user.getId());
                startActivity(intent);
            }
        });
    }

    private void setupMoodClickEventHandler() {
        binding.moodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = WellnessMood.wellnessMoodActivityIntentFactory(getApplicationContext(), user.getId());
                startActivity(intent);
            }
        });
    }

    private void setupJournalClickEventHandler() {
        binding.journalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = WellnessJournal.wellnessJournalActivityIntentFactory(getApplicationContext(), user.getId());
                startActivity(intent);
            }
        });
    }


    private void setupSummaryClickEventHandler() {
        binding.summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = WellnessSummary.wellnessSummaryActivityIntentFactory(getApplicationContext(), user.getId());
                startActivity(intent);
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

    /**
     * Intent factory for WellnessActivity.
     * @param context The application context.
     * @return The WellnessActivity Intent.
     */
    static Intent wellnessActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, WellnessActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}