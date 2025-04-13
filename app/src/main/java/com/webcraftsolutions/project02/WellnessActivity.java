/**
 * Title: Project 02: Activiti - Wellness Activity
 * Author: Nasser Akhter
 * Description: The home page for the wellness activity
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.databinding.ActivityWellnessBinding;

public class WellnessActivity extends AppCompatActivity {

    ActivityWellnessBinding binding;
    ActivitiRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // V <> C binding
        binding = ActivityWellnessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        setupHandlers();
    }

    private void setupHandlers() {
        setupSleepClickEventHandler();
        setupSummaryClickEventHandler();
    }

    private void setupSleepClickEventHandler() {
        binding.sleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = WellnessSleep.wellnessSleepActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    private void setupSummaryClickEventHandler() {
        binding.sleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = WellnessSummary.wellnessSummaryActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    /**
     * Intent factory for WellnessActivity.
     * @param context The application context.
     * @return The WellnessActivity Intent.
     */
    static Intent wellnessActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, WellnessActivity.class);
        return intent;
    }
}