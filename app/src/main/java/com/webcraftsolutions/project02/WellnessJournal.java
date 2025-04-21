/**
 * Title: Project 02: Activiti - Wellness Journal
 * Author: Nasser Akhter
 * Description: The Journal activity allows users to record Journal entries.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityWellnessJournalBinding;

import java.util.Date;

public class WellnessJournal extends AppCompatActivity {

    ActivityWellnessJournalBinding binding;
    ActivitiRepository repository;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // V <> C binding
        binding = ActivityWellnessJournalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        setupSaveHandler();

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
                showLogoutDialog(WellnessJournal.this);
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

    private void setupSaveHandler() {
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String content = binding.journalContent.getText().toString();
                    String title = binding.journalTitle.getText().toString();

                    Date today = DateWithoutTimeConverter.getDateWithoutTime(new Date());

                    var journalObject =
                            new com.webcraftsolutions.project02.database.entities.WellnessJournal(
                                    user.getId(), today, title, content
                            );

                    repository.insertJournal(journalObject);

                    // From GymLog lab
                    Toast.makeText(WellnessJournal.this,
                            R.string.wellnessActivitySaveSuccess, Toast.LENGTH_SHORT).show();

                    // Close activity, from general knowledge
                    WellnessJournal.this.finish();
                } catch (Exception e) {
                    Toast.makeText(WellnessJournal.this,
                            R.string.wellnessActivitySaveFail, Toast.LENGTH_SHORT).show();
                }
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
    static Intent wellnessJournalActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, WellnessJournal.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}