/**
 * Title: Project 02: Activiti - Wellness Summary
 * Author: Nasser Akhter
 * Description: The summary activity which allows users to view a summary of their wellness entries
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiDatabase;
import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.databinding.ActivityWellnessSummaryBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class WellnessSummary extends AppCompatActivity {

    ActivityWellnessSummaryBinding binding;
    ActivitiRepository repository;

    ArrayList<WellnessEntry> wellnessEntries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // V <> C binding
        binding = ActivityWellnessSummaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());
        populateCache();
    }

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
    }


    private void getEntries() {
        var sleepEntries = repository.getAllSleep();
        var moodEntries = repository.getAllMoods();
        var journalEntries = repository.getAllJournals();

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

        for (var entry : entries.values().toArray()) {
            Log.d("TESTDEBUGG", entry.toString());
        }
    }

    /**
     * Intent factory for WellnessActivity.
     * @param context The application context.
     * @return The WellnessActivity Intent.
     */
    static Intent wellnessSummaryActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, WellnessSummary.class);
        return intent;
    }
}