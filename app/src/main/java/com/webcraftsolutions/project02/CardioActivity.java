/**
 * Title: Project 02: Activiti - Cardio Activity
 * Author: Suhaib Peracha
 * Date Created: 4/14/2025
 * Description: Allows the user to log a cardio workout with type, duration, and intensity.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.CardioWorkout;
import com.webcraftsolutions.project02.databinding.ActivityCardioBinding;

import java.util.Date;

public class CardioActivity extends AppCompatActivity {

    private ActivityCardioBinding binding;
    private ActivitiRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        String[] cardioTypes = {"Running", "Cycling", "Walking", "Swimming"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cardioTypes);
        binding.cardioTypeDropdown.setAdapter(typeAdapter);

        String[] intensityLevels = {"Low", "Medium", "High"};
        ArrayAdapter<String> intensityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, intensityLevels);
        binding.intensityDropdown.setAdapter(intensityAdapter);

        binding.saveCardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCardioWorkout();
            }
        });
    }

    private void saveCardioWorkout() {
        String type = binding.cardioTypeDropdown.getSelectedItem().toString();
        String durationStr = binding.durationEditText.getText().toString();
        String intensity = binding.intensityDropdown.getSelectedItem().toString();

        if (!durationStr.isEmpty()) {
            int duration = Integer.parseInt(durationStr);
            int userId = -1; // placeholder
            Date date = new Date();

            CardioWorkout workout = new CardioWorkout(userId, type, duration, intensity, date);
            repository.insertCardioWorkout(workout);
            finish();
        }
    }

    public static Intent cardioActivityIntentFactory(Context context) {
        return new Intent(context, CardioActivity.class);
    }
}
