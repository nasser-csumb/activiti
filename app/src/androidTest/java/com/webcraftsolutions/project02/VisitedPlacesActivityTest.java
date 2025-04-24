/**
 * Title: Project 02: Activiti - Visited Places
 * File: VisitedPlacesActivityTest.java - Implementation of VisitedPlacesActivityTest
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 23 April 2025
 * Explanation/Abstract: This will test the VisitedPlacesActivity.java file.
 */

package com.webcraftsolutions.project02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VisitedPlacesActivityTest {

    private static final String VISITED_PLACE_EXTRA = "VISITED_PLACE_INTENT_EXTRA";

    private static final String TEST_USER_ID = "TEST_USER";

    private Context context;

    private User user;

    /**
     * Runs before each test. Sets up variables.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        context = ApplicationProvider.getApplicationContext();

        user = new User(TEST_USER_ID, "Test User");
    }

    /**
     * Runs after each test. Invalidates variables.
     * @throws Exception Exception
     */
    @After
    public void tearDown() throws Exception {
        context = null;
        user = null;
    }

    /**
     * Implementation Test for VisitedPlacesActivity's intent factory method.
     */
    @Test
    public void visitedPlacesActivityIntentFactory() {
        Intent intent = VisitedPlacesActivity.visitedPlacesIntentFactory(context, user.getId());

        assertNotNull(intent);

        assertEquals(user.getId(), intent.getIntExtra(MainActivity.LOGGED_IN_USER_ID_KEY, MainActivity.LOGGED_OUT));

        assertFalse(intent.getBooleanExtra(VISITED_PLACE_EXTRA, false));
    }
}
