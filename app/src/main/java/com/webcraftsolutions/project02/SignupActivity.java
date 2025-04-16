/**
 * Title: CST-338 Project 02: Activiti - Signup Page
 * @author Noah deFer
 * Date Created: 4/16/2025
 * Description: An activity to allow the user to signup for Activiti.
 *      New user credentials will be added to the User table.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    // CLASS FIELDS

    // INSTANCE FIELDS

    // The binding object.
    ActivitySignupBinding binding;

    // The repository object.
    ActivitiRepository repository;

    // INSTANCE METHODS

    /**
     * Called when the activity is created.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get binding
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get repository
        repository = ActivitiRepository.getRepository(getApplication());

        // Disable logout button
        binding.signupTopMenu.topMenuUserTextView.setText("");

        // Set OnClickListener for back button
        binding.signupTopMenu.topMenuBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginActivity.loginActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        // Set OnClickListener for sign up button
        binding.signupSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }

    private void signup() {
        // Get entered username
        String username = binding.signupUsernameEditText.getText().toString();

        // Check if username is empty
        if(username.isEmpty()) {
            MainActivity.toastMaker(this, "Username cannot be empty");
            return;
        }

        // Attempt to get user from repository
        LiveData<User> userObserver = repository.getUserByUsername(username);
        userObserver.observe(this, user -> {
            // Check if user exists
            if(user == null) {
                String password = binding.signupPasswordEditText.getText().toString();
                if(password.isEmpty()) {
                    MainActivity.toastMaker(this, "Password cannot be empty");
                } else {
                    // Add new user to database
                    User newUser = new User(username, password);
                    repository.insertUser(newUser);

                    // Navigate to login activity
                    Intent intent = LoginActivity
                            .loginActivityIntentFactory(getApplicationContext());
                    startActivity(intent);
                }
            } else {
                MainActivity.toastMaker(this, username + " already exists");
            }
        });
    }

    // STATIC METHODS

    /**
     * Intent Factory method for SignupActivity.
     * @param context The application context.
     * @return The SignupActivity intent.
     */
    static Intent signupActivitiIntentFactory(Context context) {
        Intent intent = new Intent(context, SignupActivity.class);
        return intent;
    }

}