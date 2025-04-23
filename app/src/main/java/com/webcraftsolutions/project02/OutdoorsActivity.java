package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.Outdoors;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityOutdoorsBinding;
import com.webcraftsolutions.project02.viewHolders.OutdoorsAdapter;
import com.webcraftsolutions.project02.viewHolders.OutdoorsViewModel;

public class OutdoorsActivity extends AppCompatActivity {

    private ActivityOutdoorsBinding binding;
    private OutdoorsViewModel viewModel;

    private ActivitiRepository repository;
    private User user;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutdoorsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        userId = getIntent().getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT);

        repository.getUserByUserId(userId).observe(this, user -> {
            this.user = user;
            if (user != null) {
                binding.outdoorsTopMenu.topMenuUserTextView.setText(user.getUsername());
            }
        });

        binding.outdoorsTopMenu.topMenuBackTextView.setOnClickListener(v -> {
            Intent intent = TravelAndExplorationActivity.travelAndExplorationActivityIntentFactory(getApplicationContext(), userId);
            startActivity(intent);
        });

        binding.outdoorsTopMenu.topMenuUserTextView.setOnClickListener(v -> showLogoutDialog(this));

        final OutdoorsAdapter adapter = new OutdoorsAdapter(new OutdoorsAdapter.OutdoorsDiff());
        binding.outdoorsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.outdoorsRecyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(OutdoorsViewModel.class);
        viewModel.getOutdoors(userId).observe(this, outdoors -> {
            adapter.submitList(outdoors);
        });

        binding.addOutdoorsButton.setOnClickListener(v -> addOutdoorActivity());
    }

    private void addOutdoorActivity() {
        String name = binding.outdoorsNameEditText.getText().toString().trim();
        String location = binding.outdoorsLocationEditText.getText().toString().trim();
        String activityType = binding.outdoorsTypeEditText.getText().toString().trim();
        String durationStr = binding.outdoorsDurationEditText.getText().toString().trim();
        String notes = binding.outdoorsNotesEditText.getText().toString().trim();
        String ratingStr = binding.outdoorsRatingEditText.getText().toString().trim();

        if (name.isEmpty() || location.isEmpty() || activityType.isEmpty() || durationStr.isEmpty() || ratingStr.isEmpty()) {
            Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double rating;
        try {
            rating = Double.parseDouble(ratingStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Rating must be a number.", Toast.LENGTH_SHORT).show();
            return;
        }

        int duration = 0;
        if (!durationStr.isEmpty()) {
            try {
                duration = Integer.parseInt(durationStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid difficulty value", Toast.LENGTH_SHORT).show();
            }
        }

        Outdoors outdoors = new Outdoors(name, location, activityType, duration, notes, rating, userId);
        viewModel.insertOutdoor(outdoors);
        Toast.makeText(this, "Outdoor activity added!", Toast.LENGTH_SHORT).show();

        binding.outdoorNameEditText.setText("");
        binding.outdoorPlaceEditText.setText("");
        binding.outdoorDurationEditText.setText("");
        binding.outdoorDangerLevelEditText.setText("");
        binding.outdoorSafetyTipsEditText.setText("");
        binding.outdoorRatingEditText.setText("");
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

    static Intent outdoorsIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, OutdoorsActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}