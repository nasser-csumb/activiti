/**
 * Title: Project 02: Activiti - Create Event Page
 * @author Noah deFer
 * Date Created: 4/9/2025
 * Description: Allows the user to create/edit events and save those changes to the database.
 */
package com.webcraftsolutions.project02;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.databinding.ActivityEventCreateBinding;

public class EventCreateActivity extends AppCompatActivity {

    // CLASS FIELDS

    // INSTANCE FIELDS
    private ActivityEventCreateBinding binding;

    // INSTANCE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get binding
        binding = ActivityEventCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    // STATIC METHODS

    // GETTERS AND SETTERS
}