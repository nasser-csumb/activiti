/**
 * Title: CST-338 Project 02: Activiti - CardioWorkoutDAO Test
 * @author Suhaib Peracha
 * Date Created: 4/21/2025
 * Description: Unit tests for CardioWorkoutDAO operations (insert, delete, get).
 */
package com.webcraftsolutions.project02.database;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.CardioWorkout;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class CardioWorkoutDAOTest {

    // Constants
    private final int TEST_USER_ID = -100;

    // DB and DAO
    private ActivitiDatabase db;
    private CardioWorkoutDAO cardioWorkoutDAO;

    // Sample Cardio Workout
    private CardioWorkout cardioWorkout;

    /**
     * Set up test database and DAO before each test.
     */
    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class).build();
        cardioWorkoutDAO = db.cardioWorkoutDAO();

        cardioWorkout = new CardioWorkout(TEST_USER_ID, "Running", 30, "High", new Date());
    }

    /**
     * Close the database after each test.
     */
    @After
    public void tearDown() {
        db.close();
        cardioWorkout = null;
    }

    /**
     * Test: Insert workout and retrieve by userId.
     */
    @Test
    public void insertAndGetByUserId() {
        cardioWorkoutDAO.insert(cardioWorkout);
        List<CardioWorkout> result = cardioWorkoutDAO.getCardioWorkoutsByUserId(TEST_USER_ID);
        assertEquals(1, result.size());
        assertEquals("Running", result.get(0).getType());
    }

    /**
     * Test: Insert then delete workout.
     */
    @Test
    public void delete() {
        long id = cardioWorkoutDAO.insert(cardioWorkout)[0];
        CardioWorkout inserted = cardioWorkoutDAO.getCardioWorkoutById((int) id); // You may need to add this method
        cardioWorkoutDAO.delete(inserted);
        List<CardioWorkout> result = cardioWorkoutDAO.getCardioWorkoutsByUserId(TEST_USER_ID);
        assertEquals(0, result.size());
    }

    /**
     * Test: Insert two workouts, then delete all by userId.
     */
    @Test
    public void deleteAllByUserId() {
        cardioWorkoutDAO.insert(cardioWorkout);
        cardioWorkoutDAO.insert(new CardioWorkout(TEST_USER_ID, "Biking", 20, "Medium", new Date()));
        cardioWorkoutDAO.deleteAllByUserId(TEST_USER_ID);
        List<CardioWorkout> result = cardioWorkoutDAO.getCardioWorkoutsByUserId(TEST_USER_ID);
        assertEquals(0, result.size());
    }

    /**
     * Test: Insert multiple workouts and validate all retrieved belong to the user.
     */
    @Test
    public void multipleInsertsAndQuery() {
        cardioWorkoutDAO.insert(new CardioWorkout(TEST_USER_ID, "Running", 30, "High", new Date()));
        cardioWorkoutDAO.insert(new CardioWorkout(TEST_USER_ID, "Swimming", 25, "Medium", new Date()));
        List<CardioWorkout> result = cardioWorkoutDAO.getCardioWorkoutsByUserId(TEST_USER_ID);
        assertEquals(2, result.size());
    }
}
