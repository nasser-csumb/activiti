/**
 * Title: CST-338 Project 02: Activiti - WeightLiftingWorkoutDAO Test
 * @author Suhaib Peracha
 * Date Created: 4/21/2025
 * Description: Unit tests for WeightLiftingWorkoutDAO operations (insert, delete, get).
 */
package com.webcraftsolutions.project02.database;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.WeightLiftingWorkout;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class WeightLiftingWorkoutDAOTest {

    // Constants
    private final int TEST_USER_ID = -200;

    // DB and DAO
    private ActivitiDatabase db;
    private WeightLiftingWorkoutDAO liftingDAO;

    // Sample workout
    private WeightLiftingWorkout liftingWorkout;

    /**
     * Set up test database and DAO before each test.
     */
    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class).build();
        liftingDAO = db.weightLiftingWorkoutDAO();

        liftingWorkout = new WeightLiftingWorkout(TEST_USER_ID, "Bench Press", 4, 32, 40, new Date());
    }

    /**
     * Close the database after each test.
     */
    @After
    public void tearDown() {
        db.close();
        liftingWorkout = null;
    }

    /**
     * Test: Insert workout and retrieve by userId.
     */
    @Test
    public void insertAndGetByUserId() {
        liftingDAO.insert(liftingWorkout);
        List<WeightLiftingWorkout> result = liftingDAO.getWeightLiftingWorkoutsByUserId(TEST_USER_ID);
        assertEquals(1, result.size());
        assertEquals("Bench Press", result.get(0).getExerciseName());
    }

    /**
     * Test: Insert then delete workout.
     */
    @Test
    public void delete() {
        liftingDAO.insert(liftingWorkout);
        liftingDAO.delete(liftingWorkout);
        List<WeightLiftingWorkout> result = liftingDAO.getWeightLiftingWorkoutsByUserId(TEST_USER_ID);
        assertEquals(0, result.size());
    }

    /**
     * Test: Insert multiple workouts and delete all by userId.
     */
    @Test
    public void deleteAllByUserId() {
        liftingDAO.insert(new WeightLiftingWorkout(TEST_USER_ID, "Squat", 3, 24, 30, new Date()));
        liftingDAO.insert(new WeightLiftingWorkout(TEST_USER_ID, "Deadlift", 2, 20, 25, new Date()));
        liftingDAO.deleteAllByUserId(TEST_USER_ID);
        List<WeightLiftingWorkout> result = liftingDAO.getWeightLiftingWorkoutsByUserId(TEST_USER_ID);
        assertEquals(0, result.size());
    }

    /**
     * Test: Insert multiple workouts and ensure all are returned for the same userId.
     */
    @Test
    public void multipleInsertsAndQuery() {
        liftingDAO.insert(new WeightLiftingWorkout(TEST_USER_ID, "Shoulder Press", 3, 18, 15, new Date()));
        liftingDAO.insert(new WeightLiftingWorkout(TEST_USER_ID, "Lat Pulldown", 3, 21, 20, new Date()));
        List<WeightLiftingWorkout> result = liftingDAO.getWeightLiftingWorkoutsByUserId(TEST_USER_ID);
        assertEquals(2, result.size());
    }
}
