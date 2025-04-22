/**
 * Title: CST-338 Project 02: Activiti - EventCreateActivity Test
 * @author Noah deFer
 * Date Created: 4/17/2025
 * Description: Java class for implementation tests for EventCreateActivity.java.
 */
package com.webcraftsolutions.project02;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;

import com.webcraftsolutions.project02.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventCreateActivityTest {

    // CONSTANT FIELDS

    // Test String
    private final String EVENT_CREATE_EXTRA = "EVENT_CREATE_INTENT_EXTRA";

    // FIELDS

    // Application Context
    private Context context;

    // Test User
    private User user;

    /**
     * Runs before each test. Sets up variables.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        // Get Context
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Set user
        String USERNAME = "TEST_USER";
        user = new User(USERNAME, USERNAME);
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
     * Implementation Test for EventCreateActivity's intent factory method.
     */
    @Test
    public void eventCreateActivityIntentFactory() {
        // Get Intent
        Intent intent = EventCreateActivity.eventCreateActivityIntentFactory(context, user.getId());

        // Check Intent
        assertNotNull(intent);
        assertEquals(user.getId(), intent.getIntExtra(
                MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT));
        assertFalse(intent.getBooleanExtra(EVENT_CREATE_EXTRA, false));
    }

    @Test
    public void eventCreateActivityIntentFactoryEventId() {
        // Get Intent
        Intent intent = EventCreateActivity.eventCreateActivityIntentFactory(context, user.getId(), 1);

        // Check Intent
        assertNotNull(intent);
        assertEquals(1, intent.getIntExtra(
                EventCreateActivity.EVENT_EXTRA, -1));
        assertFalse(intent.getBooleanExtra(EVENT_CREATE_EXTRA, false));
    }
}