package com.webcraftsolutions.project02.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.TravelExploration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TravelExplorationDAOTest {

    private final int TEST_USER_ID = -1;

    private ActivitiDatabase db;
    private TravelExplorationDAO travelExplorationDAO;
    private TravelExploration travelExploration;

    /**
     * Runs before each test. Sets up Database and DAO with a test TravelExploration entry.
     */
    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class).build();
        travelExplorationDAO = db.travelExplorationDAO();

        travelExploration = new TravelExploration(
                TEST_USER_ID,
                "Sample Hiking Route",
                "Sample Outdoor Activity",
                "Sample Visited Place",
                System.currentTimeMillis()
        );
        travelExploration.setTravelId(1); // set default ID for testing
    }

    /**
     * Runs after each test to clean up.
     */
    @After
    public void tearDown() {
        db.close();
        travelExplorationDAO = null;
        travelExploration = null;
    }

    /**
     * Test for the Insert method.
     */
    @Test
    public void insert() {
        assertNull(travelExplorationDAO.getTravelExplorationByIdSynchronous(travelExploration.getTravelId()));

        travelExplorationDAO.insert(travelExploration);

        TravelExploration dbEntry = travelExplorationDAO.getTravelExplorationByIdSynchronous(travelExploration.getTravelId());
        assertEquals(travelExploration, dbEntry);
    }

    /**
     * Test for the Delete method.
     */
    @Test
    public void delete() {
        travelExplorationDAO.insert(travelExploration);
        assertNotNull(travelExplorationDAO.getTravelExplorationByIdSynchronous(travelExploration.getTravelId()));

        travelExplorationDAO.delete(travelExploration);
        assertNull(travelExplorationDAO.getTravelExplorationByIdSynchronous(travelExploration.getTravelId()));
    }

    /**
     * Test for the Update method.
     */
    @Test
    public void update() {
        travelExplorationDAO.insert(travelExploration);

        travelExploration.setHikingRoute("Updated Hiking Route");
        travelExplorationDAO.update(travelExploration);

        TravelExploration dbEntry = travelExplorationDAO.getTravelExplorationByIdSynchronous(travelExploration.getTravelId());
        assertEquals("Updated Hiking Route", dbEntry.getHikingRoute());
    }
}
