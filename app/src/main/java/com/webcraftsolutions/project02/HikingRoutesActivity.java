package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.webcraftsolutions.project02.databinding.ActivityHikingRoutesBinding;
import com.webcraftsolutions.project02.viewHolders.HikingRoutesAdapter;
import com.webcraftsolutions.project02.viewHolders.HikingRoutesViewModel;

public class HikingRoutesActivity extends AppCompatActivity {

    private ActivityHikingRoutesBinding binding;
    private HikingRoutesViewModel viewModel;
    private HikingRoutesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHikingRoutesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(HikingRoutesViewModel.class);
        adapter = new HikingRoutesAdapter();

        binding.hikingRoutesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.hikingRoutesRecyclerView.setAdapter(adapter);

        viewModel.getHikingRoutes().observe(this, hikingRoutes -> {
            adapter.setHikingRoutes(hikingRoutes);
        });
    }

    public static Intent hikingRoutesIntentFactory(Context context) {
        return new Intent(context, HikingRoutesActivity.class);
    }
}