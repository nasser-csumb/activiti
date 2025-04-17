/**
 * Title: CST-338 Project 02: Activiti - MainActivity Test
 * @author Noah deFer
 * Date Created: 4/17/2025
 * Description: Java class for implementation tests for MainActivity.java.
 */
package com.webcraftsolutions.project02;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;

import com.webcraftsolutions.project02.database.entities.User;

import junit.framework.TestCase;

public class MainActivityTest extends TestCase {

    // CONSTANT FIELDS

    String TEST_ADMIN_NAME = "TEST_ADMIN";
    String TEST_USER_NAME = "TEST_USER";

    // FIELDS

    // Application Context
    Context context;

    // Logout Boolean
    boolean logout;

    // Repository
//    ActivitiRepository repository;

    // Test Admin User
    User testAdmin;

    // Test User
    User testUser;



    public void setUp() throws Exception {
        super.setUp();

        // Get Application Context
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Set Logout Boolean
        logout = true;

        // Get Repository

        // Set Test Users
        testAdmin = new User(TEST_ADMIN_NAME, TEST_ADMIN_NAME);
        testAdmin.setAdmin(true);
        testUser = new User(TEST_USER_NAME, TEST_USER_NAME);


    }

    public void tearDown() throws Exception {
        super.tearDown();

        // Invalidate Variables
        context = null;
        logout = true;
        testAdmin = null;
        testUser = null;
    }

//    public void testDeleteUser() {
//
//    }
//
//    public void testToggleUserAdmin() {
//    }

    public void testMainActivityIntentFactoryUserId() {
        // Get Intent
        Intent intent = MainActivity.mainActivityIntentFactory(context, testUser.getId());

        // Check Intent Extra
        assertEquals(testUser.getId(), intent.getIntExtra(
                MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT));
        assertEquals(MainActivity.LOGGED_OUT, intent
                .getIntExtra("", MainActivity.LOGGED_OUT));

    }

    public void testMainActivityIntentFactoryLogout() {
        // Get Intent
        Intent intent = MainActivity.mainActivityIntentFactory(context, logout);

        // Check Intent Extra
        assertNotNull(intent);
        assertEquals(logout, intent.getBooleanExtra(MainActivity.USER_LOGOUT_KEY, !logout));
        assertEquals(!logout, intent.getBooleanExtra("", !logout));
    }
}