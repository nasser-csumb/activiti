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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.webcraftsolutions.project02.adapters.CardioAdapter;
import com.webcraftsolutions.project02.adapters.LiftingAdapter;
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

    private CardioAdapter cardioAdapter;
    private LiftingAdapter liftingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProgressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        cardioAdapter = new CardioAdapter(workout -> {
            ActivitiDatabase.databaseWriteExecutor.execute(() -> {
                repository.deleteCardioWorkout(workout);
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                runOnUiThread(() -> updateDisplay(user.getId()));
            });
        });

        liftingAdapter = new LiftingAdapter(workout -> {
            ActivitiDatabase.databaseWriteExecutor.execute(() -> {
                repository.deleteWeightLiftingWorkout(workout);
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                runOnUiThread(() -> updateDisplay(user.getId()));
            });
        });

        binding.cardioRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.liftingRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.cardioRecyclerView.setAdapter(cardioAdapter);
        binding.liftingRecyclerView.setAdapter(liftingAdapter);

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
            ArrayList<CardioWorkout> cardio = repository.getAllCardioWorkoutsByUserId(userId);
            ArrayList<WeightLiftingWorkout> lifting = repository.getAllWeightLiftingWorkoutsByUserId(userId);

            int totalCalories = 0;
            for (CardioWorkout c : cardio) {
                int multiplier;
                String intensity = c.getIntensity().toLowerCase();
                if (intensity.equals("high")) {
                    multiplier = 10;
                } else if (intensity.equals("medium")) {
                    multiplier = 8;
                } else {
                    multiplier = 6;
                }
                totalCalories += c.getDurationMinutes() * multiplier;
            }
            for (WeightLiftingWorkout l : lifting) {
                totalCalories += l.getDurationMinutes() * 6;
            }

            final int totalCaloriesFinal = totalCalories;

            runOnUiThread(() -> {
                cardioAdapter.setData(cardio);
                liftingAdapter.setData(lifting);
                binding.totalCardioTextView.setText("Total cardio sessions: " + cardio.size());
                binding.totalLiftingTextView.setText("Total lifting sessions: " + lifting.size());
                binding.estimatedCaloriesTextView.setText("Estimated total calories burned: " + totalCaloriesFinal);
            });
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


