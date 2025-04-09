package com.webcraftsolutions.project02;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.databinding.ActivityEventCreateBinding;

public class EventCreateActivity extends AppCompatActivity {

    private ActivityEventCreateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get binding
        binding = ActivityEventCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}