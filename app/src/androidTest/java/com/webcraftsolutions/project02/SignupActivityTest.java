/**
 * Title: CST-338 Project 02: Activiti - SignupActivity Test
 * @author Noah deFer
 * Date Created: 4/17/2025
 * Description: Java class for implementation tests for SignupActivity.java.
 */
package com.webcraftsolutions.project02;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SignupActivityTest {

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
        context = null;
    }

    @Test
    public void signupActivitiIntentFactory() {
        // Get Intent
        Intent intent = SignupActivity.signupActivitiIntentFactory(context);

        // Check Intent
        assertNotNull(intent);
        // Test String
        String SIGNUP_EXTRA = "SIGNUP_INTENT_EXTRA";
        assertFalse(intent.getBooleanExtra(SIGNUP_EXTRA, false));
    }
}