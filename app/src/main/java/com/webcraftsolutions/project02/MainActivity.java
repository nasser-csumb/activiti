/**
 * Title: Project 02: Activiti - Main Menu Activity
 * Author(s): Noah deFer
 * Date Created: 4/8/2025
 * Description: The main menu for the Activiti application.
 *      Contains buttons that lead to the other sections of the Activiti application.
 */

package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // CLASS FIELDS

    // The key for the logged in user extra.
    public static final String LOGGED_IN_USER_ID_KEY =
            "com.webcraftsolutions.project02.LOGGED_IN_USER_ID_KEY";

    // The key for the logout user extra.
    public static final String USER_LOGOUT_KEY = "com.webcraftsolutions.project02.USER_LOGOUT_KEY";

    // The default value for when no user is logged in.
    public static final int LOGGED_OUT = -1;

    // The key for the application's shared preferences.
    public static final String PREFERENCES_KEY = "WebcraftSolutionsPreferencesActiviti";

    // The key for the application's user id preference.
    public static final String PREFERENCES_USER_ID_KEY =
            "WebCraftSolutionsPreferencesUserIdKeyActiviti";

    // The identifier used for Logcat.
    public static final String TAG = "JNNS_Activiti";

    // INSTANCE FIELDS

    private ActivityMainBinding binding;

    // The ID of the logged in user. Defaults to LOGGED_OUT.
    private int loggedInUserId = LOGGED_OUT;

    // The repository.
    private ActivitiRepository repository;

    // The data of the logged in user.
    private User user;

    // METHODS

    private void enableAdmin() {

    }

    /**
     * Attempts to get user and user id from the application shared preferences, instance state,
     *      intent extras, and user repository.
     * loggedInUserId is set to LOGGED_OUT by default if no user id is found.
     * Starts LoginActivity if a user id is found but no valid user.
     */
    private void loginUser(Bundle savedInstanceState) {
        // Get Shared Preferences
        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        // Check shared preferences for logged in user.
        loggedInUserId = sharedPreferences.getInt(PREFERENCES_USER_ID_KEY, LOGGED_OUT);

        // Check instance state for user id.
        if(loggedInUserId == LOGGED_OUT && savedInstanceState != null
                && savedInstanceState.containsKey(PREFERENCES_USER_ID_KEY)) {
            loggedInUserId = savedInstanceState.getInt(PREFERENCES_USER_ID_KEY, LOGGED_OUT);
        }

        // Check intent for user id.
        if(loggedInUserId == LOGGED_OUT) {
            loggedInUserId = getIntent().getIntExtra(LOGGED_IN_USER_ID_KEY, LOGGED_OUT);
        }

        // Check if loggedInUserId is -1
        if(loggedInUserId == LOGGED_OUT) {
            return;
        }

        // Check repository for logged in user.
        LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
            if(this.user != null) {
                invalidateOptionsMenu();

                // Update Text
                binding.mainMenuGreetingTextView.setText(String
                        .format("Hello %s!", user.getUsername()));
                binding.mainMenuTopMenu.topMenuUserTextView.setText(String
                        .format("%s", user.getUsername()));

                // Activate or Deactivate Admin features
                if(user.isAdmin()) {
                    enableAdmin();
                }
            }
        });
    }

    /**
     * Stores loggedInUserId into the activity intent.
     * Calls the static logout method.
     */
    private void logout() {
        // Set user id preference to LOGGED_OUT
        loggedInUserId = LOGGED_OUT;
        updateSharedPreference();
        getIntent().putExtra(LOGGED_IN_USER_ID_KEY, LOGGED_OUT);

        // Start LoginActivity
        Intent intent = LoginActivity.loginActivityIntentFactory(getApplicationContext());
        startActivity(intent);
    }

    /**
     * Called when this activity is created.
     * Sets an OnClickListener for each button.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get repository
        repository = ActivitiRepository.getRepository(getApplication());

        // Add Default Users
        User admin = new User("admin1", "admin1");
        admin.setId(1);
        admin.setAdmin(true);
        User testUser = new User("testuser1", "testuser1");
        testUser.setId(2);
        repository.insertUser(admin, testUser);

        // Go back to LoginActivity if user is trying to logout.
        if(getIntent().getBooleanExtra(USER_LOGOUT_KEY, false)) { logout(); }

        // Go back to LoginActivity if user is not logged in.
        loginUser(savedInstanceState);
        if(loggedInUserId == LOGGED_OUT){
            logout();
        }

        // Store user id in preferences.
        updateSharedPreference();

        // Hide Back Button
        binding.mainMenuTopMenu.topMenuBackTextView.setText("");

        // Set OnClickListener for logout button
        binding.mainMenuTopMenu.topMenuUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog(MainActivity.this);

            }
        });

        // Set OnClickListener for mainMenuEventButton
        binding.mainMenuEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EventActivity
                        .eventActivityIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });

        // Set OnClickListener for mainMenuExerciseButton
        binding.mainMenuExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO SUHAIB Start Exercise Activity
//                Intent intent = Exercise Activity Intent Factory
//                startActivity(intent);

            }
        });

        // Set OnClickListener for mainMenuWellnessButton
        binding.mainMenuWellnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WellnessActivity.wellnessActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        // Set OnClickListener for mainMenuTravelButton
        binding.mainMenuTravelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TravelAndExplorationActivity.travelAndExplorationActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    /**
     * Called before MainActivity is killed.
     * Attempts to save user id preference.
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save user id preference.
        outState.putInt(LOGGED_IN_USER_ID_KEY, loggedInUserId);
        updateSharedPreference();
    }

    /**
     * Called when logoutMenuItem is clicked.
     * Displays an alert message to the user.
     * User clicks 'Logout': logout() is called.
     * User clicks 'Cancel': alert message is dismissed.
     */
    private void showLogoutDialog(Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Logout?");

        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();
    }

    /**
     * Updates user id shared preference to loggedInUserId.
     */
    private void updateSharedPreference() {
        // Create SharedPreferences and SharedPreferences.Editor
        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();

        // Insert loggedInUserId
        sharedPrefEditor.putInt(PREFERENCES_USER_ID_KEY, loggedInUserId);
        sharedPrefEditor.apply();
    }

    // STATIC METHODS

    /**
     * Intent factory for MainActivity.
     * @param context The application context.
     * @param userId The id of the logged in user.
     * @return The MainActivity Intent.
     */
    static Intent mainActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(LOGGED_IN_USER_ID_KEY, userId);
        return intent;
    }

    /**
     * Intent factory for MainActivity. Used when the user is logging out.
     * @param context The application context.
     * @param logout If the user is trying to logout (true) or not logout (false)
     * @return The MainActivity Intent.
     */
    static Intent mainActivityIntentFactory(Context context, boolean logout) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_LOGOUT_KEY, logout);
        return intent;
    }

    /**
     * Creates a toast and displays it to the screen.
     * @param message The message to be displayed.
     */
    public static void toastMaker(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // GETTERS & SETTERS
}