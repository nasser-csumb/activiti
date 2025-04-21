package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.webcraftsolutions.project02.database.ActivitiDatabase;
import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.database.entities.VisitedPlaces;
import com.webcraftsolutions.project02.databinding.ActivityVisitedPlacesBinding;
import com.webcraftsolutions.project02.viewHolders.VisitedPlacesAdapter;
import com.webcraftsolutions.project02.viewHolders.VisitedPlacesViewModel;

import java.util.List;
import java.util.concurrent.Executors;

public class VisitedPlacesActivity extends AppCompatActivity {

    private ActivityVisitedPlacesBinding binding;
    private VisitedPlacesViewModel viewModel;
    private ActivitiRepository repository;
    private User user;
    private int userId;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisitedPlacesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        userId = getIntent().getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT);

        repository.getUserByUserId(userId).observe(this, user -> {
            this.user = user;
            if (user != null) {
                binding.visitedTopMenu.topMenuUserTextView.setText(user.getUsername());
            }
        });

        binding.visitedTopMenu.topMenuBackTextView.setOnClickListener(v -> {
            Intent intent = TravelAndExplorationActivity.travelAndExplorationActivityIntentFactory(getApplicationContext(), userId);
            startActivity(intent);
        });

        binding.visitedTopMenu.topMenuUserTextView.setOnClickListener(v -> showLogoutDialog(this));

        final VisitedPlacesAdapter adapter = new VisitedPlacesAdapter(new VisitedPlacesAdapter.VisitedPlacesDiff());
        binding.visitedPlacesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.visitedPlacesRecyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(VisitedPlacesViewModel.class);
        viewModel.getVisitedPlaces(userId).observe(this, visitedPlaces -> {
            adapter.submitList(visitedPlaces);
        });

        binding.addPhotoButton.setOnClickListener(v -> openGallery());

        binding.saveVisitedPlaceButton.setOnClickListener(v -> {
            String name = binding.placeNameEditText.getText().toString().trim();
            String category = binding.placeCategoryEditText.getText().toString().trim();
            String notes = binding.placeNotesEditText.getText().toString().trim();
            String photoUri = selectedImageUri != null ? selectedImageUri.toString() : null;

            if (name.isEmpty() || category.isEmpty()) {
                Toast.makeText(this, "Please fill out required fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            long currentTime = System.currentTimeMillis();
            VisitedPlaces place = new VisitedPlaces(userId, name, category, notes, photoUri, currentTime);
            viewModel.insertVisitedPlace(place);
            Toast.makeText(this, "Visited place saved!", Toast.LENGTH_SHORT).show();


            binding.placeNameEditText.setText("");
            binding.placeCategoryEditText.setText("");
            binding.placeNotesEditText.setText("");
            binding.placePhotoImageView.setImageResource(android.R.color.transparent);
        });
    }

    // I learned this at https://developer.android.com/training/basics/intents/result &
    // https://stackoverflow.com/questions/46810435/how-to-open-gallery-to-pick-an-image/46811735
    // The following was inspired from the links above to implement a way to get the image and see it.
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }

    // I learned this at https://developer.android.com/training/basics/intents/result
    // The following was inspired from the link above to implement a way to get the image and see it.
    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    binding.placePhotoImageView.setImageURI(selectedImageUri);
                }
            }
    );

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

    static Intent visitedPlacesIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, VisitedPlacesActivity.class);
        intent.putExtra(MainActivity.LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }
}
