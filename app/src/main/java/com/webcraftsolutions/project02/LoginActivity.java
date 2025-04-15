/**
 * Title: CST-338 Project 02: Activiti - Login Screen
 * @author Noah deFer
 * Date Created: 4/15/2025
 * Description: The login screen for Activiti.
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
import com.webcraftsolutions.project02.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    // INSTANCE FIELDS

    // The binding object for LoginActivity
    private ActivityLoginBinding binding;

    // The repository
    private ActivitiRepository repository;

    // METHODS

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

    /**
     * Compares the entered username and password with the repository.
     * Shows a toast if the username or password are invalid.
     * Starts MainActivity if the username and password are valid.
     */
    // TODO Update MainActivity intent factory call to include userId.
    private void verifyUser() {
        // Get entered username
        String username = binding.loginUsernameEditText.getText().toString();

        // Check if username is empty
        if(username.isEmpty()) {
            MainActivity.toastMaker(this, "Username may not be blank.");
            return;
        }

        // Get user from repository
        LiveData<User> userObserver = repository.getUserByUsername(username);
        userObserver.observe(this, user -> {
            /*
             * If the user exists:
             *      Start MainActivity if it is valid.
             *      Show toast if it is NOT valid.
             * Or show toast if the user does NOT exist.
             */
            if(user != null) {
                String password = binding.loginPasswordEditText.getText().toString();
                if(password.equals(user.getPassword())) {
                    Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
                    startActivity(intent);
                } else {
                    MainActivity.toastMaker(this, "Invalid Password");
                }
            } else {
                MainActivity.toastMaker(this,
                        username + " Is not a valid username.");

            }
        });
    }

    // STATIC METHODS

    /**
     * Intent factory for LoginActivity.
     * @param context The application context.
     * @return The LoginActivity Intent.
     */
    public static Intent loginActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }
}