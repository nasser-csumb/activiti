/**
 * Title: Project 02: Activiti - Wellness Summary
 * Author: Nasser Akhter
 * Description: The summary activity which allows users to view a summary of their wellness entries
 */

package com.webcraftsolutions.project02;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiDatabase;
import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityWellnessSummaryBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class WellnessSummary extends AppCompatActivity {

    ActivityWellnessSummaryBinding binding;
    ActivitiRepository repository;

    /**
     * Store an array of wellnessEntries in cache of activity
     */
    ArrayList<WellnessEntry> wellnessEntries = new ArrayList<>();

    User user;

    int currentEntry = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // V <> C binding
        binding = ActivityWellnessSummaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());
        populateCache();

        binding.previousDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentEntry > 0) {
                    currentEntry--;
                    refreshUI();
                }
            }
        });

        binding.nextDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wellnessEntries.size() - 1 > currentEntry) {
                    currentEntry++;
                    refreshUI();
                }
            }
        });

        // Set OnClickListener for back button
        binding.topMenu.topMenuBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WellnessSummary.this.finish();
            }
        });

        // Set OnClickListener for logout button
        binding.topMenu.topMenuUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog(WellnessSummary.this);
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
                populateCache();
            }
        });
    }

    /**
     * Update the UI
     */
    private void refreshUI() {
        // refresh current date view

        WellnessEntry entry = wellnessEntries.get(currentEntry);

        binding.date.setText(String.format("%s/%s/%s",
                entry.date.getMonth() + 1,
                entry.date.getDate(),
                entry.date.getYear() + 1900
        ));

        binding.previousDayBtn.setEnabled(currentEntry > 0);
        binding.nextDayBtn.setEnabled(wellnessEntries.size() - 1 > currentEntry);

        binding.sleepEntries.removeAllViews();
        if (entry.sleepEntries.isEmpty()) {
            TextView tv = new TextView(getApplicationContext());
            tv.setText("No sleep recorded for this day.");
            tv.setTextAlignment(TEXT_ALIGNMENT_CENTER);

            binding.sleepEntries.addView(tv);
        } else {
            for (var sleepEntry : entry.sleepEntries) {
                TextView tv = new TextView(getApplicationContext());
                tv.setText(sleepEntry.toString());

                binding.sleepEntries.addView(tv);
            }
        }

        binding.moodEntries.removeAllViews();
        if (entry.moodEntries.isEmpty()) {
            TextView tv = new TextView(getApplicationContext());
            tv.setText("No mood recorded for this day.");
            tv.setTextAlignment(TEXT_ALIGNMENT_CENTER);

            binding.moodEntries.addView(tv);
        } else {
            for (var sleepEntry : entry.moodEntries) {
                TextView tv = new TextView(getApplicationContext());
                tv.setText(sleepEntry.toString());

                binding.moodEntries.addView(tv);
            }
        }

        binding.journalEntries.removeAllViews();
        if (entry.journalEntries.isEmpty()) {
            TextView tv = new TextView(getApplicationContext());
            tv.setText("No journal entries recorded for this day.");
            tv.setTextAlignment(TEXT_ALIGNMENT_CENTER);

            binding.journalEntries.addView(tv);
        } else {
            for (var sleepEntry : entry.journalEntries) {
                TextView tv = new TextView(getApplicationContext());
                tv.setText(sleepEntry.toString());

                binding.journalEntries.addView(tv);
            }
        }

    }


    /**
     * Function to populate cache from database itself.
     */
    private void populateCache() {
        Future<Object> future = ActivitiDatabase.databaseWriteExecutor.submit(
                new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        getEntries();
                        return null;
                    }
                }
        );

        try {
            future.get();
        } catch (Exception e) {
            Log.e("FUTURE DATABASE", e.getMessage(), e);
        }

        if (wellnessEntries.isEmpty()) {
            currentEntry = -1;
            return;
        }

        currentEntry = wellnessEntries.size() - 1;
        refreshUI();
    }


    /**
     * Convert three entries per day WelnessEntry items.
     */
    private void getEntries() {
        var sleepEntries = repository.getAllSleepByUserId(WellnessSummary.this.user.getId());
        var moodEntries = repository.getAllMoodsByUserId(WellnessSummary.this.user.getId());
        var journalEntries = repository.getAllJournalsByUserId(WellnessSummary.this.user.getId());

        Map<String, WellnessEntry> entries = new HashMap<>();

        for (var sleepEntry : sleepEntries) {
            String dateString = DateWithoutTimeConverter.getDateAsString(sleepEntry.getDate());

            WellnessEntry wellnessEntry;

            if (entries.containsKey(dateString)) {
                wellnessEntry = entries.get(dateString);
                wellnessEntry.sleepEntries.add(sleepEntry);
            } else {
                wellnessEntry = new WellnessEntry(sleepEntry.getDate());
                wellnessEntry.sleepEntries.add(sleepEntry);
                entries.put(dateString, wellnessEntry);
            }
        }

        for (var moodEntry : moodEntries) {
            String dateString = DateWithoutTimeConverter.getDateAsString(moodEntry.getDate());

            WellnessEntry wellnessEntry;

            if (entries.containsKey(dateString)) {
                wellnessEntry = entries.get(dateString);
                wellnessEntry.moodEntries.add(moodEntry);
            } else {
                wellnessEntry = new WellnessEntry(moodEntry.getDate());
                wellnessEntry.moodEntries.add(moodEntry);
                entries.put(dateString, wellnessEntry);
            }
        }

        for (var journalEntry : journalEntries) {
            String dateString = DateWithoutTimeConverter.getDateAsString(journalEntry.getDate());

            WellnessEntry wellnessEntry;

            if (entries.containsKey(dateString)) {
                wellnessEntry = entries.get(dateString);
                wellnessEntry.journalEntries.add(journalEntry);
            } else {
                wellnessEntry = new WellnessEntry(journalEntry.getDate());
                wellnessEntry.journalEntries.add(journalEntry);
                entries.put(dateString, wellnessEntry);
            }
        }

        wellnessEntries.clear();
        for (var entry : entries.values().toArray()) {
            wellnessEntries.add((WellnessEntry) entry);
        }

        wellnessEntries.sort(new Comparator<WellnessEntry>() {
            @Override
            public int compare(WellnessEntry wellnessEntry, WellnessEntry t1) {
                return wellnessEntry.date.compareTo(t1.date);
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
     *
     * @param context The application context.
     * @return The WellnessActivity Intent.
     */
    static Intent wellnessSummaryActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, WellnessSummary.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}