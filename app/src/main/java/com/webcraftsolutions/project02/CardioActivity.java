/**
 * Title: Project 02: Activiti - Cardio Activity
 * Author: Suhaib Peracha
 * Date Created: 4/14/2025
 * Description: Allows the user to log a cardio workout with type, duration, and intensity.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.CardioWorkout;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityCardioBinding;

import java.util.Date;

public class CardioActivity extends AppCompatActivity {

    private ActivityCardioBinding binding;
    private ActivitiRepository repository;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        int userId = getIntent().getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT);
        LiveData<User> userObserver = repository.getUserByUserId(userId);
        userObserver.observe(this, user -> {
            this.user = user;
            if (user != null) {
                binding.cardioTopMenu.topMenuUserTextView.setText(user.getUsername());

                binding.cardioTopMenu.topMenuUserTextView.setOnClickListener(v -> showLogoutDialog(CardioActivity.this));
                binding.cardioTopMenu.topMenuBackTextView.setOnClickListener(v -> {
                    Intent intent = ExerciseActivity.exerciseActivityIntentFactory(getApplicationContext(), user.getId());
                    startActivity(intent);
                });
            }
        });

        String[] cardioTypes = {"Running", "Cycling", "Walking", "Swimming"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cardioTypes);
        binding.cardioTypeDropdown.setAdapter(typeAdapter);

        String[] intensityLevels = {"Low", "Medium", "High"};
        ArrayAdapter<String> intensityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, intensityLevels);
        binding.intensityDropdown.setAdapter(intensityAdapter);

        binding.saveCardioButton.setOnClickListener(v -> saveCardioWorkout());
    }

    private void saveCardioWorkout() {
        String type = binding.cardioTypeDropdown.getSelectedItem().toString();
        String durationStr = binding.durationEditText.getText().toString();
        String intensity = binding.intensityDropdown.getSelectedItem().toString();

        if (!durationStr.isEmpty()) {
            int duration = Integer.parseInt(durationStr);
            int userId = (user != null) ? user.getId() : -1;
            Date date = new Date();

            CardioWorkout workout = new CardioWorkout(userId, type, duration, intensity, date);
            repository.insertCardioWorkout(workout);
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

    public static Intent cardioActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, CardioActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}
