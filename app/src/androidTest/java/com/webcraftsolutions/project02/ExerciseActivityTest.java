/**
 * Title: CST-338 Project 02: Activiti - ExerciseActivity Test
 * @author Suhaib Peracha
 * Date Created: 4/21/2025
 * Description: Unit test for ExerciseActivity's intent factory method.
 */
package com.webcraftsolutions.project02;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExerciseActivityTest {

    // FIELDS
    private Context context;

    /**
     * Setup before each test.
     */
    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    /**
     * Tear down after each test.
     */
    @After
    public void tearDown() {
        context = null;
    }

    /**
     * Implementation Test for ExerciseActivity's intent factory method.
     */
    @Test
    public void exerciseActivityIntentFactory() {
        // Create intent from factory
        Intent intent = ExerciseActivity.exerciseActivityIntentFactory(context, 1);

        // Check intent is not null
        assertNotNull(intent);

        // Check intent component is not null
        assertNotNull(intent.getComponent());

        // Check correct class is set
        assertEquals(ExerciseActivity.class.getName(), intent.getComponent().getClassName());
    }
}