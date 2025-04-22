package com.webcraftsolutions.project02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TravelAndExplorationActivityTest {

    // CONSTANT FIELDS
    // Test String
    private static final String TRAVEL_EXPLORATION_EXTRA = "TRAVEL_EXPLORATION_INTENT_EXTRA";  // Example extra string

    // Test User ID
    private static final String TEST_USER_ID = "TEST_USER";

    // FIELDS
    // Application Context
    private Context context;

    // Test User (assuming a simple User class exists with an ID)
    private User user;

    /**
     * Runs before each test. Sets up variables.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        // Get Context
        context = ApplicationProvider.getApplicationContext();

        // Set up test user
        user = new User(TEST_USER_ID, "Test User");
    }

    /**
     * Runs after each test. Invalidates variables.
     * @throws Exception Exception
     */
    @After
    public void tearDown() throws Exception {
        // Invalidate Variables
        context = null;
        user = null;
    }

    /**
     * Implementation Test for TravelAndExplorationActivity's intent factory method.
     */
    @Test
    public void travelAndExplorationActivityIntentFactory() {
        // Get Intent from the intent factory method in TravelAndExplorationActivity
        Intent intent = TravelAndExplorationActivity.travelAndExplorationActivityIntentFactory(context, user.getId());

        // Check that the Intent is not null
        assertNotNull(intent);

        // Validate the user ID extra passed to the Intent
        assertEquals(user.getId(), intent.getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT));

        // Check if other relevant extras or flags are passed in the Intent
        assertFalse(intent.getBooleanExtra(TRAVEL_EXPLORATION_EXTRA, false));  // Example of checking an extra
    }
}
