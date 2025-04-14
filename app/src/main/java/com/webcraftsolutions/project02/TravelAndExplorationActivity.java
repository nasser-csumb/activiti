package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.TravelExploration;
import com.webcraftsolutions.project02.databinding.ActivityTravelAndExplorationBinding;

public class TravelAndExplorationActivity extends AppCompatActivity {

    private ActivityTravelAndExplorationBinding binding;

    private ActivitiRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTravelAndExplorationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

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

    public static Intent travelAndExplorationActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, TravelAndExplorationActivity.class);
        return intent;
    }
}
