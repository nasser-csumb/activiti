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

    // Test String
    String LOGIN_EXTRA = "LOGIN_INTENT_EXTRA";

    // Application Context
    Context CONTEXT;

    @Before
    public void setUp() throws Exception {
        // Get Context
        CONTEXT = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @After
    public void tearDown() throws Exception {
        // Invalidate Variables
        CONTEXT = null;
    }

    @Test
    public void loginActivityIntentFactory() {
        // Get Intent
        Intent intent = LoginActivity.loginActivityIntentFactory(CONTEXT);

        // Check Intent
        assertNotNull(intent);
        assertFalse(intent.getBooleanExtra(LOGIN_EXTRA, false));
    }
}