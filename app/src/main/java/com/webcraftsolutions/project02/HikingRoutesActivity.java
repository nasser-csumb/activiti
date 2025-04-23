package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.HikingRoutes;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityHikingRoutesBinding;
import com.webcraftsolutions.project02.viewHolders.HikingRoutesAdapter;
import com.webcraftsolutions.project02.viewHolders.HikingRoutesViewModel;

public class HikingRoutesActivity extends AppCompatActivity {

    private ActivityHikingRoutesBinding binding;
    private HikingRoutesViewModel viewModel;

    private ActivitiRepository repository;
    private User user;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHikingRoutesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        userId = getIntent().getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT);

        repository.getUserByUserId(userId).observe(this, user -> {
            this.user = user;
            if (user != null) {
                binding.hikingTopMenu.topMenuUserTextView.setText(user.getUsername());
            }
        });

        binding.hikingTopMenu.topMenuBackTextView.setOnClickListener(v -> {
            Intent intent = TravelAndExplorationActivity.travelAndExplorationActivityIntentFactory(getApplicationContext(), userId);
            startActivity(intent);
        });

        binding.hikingTopMenu.topMenuUserTextView.setOnClickListener(v -> showLogoutDialog(this));

        final HikingRoutesAdapter adapter = new HikingRoutesAdapter(new HikingRoutesAdapter.HikingRoutesDiff());
        binding.hikingRoutesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.hikingRoutesRecyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(HikingRoutesViewModel.class);
        viewModel.getHikingRoutes(userId).observe(this, hikingRoutes -> {
            adapter.submitList(hikingRoutes);
        });

        binding.addHikingRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHikingRoute();
            }
        });
    }

    private void addHikingRoute() {
        String name = binding.routeNameEditText.getText().toString().trim();
        String place = binding.routePlaceEditText.getText().toString().trim();
        String difficultyStr = binding.routeDifficultyEditText.getText().toString().trim();
        String time = binding.routeTimeEstimateEditText.getText().toString().trim();
        String dangerLevel = binding.routeDangerLevelEditText.getText().toString().trim();
        String safetyTips = binding.routeSafetyTipsEditText.getText().toString().trim();
        String ratingStr = binding.routeRatingEditText.getText().toString().trim();

        if (name.isEmpty() || place.isEmpty() || difficultyStr.isEmpty() || time.isEmpty() || ratingStr.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        double rating;
        try {
            rating = Double.parseDouble(ratingStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Rating must be a number.", Toast.LENGTH_SHORT).show();
            return;
        }

        int difficulty = 0;
        if (!difficultyStr.isEmpty()) {
            try {
                difficulty = Integer.parseInt(difficultyStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid difficulty value", Toast.LENGTH_SHORT).show();
            }
        }

        userId = getIntent().getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT);

        HikingRoutes newRoute = new HikingRoutes(rating, safetyTips, dangerLevel, time, difficulty, place, name, userId);

        viewModel.insertHikingRoute(newRoute);
        Toast.makeText(this, "Hiking route added!", Toast.LENGTH_SHORT).show();

        binding.routeNameEditText.setText("");
        binding.routePlaceEditText.setText("");
        binding.routeDifficultyEditText.setText("");
        binding.routeTimeEstimateEditText.setText("");
        binding.routeDangerLevelEditText.setText("");
        binding.routeSafetyTipsEditText.setText("");
        binding.routeRatingEditText.setText("");
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

    static Intent hikingRoutesIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, HikingRoutesActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}