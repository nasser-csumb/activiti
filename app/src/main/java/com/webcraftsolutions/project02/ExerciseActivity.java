package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityExerciseBinding;

public class ExerciseActivity extends AppCompatActivity {

    private ActivityExerciseBinding binding;
    private ActivitiRepository repository;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        int userId = getIntent().getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT);
        LiveData<User> userObserver = repository.getUserByUserId(userId);
        userObserver.observe(this, user -> {
            this.user = user;
            if (user != null) {
                binding.exerciseTopMenu.topMenuUserTextView.setText(user.getUsername());

                binding.exerciseTopMenu.topMenuUserTextView.setOnClickListener(v -> showLogoutDialog(ExerciseActivity.this));

                binding.exerciseTopMenu.topMenuBackTextView.setOnClickListener(v -> {
                    Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId());
                    startActivity(intent);
                });

                binding.cardioButton.setOnClickListener(v -> {
                    startActivity(CardioActivity.cardioActivityIntentFactory(this, user.getId()));
                });

                binding.weightLiftingButton.setOnClickListener(v -> {
                    startActivity(WeightLiftingActivity.weightLiftingActivityIntentFactory(this, user.getId()));
                });

                binding.progressButton.setOnClickListener(v -> {
                    startActivity(ProgressActivity.progressActivityIntentFactory(this, user.getId()));
                });
            }
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

    public static Intent exerciseActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, ExerciseActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}
