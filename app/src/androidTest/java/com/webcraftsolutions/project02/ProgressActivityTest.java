/**
 * Title: CST-338 Project 02: Activiti - ProgressActivity Test
 * @author Suhaib Peracha
 * Date Created: 4/21/2025
 * Description: Java class for implementation test for ProgressActivity.java.
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

public class ProgressActivityTest {

    // CONSTANTS
    String USERNAME = "TEST_USER";

    // VARIABLES
    private Context context;
    private User user;

    /**
     * Runs before each test. Sets up variables.
     */
    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        user = new User(USERNAME, USERNAME);
    }

    /**
     * Runs after each test. Invalidates variables.
     */
    @After
    public void tearDown() {
        context = null;
        user = null;
    }

    /**
     * Implementation Test for ProgressActivity's intent factory method.
     */
    @Test
    public void progressActivityIntentFactory() {
        // Get Intent using factory
        Intent intent = ProgressActivity.progressActivityIntentFactory(context, user.getId());

        // Assert intent is not null
        assertNotNull(intent);

        // Assert intent has correct user ID
        assertEquals(user.getId(), intent.getIntExtra(
                MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT));
    }
}
