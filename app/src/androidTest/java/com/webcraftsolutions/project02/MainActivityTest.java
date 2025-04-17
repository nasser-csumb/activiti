package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.User;

import junit.framework.Test;
import junit.framework.TestCase;

public class MainActivityTest extends TestCase {

    // Application Context
    Context CONTEXT;

    // Logout Boolean
    boolean LOGOUT;

    // Repository
    ActivitiRepository REPOSITORY;

    // Test Admin User
    User TEST_ADMIN;
    String TEST_ADMIN_NAME = "TEST_ADMIN";

    // Test User
    User TEST_USER;
    String TEST_USER_NAME = "TEST_USER";


    public void setUp() throws Exception {
        super.setUp();

        // Get Application Context
        CONTEXT = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Set Logout Boolean
        LOGOUT = true;

        // Get Repository

        // Set Test Users
        TEST_ADMIN = new User(TEST_ADMIN_NAME, TEST_ADMIN_NAME);
        TEST_ADMIN.setAdmin(true);
        TEST_USER = new User(TEST_USER_NAME, TEST_USER_NAME);


    }

    public void tearDown() throws Exception {
        super.tearDown();

        // Invalidate Variables
        CONTEXT = null;
    }

    public void testDeleteUser() {
    }

    public void testToggleUserAdmin() {
    }

    public void testMainActivityIntentFactoryUserId() {
        // Get Intent
        Intent intent = MainActivity.mainActivityIntentFactory(CONTEXT, TEST_USER.getId());

        // Check Intent Extra
        assertEquals(TEST_USER.getId(), intent.getIntExtra(
                MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT));
        assertEquals(MainActivity.LOGGED_OUT, intent
                .getIntExtra("", MainActivity.LOGGED_OUT));

    }

    public void testMainActivityIntentFactoryLogout() {
        // Get Intent
        Intent intent = MainActivity.mainActivityIntentFactory(CONTEXT, LOGOUT);

        // Check Intent Extra
        assertEquals(LOGOUT, intent.getBooleanExtra(MainActivity.USER_LOGOUT_KEY, !LOGOUT));
        assertEquals(!LOGOUT, intent.getBooleanExtra("", !LOGOUT));
    }
}