/**
 * Title: Project 02: Activiti - Travel & Exploration
 * File: TravelAndExplorationActivity.java - Implementation of TravelAndExplorationActivity
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 23 April 2025
 * Explanation/Abstract: The travel and exploration activity will be able to save the
 * users input into a saved card file on their personal account.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityTravelAndExplorationBinding;
import com.webcraftsolutions.project02.viewHolders.HikingRoutesAdapter;
import com.webcraftsolutions.project02.viewHolders.OutdoorsAdapter;
import com.webcraftsolutions.project02.viewHolders.VisitedPlacesAdapter;

import java.util.Collections;

public class TravelAndExplorationActivity extends AppCompatActivity {

    private ActivityTravelAndExplorationBinding binding;
    private ActivitiRepository repository;
    private int userId;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTravelAndExplorationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());
        userId = getIntent().getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT);

        repository.getUserByUserId(userId).observe(this, user -> {
            this.user = user;
            if (user != null) {
                binding.travelTopMenu.topMenuUserTextView.setText(user.getUsername());
            }
        });

        binding.travelTopMenu.topMenuBackTextView.setOnClickListener(v -> {
            startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), false));
        });

        binding.travelTopMenu.topMenuUserTextView.setOnClickListener(v -> showLogoutDialog(this));

        binding.viewHikingRoutesButton.setOnClickListener(v -> {
            Intent intent = HikingRoutesActivity.hikingRoutesIntentFactory(this, userId);
            startActivity(intent);
        });

        binding.viewVisitedPlacesButton.setOnClickListener(v -> {
            Intent intent = VisitedPlacesActivity.visitedPlacesIntentFactory(this, userId);
            startActivity(intent);
        });

        binding.viewOutdoorsButton.setOnClickListener(v -> {
            Intent intent = OutdoorsActivity.outdoorsIntentFactory(this, userId);
            startActivity(intent);
        });

        // I learned this from the following https://www.geeksforgeeks.org/how-to-implement-textwatcher-in-android/
        // https://www.geeksforgeeks.org/android-recyclerview/ , https://developer.android.com/develop/ui/views/layout/recyclerview
        // The following was inspired from the links above
        EditText searchVisitedPlaceEditText = findViewById(R.id.searchVisitedPlaceEditText);
        RecyclerView resultRecyclerView = findViewById(R.id.visitedPlaceResultRecyclerView);

        EditText hikingSearchEditText = findViewById(R.id.searchHikingRouteEditText);
        RecyclerView hikingResultRecyclerView = findViewById(R.id.hikingRouteResultRecyclerView);

        EditText searchOutdoorsEditText = findViewById(R.id.searchOutdoorEditText);
        RecyclerView outdoorResultRecyclerView = findViewById(R.id.outdoorsRecyclerView);

        VisitedPlacesAdapter visitedPlacesAdapter = new VisitedPlacesAdapter(new VisitedPlacesAdapter.VisitedPlacesDiff());
        resultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        resultRecyclerView.setAdapter(visitedPlacesAdapter);

        HikingRoutesAdapter hikingRoutesAdapter = new HikingRoutesAdapter(new HikingRoutesAdapter.HikingRoutesDiff());
        hikingResultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hikingResultRecyclerView.setAdapter(hikingRoutesAdapter);

        OutdoorsAdapter outdoorsAdapter = new OutdoorsAdapter(new OutdoorsAdapter.OutdoorsDiff());
        outdoorResultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        outdoorResultRecyclerView.setAdapter(outdoorsAdapter);

        // I learned this from the following https://www.geeksforgeeks.org/how-to-implement-textwatcher-in-android/
        // https://www.geeksforgeeks.org/android-recyclerview/ , https://developer.android.com/develop/ui/views/layout/recyclerview
        // The following was inspired from the links above
        searchVisitedPlaceEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hikingSearchEditText.setEnabled(s.length() == 0);
                searchOutdoorsEditText.setEnabled(s.length() == 0);

                String query = s.toString().trim();
                if (!query.isEmpty()) {
                    repository.getVisitedPlaceByName(query).observe(TravelAndExplorationActivity.this, place -> {
                        if (place != null) {
                            visitedPlacesAdapter.submitList(Collections.singletonList(place));
                        } else {
                            visitedPlacesAdapter.submitList(Collections.emptyList());
                        }
                    });
                } else {
                    visitedPlacesAdapter.submitList(Collections.emptyList());
                }
            }
        });

        // I learned this from the following https://www.geeksforgeeks.org/how-to-implement-textwatcher-in-android/
        // https://www.geeksforgeeks.org/android-recyclerview/ , https://developer.android.com/develop/ui/views/layout/recyclerview
        // The following was inspired from the links above
        hikingSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchVisitedPlaceEditText.setEnabled(s.length() == 0);
                searchOutdoorsEditText.setEnabled(s.length() == 0);

                String query = s.toString().trim();
                if (!query.isEmpty()) {
                    repository.getHikingRouteByName(query).observe(TravelAndExplorationActivity.this, route -> {
                        if (route != null) {
                            hikingRoutesAdapter.submitList(Collections.singletonList(route));
                        } else {
                            hikingRoutesAdapter.submitList(Collections.emptyList());
                        }
                    });
                } else {
                    hikingRoutesAdapter.submitList(Collections.emptyList());
                }
            }
        });

        // I learned this from the following https://www.geeksforgeeks.org/how-to-implement-textwatcher-in-android/
        // https://www.geeksforgeeks.org/android-recyclerview/ , https://developer.android.com/develop/ui/views/layout/recyclerview
        // The following was inspired from the links above
        searchOutdoorsEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hikingSearchEditText.setEnabled(s.length() == 0);
                searchVisitedPlaceEditText.setEnabled(s.length() == 0);

                String query = s.toString().trim();
                if (!query.isEmpty()) {
                    repository.getOutdoorByName(query).observe(TravelAndExplorationActivity.this, place -> {
                        if (place != null) {
                            outdoorsAdapter.submitList(Collections.singletonList(place));
                        } else {
                            outdoorsAdapter.submitList(Collections.emptyList());
                        }
                    });
                } else {
                    outdoorsAdapter.submitList(Collections.emptyList());
                }
            }
        });
    }

    private void showLogoutDialog(Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Logout?");
        alertBuilder.setPositiveButton("Logout", (dialog, which) ->
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), true))
        );

        alertBuilder.setNegativeButton("Cancel", (dialog, which) -> alertDialog.dismiss());
        alertBuilder.create().show();
    }

    static Intent travelAndExplorationActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, TravelAndExplorationActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}
