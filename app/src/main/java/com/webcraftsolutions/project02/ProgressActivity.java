/**
 * Title: Project 02: Activiti - Progress Activity
 * Author: Suhaib Peracha
 * Date Created: 4/14/2025
 * Description: Displays user's cardio and weightlifting workout history.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiDatabase;
import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.CardioWorkout;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.database.entities.WeightLiftingWorkout;
import com.webcraftsolutions.project02.databinding.ActivityProgressBinding;

import java.util.ArrayList;
import java.util.Locale;

public class ProgressActivity extends AppCompatActivity {

    private ActivityProgressBinding binding;
    private ActivitiRepository repository;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProgressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        int userId = getIntent().getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT);
        LiveData<User> userObserver = repository.getUserByUserId(userId);
        userObserver.observe(this, user -> {
            this.user = user;
            if (user != null) {
                binding.progressTopMenu.topMenuUserTextView.setText(user.getUsername());

                binding.progressTopMenu.topMenuUserTextView.setOnClickListener(v -> showLogoutDialog(ProgressActivity.this));
                binding.progressTopMenu.topMenuBackTextView.setOnClickListener(v -> {
                    Intent intent = ExerciseActivity.exerciseActivityIntentFactory(getApplicationContext(), user.getId());
                    startActivity(intent);
                });

                updateDisplay(user.getId());
            }
        });
    }

    private void updateDisplay(int userId) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> {
            StringBuilder sb = new StringBuilder();

            ArrayList<CardioWorkout> cardio = repository.getAllCardioWorkoutsByUserId(userId);
            if (cardio != null) {
                for (CardioWorkout c : cardio) {
                    sb.append(String.format(Locale.US, "Cardio: %s - %d min - %s\n",
                            c.getType(), c.getDurationMinutes(), c.getIntensity()));
                }
            }

            ArrayList<WeightLiftingWorkout> lifting = repository.getAllWeightLiftingWorkoutsByUserId(userId);
            if (lifting != null) {
                for (WeightLiftingWorkout l : lifting) {
                    sb.append(String.format(Locale.US, "Lifting: %s - %d sets - %d reps - %d min\n",
                            l.getExerciseName(), l.getSets(), l.getTotalReps(), l.getDurationMinutes()));
                }
            }

            runOnUiThread(() -> binding.progressTextView.setText(sb.toString()));
        });
    }

    private void showLogoutDialog(Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Logout?");
        alertBuilder.setPositiveButton("Logout", (dialog, which) -> {
            startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), true));
        });

        alertBuilder.setNegativeButton("Cancel", (dialog, which) -> alertDialog.dismiss());
        alertBuilder.create().show();
    }

    public static Intent progressActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, ProgressActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}

