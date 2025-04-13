/**
 * Title: Project 02: Activiti - Wellness Sleep
 * Author: Nasser Akhter
 * Description: The sleep activity allows users to record sleep.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.databinding.ActivityWellnessSleepBinding;

import java.util.Date;

public class WellnessSleep extends AppCompatActivity {

    ActivityWellnessSleepBinding binding;
    ActivitiRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // V <> C binding
        binding = ActivityWellnessSleepBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ActivitiRepository.getRepository(getApplication());

        setupHandlers();
    }

    private void setupHandlers(){
        setupHoursSliderHandler();
        setupSaveHandler();
    }

    private void setupHoursSliderHandler() {
        binding.hoursSleptSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.hoursSleptLabel.setText(getString(R.string.wellnessActivitySleepHoursValue, i));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void setupSaveHandler() {
        WellnessSleep activity = this;

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int hoursSlept = binding.hoursSleptSlider.getProgress();
                    boolean refreshed = binding.refreshed.isChecked();
                    boolean restless = binding.restless.isChecked();

                    var sleepObject = new com.webcraftsolutions.project02.database.entities.WellnessSleep(-1, hoursSlept, new Date(), refreshed, restless);

                    repository.insertSleep(sleepObject);

                    Toast.makeText(activity, R.string.wellnessActivitySaveSuccess, Toast.LENGTH_SHORT).show();
                    activity.finish();
                } catch (Exception e) {
                    Toast.makeText(activity, R.string.wellnessActivitySaveFail, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * Intent factory for WellnessActivity.
     * @param context The application context.
     * @return The WellnessActivity Intent.
     */
    static Intent wellnessSleepActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, WellnessSleep.class);
        return intent;
    }
}