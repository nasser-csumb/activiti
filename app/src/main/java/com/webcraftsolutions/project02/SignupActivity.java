/**
 * Title: CST-338 Project 02: Activiti - Signup Page
 * @author Noah deFer
 * Date Created: 4/16/2025
 * Description: An activity to allow the user to signup for Activiti.
 *      New user credentials will be added to the User table.
 */

package com.webcraftsolutions.project02;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.webcraftsolutions.project02.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    // CLASS FIELDS

    // INSTANCE FIELDS

    // The binding object.
    ActivitySignupBinding binding;

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
    }

    // STATIC METHODS
}