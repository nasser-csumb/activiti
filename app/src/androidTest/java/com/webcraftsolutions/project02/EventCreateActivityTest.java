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
    String EVENT_CREATE_EXTRA = "EVENT_CREATE_INTENT_EXTRA";

    // User Username/Password String
    String USERNAME = "TEST_USER";

    // FIELDS

    // Application Context
    Context context;

    // Test User
    User user;

    @Before
    public void setUp() throws Exception {
        // Get Context
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Set user
        user = new User(USERNAME, USERNAME);
    }

    @After
    public void tearDown() throws Exception {
        // Invalidate Variables
        context = null;
        user = null;
    }

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
}