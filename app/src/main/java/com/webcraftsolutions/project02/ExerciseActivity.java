package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.databinding.ActivityExerciseBinding;

public class ExerciseActivity extends AppCompatActivity {

    private ActivityExerciseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cardioButton.setOnClickListener(v -> {
            startActivity(CardioActivity.cardioActivityIntentFactory(this));
        });

        binding.weightLiftingButton.setOnClickListener(v -> {
            startActivity(WeightLiftingActivity.weightLiftingActivityIntentFactory(this));
        });

        binding.progressButton.setOnClickListener(v -> {
            startActivity(ProgressActivity.progressActivityIntentFactory(this));
        });
    }

    public static Intent exerciseActivityIntentFactory(Context context) {
        return new Intent(context, ExerciseActivity.class);
    }
}
