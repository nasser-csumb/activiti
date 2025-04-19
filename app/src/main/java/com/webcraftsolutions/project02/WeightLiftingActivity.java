/**
 * Title: Project 02: Activiti - Weight Lifting Activity
 * Author: Suhaib Peracha
 * Date Created: 4/14/2025
 * Description: Allows the user to log a weightlifting workout including exercise name, sets, reps, and duration.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.WeightLiftingWorkout;
import com.webcraftsolutions.project02.databinding.ActivityWeightLiftingBinding;

import java.util.Date;

public class WeightLiftingActivity extends AppCompatActivity {

    private ActivityWeightLiftingBinding binding;
    private ActivitiRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeightLiftingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        binding.saveLiftingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWeightLiftingWorkout();
            }
        });
    }

    private void saveWeightLiftingWorkout() {
        String exerciseName = binding.exerciseNameEditText.getText().toString();
        String setsStr = binding.setsEditText.getText().toString();
        String repsStr = binding.repsEditText.getText().toString();
        String minutesStr = binding.durationEditText.getText().toString();

        if (!exerciseName.isEmpty() && !setsStr.isEmpty() && !repsStr.isEmpty() && !minutesStr.isEmpty()) {
            int sets = Integer.parseInt(setsStr);
            int reps = Integer.parseInt(repsStr);
            int minutes = Integer.parseInt(minutesStr);
            int userId = -1; // placeholder
            Date date = new Date();

            WeightLiftingWorkout workout = new WeightLiftingWorkout(userId, exerciseName, sets, reps, minutes, date);
            repository.insertWeightLiftingWorkout(workout);
            finish();
        }
    }

    public static Intent weightLiftingActivityIntentFactory(Context context) {
        return new Intent(context, WeightLiftingActivity.class);
    }
}
