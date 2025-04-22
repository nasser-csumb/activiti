/**
 * Title: CST-338 Project 02: Activiti - ExerciseActivity
 * Author: Suhaib Peracha
 * Date Created: 4/8/2025
 * Description: Main activity for exercise section, includes navigation and quote display.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.api.ExerciseQuoteApiService;
import com.webcraftsolutions.project02.api.QuoteResponse;
import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityExerciseBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://zenquotes.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExerciseQuoteApiService service = retrofit.create(ExerciseQuoteApiService.class);
        service.getRandomQuote().enqueue(new Callback<List<QuoteResponse>>() {
            @Override
            public void onResponse(Call<List<QuoteResponse>> call, Response<List<QuoteResponse>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    QuoteResponse quote = response.body().get(0);
                    binding.quoteTextView.setText("\"" + quote.getQuote() + "\"\n- " + quote.getAuthor());
                } else {
                    binding.quoteTextView.setText("Quote not available.");
                }
            }

            @Override
            public void onFailure(Call<List<QuoteResponse>> call, Throwable t) {
                binding.quoteTextView.setText("Failed to load quote.");
            }
        });

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
