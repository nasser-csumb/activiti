/**
 * Title: Project 02: Activiti - Weight Lifting Activity
 * Author: Suhaib Peracha
 * Date Created: 4/14/2025
 * Description: Allows the user to log a weightlifting workout including exercise name, sets, reps, and duration.
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
import com.webcraftsolutions.project02.database.entities.WeightLiftingWorkout;
import com.webcraftsolutions.project02.databinding.ActivityWeightLiftingBinding;

import java.util.Date;

public class WeightLiftingActivity extends AppCompatActivity {

    private ActivityWeightLiftingBinding binding;
    private ActivitiRepository repository;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeightLiftingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        int userId = getIntent().getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT);

        LiveData<User> userObserver = repository.getUserByUserId(userId);
        userObserver.observe(this, user -> {
            this.user = user;
            if (user != null) {
                binding.liftingTopMenu.topMenuUserTextView.setText(user.getUsername());

                binding.liftingTopMenu.topMenuUserTextView.setOnClickListener(v -> showLogoutDialog(WeightLiftingActivity.this));
                binding.liftingTopMenu.topMenuBackTextView.setOnClickListener(v -> {
                    Intent intent = ExerciseActivity.exerciseActivityIntentFactory(getApplicationContext(), user.getId());
                    startActivity(intent);
                });
            }
        });

        binding.saveLiftingButton.setOnClickListener(v -> saveWeightLiftingWorkout());
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
            int userId = (user != null) ? user.getId() : -1;
            Date date = new Date();

            WeightLiftingWorkout workout = new WeightLiftingWorkout(userId, exerciseName, sets, reps, minutes, date);
            repository.insertWeightLiftingWorkout(workout);
            finish();
        }
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

    public static Intent weightLiftingActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, WeightLiftingActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}
