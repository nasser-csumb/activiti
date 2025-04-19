/**
 * Title: CST-338 Project 02: Activiti - LoginActivity Test
 * @author Noah deFer
 * Date Created: 4/17/2025
 * Description: Java class for implementation tests for LoginActivity.java.
 */
package com.webcraftsolutions.project02;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginActivityTest {

    // CONSTANT FIELDS

    // FIELDS

    // Application Context
    private Context context;

    /**
     * Runs before each test. Sets up variables.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        // Get Context
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    /**
     * Runs after each test. Invalidates variables.
     * @throws Exception Exception
     */
    @After
    public void tearDown() throws Exception {
        // Invalidate Variables
        context = null;
    }

    /**
     * Implementation Test for LoginActivity's intent factory method.
     */
    @Test
    public void loginActivityIntentFactory() {
        // Get Intent
        Intent intent = LoginActivity.loginActivityIntentFactory(context);

        // Check Intent
        assertNotNull(intent);
        String LOGIN_EXTRA = "LOGIN_INTENT_EXTRA";
        assertFalse(intent.getBooleanExtra(LOGIN_EXTRA, false));
    }
}