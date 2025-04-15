/**
 * Title: CST-338 Project 02: Activiti - Login Screen
 * @author Noah deFer
 * Date Created: 4/15/2025
 * Description: The login screen for Activiti.
 */
package com.webcraftsolutions.project02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    // INSTANCE FIELDS

    // The binding object for LoginActivity
    private ActivityLoginBinding binding;

    // The repository
    private ActivitiRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get repository
        repository = ActivitiRepository.getRepository(getApplication());

        // Set OnClickListener for Login Button
        binding.loginLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        /*
        Set OnLongClickListener for Login Button
        Logs in admin without having to type login credentials, FOR DEBUGGING.
        */
        binding.loginLoginButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
                startActivity(intent);
                return false;
            }
        });

        // Set OnClickListener for SignUp Button
        binding.loginSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}