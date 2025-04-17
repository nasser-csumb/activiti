package com.webcraftsolutions.project02;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SignupActivityTest {

    // Test String
    String SIGNUP_EXTRA = "SIGNUP_INTENT_EXTRA";

    // Application Context
    Context context;

    @Before
    public void setUp() throws Exception {
        // Get Context
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

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
        assertFalse(intent.getBooleanExtra(SIGNUP_EXTRA, false));
    }
}