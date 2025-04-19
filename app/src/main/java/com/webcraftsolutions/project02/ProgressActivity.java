/**
 * Title: Project 02: Activiti - Progress Activity
 * Author: Suhaib Peracha
 * Date Created: 4/14/2025
 * Description: Displays user's cardio and weightlifting workout history.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.ActivitiDatabase;
import com.webcraftsolutions.project02.database.entities.CardioWorkout;
import com.webcraftsolutions.project02.database.entities.WeightLiftingWorkout;
import com.webcraftsolutions.project02.databinding.ActivityProgressBinding;

import java.util.ArrayList;
import java.util.Locale;

public class ProgressActivity extends AppCompatActivity {

    private ActivityProgressBinding binding;
    private ActivitiRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProgressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());
        updateDisplay();
    }

    private void updateDisplay() {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> {
            StringBuilder sb = new StringBuilder();

            ArrayList<CardioWorkout> cardio = repository.getAllCardioWorkoutsByUserId(-1);
            if (cardio != null) {
                for (CardioWorkout c : cardio) {
                    sb.append(String.format(Locale.US, "Cardio: %s - %d min - %s\n",
                            c.getType(), c.getDurationMinutes(), c.getIntensity()));
                }
            }

            ArrayList<WeightLiftingWorkout> lifting = repository.getAllWeightLiftingWorkoutsByUserId(-1);
            if (lifting != null) {
                for (WeightLiftingWorkout l : lifting) {
                    sb.append(String.format(Locale.US, "Lifting: %s - %d sets - %d reps - %d min\n",
                            l.getExerciseName(), l.getSets(), l.getTotalReps(), l.getDurationMinutes()));
                }
            }

            runOnUiThread(() -> binding.progressTextView.setText(sb.toString()));
        });
    }

    public static Intent progressActivityIntentFactory(Context context) {
        return new Intent(context, ProgressActivity.class);
    }
}
