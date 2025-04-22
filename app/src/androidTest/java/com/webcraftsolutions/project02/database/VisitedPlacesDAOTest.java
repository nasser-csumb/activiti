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

    private final int TEST_USER_ID = -1;

    private ActivitiDatabase db;
    private VisitedPlacesDAO visitedPlacesDAO;
    private VisitedPlaces visitedPlace;

    /**
     * Runs before each test. Sets up Database and VisitedPlace variables.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class).build();

        visitedPlacesDAO = db.visitedPlacesDAO();

        visitedPlace = new VisitedPlaces(
                TEST_USER_ID,
                "Test Location",
                "Test Category",
                "Test Notes",
                "test_photo_uri",
                System.currentTimeMillis()
        );
        visitedPlace.setId(1);
    }

    /**
     * Runs after each test. Closes database and invalidates variables.
     * @throws Exception Exception
     */
    @After
    public void tearDown() throws Exception {
        db.close();

        visitedPlacesDAO = null;
        visitedPlace = null;
    }

    /**
     * Test for the Insert method.
     */
    @Test
    public void insert() {
        assertNull(visitedPlacesDAO.getVisitedPlaceByIdSynchronous(visitedPlace.getId()));

        visitedPlacesDAO.insert(visitedPlace);

        VisitedPlaces dbVisitedPlace = visitedPlacesDAO.getVisitedPlaceByIdSynchronous(visitedPlace.getId());
        assertNotNull(dbVisitedPlace);
        assertEquals(visitedPlace.getName(), dbVisitedPlace.getName());
    }

    /**
     * Test for the Delete method.
     */
    @Test
    public void delete() {
        visitedPlacesDAO.insert(visitedPlace);

        VisitedPlaces dbVisitedPlace = visitedPlacesDAO.getVisitedPlaceByIdSynchronous(visitedPlace.getId());
        assertNotNull(dbVisitedPlace);

        visitedPlacesDAO.delete(visitedPlace);

        VisitedPlaces deletedPlace = visitedPlacesDAO.getVisitedPlaceByIdSynchronous(visitedPlace.getId());
        assertNull(deletedPlace);  // Should be null after deletion
    }

    /**
     * Test for the Update method.
     */
    @Test
    public void update() {
        visitedPlacesDAO.insert(visitedPlace);

        visitedPlace.setName("Updated Location");
        visitedPlacesDAO.update(visitedPlace);

        VisitedPlaces dbVisitedPlace = visitedPlacesDAO.getVisitedPlaceByIdSynchronous(visitedPlace.getId());

        assertEquals("Updated Location", dbVisitedPlace.getName());
    }
}
