package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.TravelExploration;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityTravelAndExplorationBinding;

public class TravelAndExplorationActivity extends AppCompatActivity {

    private ActivityTravelAndExplorationBinding binding;

    private ActivitiRepository repository;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTravelAndExplorationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        int userId = getIntent().getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT);

        LiveData<User> userObserver = repository.getUserByUserId(userId);
        userObserver.observe(this, user -> {
            this.user = user;
            if (user != null) {
                // Display username in top menu
                binding.travelTopMenu.topMenuUserTextView.setText(user.getUsername());

                // Set back button behavior
                binding.travelTopMenu.topMenuBackTextView.setOnClickListener(v -> {
                    Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId());
                    startActivity(intent);
                });

                // Set logout behavior
                binding.travelTopMenu.topMenuUserTextView.setOnClickListener(v -> {
                    showLogoutDialog(TravelAndExplorationActivity.this);
                });

                // View Hiking Routes Button
                binding.viewHikingRoutesButton.setOnClickListener(v -> {
                    Intent intent = HikingRoutesActivity.hikingRoutesIntentFactory(this, user.getId());
                    startActivity(intent);
                });

                binding.viewVisitedPlacesButton.setOnClickListener(v -> {
                   Intent intent = VisitedPlacesActivity.visitedPlacesIntentFactory(this, user.getId());
                   startActivity(intent);
                });
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTravelExploration();
            }
        });
    }

    private void saveTravelExploration() {
        String hikingRoute = binding.hikingRouteEditText.getText().toString();
        String outdoors = binding.outdoorsEditText.getText().toString();
        String visitedPlaces = binding.visitedPlacesEditText.getText().toString();

        if (hikingRoute.isEmpty() || outdoors.isEmpty() || visitedPlaces.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        TravelExploration travelExploration = new TravelExploration(hikingRoute,outdoors,visitedPlaces);
        repository.insertTravelExploration(travelExploration);

        Toast.makeText(this, "Travel Exploration Saved!", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void clearFields() {
        binding.hikingRouteEditText.setText("");
        binding.outdoorsEditText.setText("");
        binding.visitedPlacesEditText.setText("");
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

    static Intent travelAndExplorationActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, TravelAndExplorationActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}
