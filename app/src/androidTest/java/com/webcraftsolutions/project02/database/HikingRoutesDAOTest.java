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

    private final int TEST_USER_ID = -1;

    private ActivitiDatabase db;
    private HikingRoutesDAO hikingRoutesDAO;
    private HikingRoutes hikingRoute;

    /**
     * Runs before each test. Sets up Database and HikingRoute variables.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class).build();

        hikingRoutesDAO = db.hikingRoutesDAO();

        hikingRoute = new HikingRoutes(4.5, "Be careful", "Low", "2 hours", 2, "Test Place", "Test Trail", TEST_USER_ID);
        hikingRoute.setId(1);
    }

    /**
     * Runs after each test. Closes database and invalidates variables.
     * @throws Exception Exception
     */
    @After
    public void tearDown() throws Exception {
        db.close();

        hikingRoutesDAO = null;
        hikingRoute = null;
    }

    /**
     * Test for the Insert method.
     */
    @Test
    public void insert() {
        assertNull(hikingRoutesDAO.getHikingRouteByIdSynchronous(hikingRoute.getId()));

        hikingRoutesDAO.insert(hikingRoute);

        HikingRoutes dbHikingRoute = hikingRoutesDAO.getHikingRouteByIdSynchronous(hikingRoute.getId());
        assertEquals(hikingRoute, dbHikingRoute);
    }

    /**
     * Test for the Delete method.
     */
    @Test
    public void delete() {
        hikingRoutesDAO.insert(hikingRoute);

        assertNotNull(hikingRoutesDAO.getHikingRouteByIdSynchronous(hikingRoute.getId()));

        hikingRoutesDAO.delete(hikingRoute);

        assertNull(hikingRoutesDAO.getHikingRouteByIdSynchronous(hikingRoute.getId()));
    }

    /**
     * Test for the Update method.
     */
    @Test
    public void update() {
        hikingRoutesDAO.insert(hikingRoute);

        hikingRoute.setName("Updated Trail");
        hikingRoutesDAO.update(hikingRoute);

        HikingRoutes dbHikingRoute = hikingRoutesDAO.getHikingRouteByIdSynchronous(hikingRoute.getId());

        assertEquals("Updated Trail", dbHikingRoute.getName());
    }
}
