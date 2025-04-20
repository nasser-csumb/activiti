package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityHikingRoutesBinding;
import com.webcraftsolutions.project02.viewHolders.HikingRoutesAdapter;
import com.webcraftsolutions.project02.viewHolders.HikingRoutesViewModel;

public class HikingRoutesActivity extends AppCompatActivity {

    private ActivityHikingRoutesBinding binding;
    private HikingRoutesViewModel viewModel;
    private HikingRoutesAdapter adapter;

    private ActivitiRepository repository;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHikingRoutesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up repository
        repository = ActivitiRepository.getRepository(getApplication());

        // Get User from intent
        int userId = getIntent().getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT);
        repository.getUserByUserId(userId).observe(this, user -> {
            this.user = user;
            if (user != null) {
                // Set top menu username
                binding.hikingTopMenu.topMenuUserTextView.setText(user.getUsername());
            }
        });

        // Back button
        binding.hikingTopMenu.topMenuBackTextView.setOnClickListener(v -> {
            Intent intent = TravelAndExplorationActivity.travelAndExplorationActivityIntentFactory(getApplicationContext(), user.getId());
            startActivity(intent);
        });

        // Logout prompt (optional)
        binding.hikingTopMenu.topMenuUserTextView.setOnClickListener(v -> showLogoutDialog(this));

        // Set up RecyclerView and ViewModel
        adapter = new HikingRoutesAdapter();
        binding.hikingRoutesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.hikingRoutesRecyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(HikingRoutesViewModel.class);
        viewModel.getHikingRoutes().observe(this, adapter::setHikingRoutes);
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

    public static Intent hikingRoutesIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, HikingRoutesActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}