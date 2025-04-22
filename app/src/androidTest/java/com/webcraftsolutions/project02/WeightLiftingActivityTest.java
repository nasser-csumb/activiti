/**
 * Title: CST-338 Project 02: Activiti - WeightLiftingActivity Test
 * @author Suhaib Peracha
 * Date Created: 4/21/2025
 * Description: Unit test for WeightLiftingActivity's intent factory method.
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

public class WeightLiftingActivityTest {

    // CONSTANTS
    private static final String TEST_USERNAME = "testuser";

    // FIELDS
    private Context context;
    private User user;

    /**
     * Set up test environment before each test.
     */
    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        user = new User(TEST_USERNAME, TEST_USERNAME);
        user.setId(2);
    }

    /**
     * Clean up test environment after each test.
     */
    @After
    public void tearDown() {
        context = null;
        user = null;
    }

    /**
     * Implementation Test for WeightLiftingActivity's intent factory method.
     */
    @Test
    public void weightLiftingActivityIntentFactory() {
        // Get intent from factory
        Intent intent = WeightLiftingActivity.weightLiftingActivityIntentFactory(context, user.getId());

        // Assert the intent is not null
        assertNotNull(intent);

        // Assert correct user ID passed in extras
        assertEquals(user.getId(), intent.getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT));
    }
}