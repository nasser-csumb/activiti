/**
 * Title: Project 02: Activiti - Wellness Summary
 * Author: Nasser Akhter
 * Description: The summary activity which allows users to view a summary of their wellness entries
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.databinding.ActivityWellnessSummaryBinding;

public class WellnessSummary extends AppCompatActivity {

    ActivityWellnessSummaryBinding binding;
    ActivitiRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // V <> C binding
        binding = ActivityWellnessSummaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());
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