package com.webcraftsolutions.project02.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.HikingRoutes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class HikingRoutesDAOTest {

    // CONSTANT FIELDS
    private final int TEST_USER_ID = -1;

    // FIELDS
    private ActivitiDatabase db; // Database instance
    private HikingRoutesDAO hikingRoutesDAO; // DAO instance
    private HikingRoutes hikingRoute; // HikingRoute entity

    /**
     * Runs before each test. Sets up Database and HikingRoute variables.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        // Get Database
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class).build();

        // Get DAO
        hikingRoutesDAO = db.hikingRoutesDAO();

        // Create a HikingRoute object
        hikingRoute = new HikingRoutes(4.5, "Be careful", "Low", "2 hours", 2, "Test Place", "Test Trail", TEST_USER_ID);
        hikingRoute.setId(1); // Set a default ID
    }

    /**
     * Runs after each test. Closes database and invalidates variables.
     * @throws Exception Exception
     */
    @After
    public void tearDown() throws Exception {
        // Close Database
        db.close();

        // Invalidate Variables
        hikingRoutesDAO = null;
        hikingRoute = null;
    }

    /**
     * Test for the Insert method.
     */
    @Test
    public void insert() {
        // Check that HikingRoute is NOT in Database initially
        assertNull(hikingRoutesDAO.getHikingRouteByIdSynchronous(hikingRoute.getId()));

        // Insert HikingRoute into Database
        hikingRoutesDAO.insert(hikingRoute);

        // Check that HikingRoute IS in Database
        HikingRoutes dbHikingRoute = hikingRoutesDAO.getHikingRouteByIdSynchronous(hikingRoute.getId());
        assertEquals(hikingRoute, dbHikingRoute);
    }

    /**
     * Test for the Delete method.
     */
    @Test
    public void delete() {
        // Insert HikingRoute into Database
        hikingRoutesDAO.insert(hikingRoute);

        // Check that HikingRoute is in Database
        assertNotNull(hikingRoutesDAO.getHikingRouteByIdSynchronous(hikingRoute.getId()));

        // Delete HikingRoute
        hikingRoutesDAO.delete(hikingRoute);

        // Check that HikingRoute is NOT in Database
        assertNull(hikingRoutesDAO.getHikingRouteByIdSynchronous(hikingRoute.getId()));
    }

    /**
     * Test for the Update method.
     */
    @Test
    public void update() {
        // Insert HikingRoute into Database
        hikingRoutesDAO.insert(hikingRoute);

        // Update the HikingRoute
        hikingRoute.setName("Updated Trail");
        hikingRoutesDAO.update(hikingRoute);

        // Get the updated HikingRoute
        HikingRoutes dbHikingRoute = hikingRoutesDAO.getHikingRouteByIdSynchronous(hikingRoute.getId());

        // Check that the name has been updated
        assertEquals("Updated Trail", dbHikingRoute.getName());
    }
}
