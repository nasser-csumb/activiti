/**
 * Title: Project 02: Activiti - Wellness Mood
 * Author: Nasser Akhter
 * Description: The mood activity allows users to record mood.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.databinding.ActivityWellnessMoodBinding;

import java.util.Date;

public class WellnessMood extends AppCompatActivity {

    ActivityWellnessMoodBinding binding;
    ActivitiRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // V <> C binding
        binding = ActivityWellnessMoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        setupSaveHandler();
    }

    private void setupSaveHandler() {
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String moodName = binding.moodName.getText().toString();
                    int energyLevel = binding.energyLevel.getProgress();

                    Date today = DateWithoutTimeConverter.getDateWithoutTime(new Date());

                    var moodObject =
                            new com.webcraftsolutions.project02.database.entities.WellnessMood(
                                    -1, today, moodName, energyLevel
                            );

                    repository.insertMood(moodObject);

                    // From GymLog lab
                    Toast.makeText(WellnessMood.this,
                            R.string.wellnessActivitySaveSuccess, Toast.LENGTH_SHORT).show();

                    // Close activity, from general knowledge
                    WellnessMood.this.finish();
                } catch (Exception e) {
                    Toast.makeText(WellnessMood.this,
                            R.string.wellnessActivitySaveFail, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * Intent factory for WellnessActivity.
     * @param context The application context.
     * @return The WellnessActivity Intent.
     */
    static Intent wellnessMoodActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, WellnessMood.class);
        return intent;
    }
}