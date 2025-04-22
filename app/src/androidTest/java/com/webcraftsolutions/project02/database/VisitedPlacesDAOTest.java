package com.webcraftsolutions.project02.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.VisitedPlaces;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class VisitedPlacesDAOTest {

    // CONSTANT FIELDS
    private final int TEST_USER_ID = -1;

    // FIELDS
    private ActivitiDatabase db; // Database instance
    private VisitedPlacesDAO visitedPlacesDAO; // DAO instance
    private VisitedPlaces visitedPlace; // VisitedPlace entity

    /**
     * Runs before each test. Sets up Database and VisitedPlace variables.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        // Get Database
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class).build();

        // Get DAO
        visitedPlacesDAO = db.visitedPlacesDAO();

        // Create a VisitedPlace object
        visitedPlace = new VisitedPlaces(
                TEST_USER_ID,
                "Test Location",
                "Test Category",
                "Test Notes",
                "test_photo_uri",
                System.currentTimeMillis()
        );
        visitedPlace.setId(1); // Set a default ID
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
        visitedPlacesDAO = null;
        visitedPlace = null;
    }

    /**
     * Test for the Insert method.
     */
    @Test
    public void insert() {
        // Check that VisitedPlace is NOT in Database initially
        assertNull(visitedPlacesDAO.getVisitedPlaceByIdSynchronous(visitedPlace.getId()));

        // Insert VisitedPlace into Database
        visitedPlacesDAO.insert(visitedPlace);

        // Check that VisitedPlace IS in Database
        VisitedPlaces dbVisitedPlace = visitedPlacesDAO.getVisitedPlaceByIdSynchronous(visitedPlace.getId());
        assertNotNull(dbVisitedPlace);
        assertEquals(visitedPlace.getName(), dbVisitedPlace.getName());
    }

    /**
     * Test for the Delete method.
     */
    @Test
    public void delete() {
        // Insert VisitedPlace into Database
        visitedPlacesDAO.insert(visitedPlace);

        // Check that VisitedPlace is in Database
        VisitedPlaces dbVisitedPlace = visitedPlacesDAO.getVisitedPlaceByIdSynchronous(visitedPlace.getId());
        assertNotNull(dbVisitedPlace);

        // Delete VisitedPlace
        visitedPlacesDAO.delete(visitedPlace);

        // Check that VisitedPlace is NOT in Database
        VisitedPlaces deletedPlace = visitedPlacesDAO.getVisitedPlaceByIdSynchronous(visitedPlace.getId());
        assertNull(deletedPlace);  // Should be null after deletion
    }

    /**
     * Test for the Update method.
     */
    @Test
    public void update() {
        // Insert VisitedPlace into Database
        visitedPlacesDAO.insert(visitedPlace);

        // Update the VisitedPlace
        visitedPlace.setName("Updated Location");
        visitedPlacesDAO.update(visitedPlace);

        // Get the updated VisitedPlace
        VisitedPlaces dbVisitedPlace = visitedPlacesDAO.getVisitedPlaceByIdSynchronous(visitedPlace.getId());

        // Check that the name has been updated
        assertEquals("Updated Location", dbVisitedPlace.getName());
    }
}
