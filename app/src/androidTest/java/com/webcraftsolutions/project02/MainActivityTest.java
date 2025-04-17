package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;

import junit.framework.TestCase;

public class MainActivityTest extends TestCase {

    // Application Context
    Context CONTEXT;

    public void setUp() throws Exception {
        super.setUp();

        // Get Application Context
        CONTEXT = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDeleteUser() {
    }

    public void testToggleUserAdmin() {
    }

    public void testMainActivityIntentFactoryUserId() {
        // Get Intent
        Intent intent = MainActivity.mainActivityIntentFactory(CONTEXT, 1);

        // Check Intent Extra
        assertEquals(1,
                intent.getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT));

    }

    public void testMainActivityIntentFactoryLogout() {
    }
}