/**
 * Title: Project 02: Activiti - Outdoors
 * File: OutdoorsDAOTest.java - Implementation of OutdoorsDAOTest
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 23 April 2025
 * Explanation/Abstract: This will test the OutdoorsDAO.java file.
 */

package com.webcraftsolutions.project02.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.Outdoors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OutdoorsDAOTest {

    private final int TEST_USER_ID = -1;

    private ActivitiDatabase db;
    private OutdoorsDAO outdoorsDAO;
    private Outdoors outdoors;

    /**
     * Runs before each test. Sets up Database and HikingRoute variables.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class).build();

        outdoorsDAO = db.outdoorsDAO();

        outdoors = new Outdoors("Dusty Ol'back", "Sacramento", "running", 2, "I have been here", 4.5, TEST_USER_ID);
        outdoors.setId(1);
    }

    /**
     * Runs after each test. Closes database and invalidates variables.
     * @throws Exception Exception
     */
    @After
    public void tearDown() throws Exception {
        db.close();

        outdoorsDAO = null;
        outdoors = null;
    }

    /**
     * Test for the Insert method.
     */
    @Test
    public void insert() {
        assertNull(outdoorsDAO.getOutdoorByIdSynchronous(outdoors.getId()));

        outdoorsDAO.insert(outdoors);

        Outdoors dbHikingRoute = outdoorsDAO.getOutdoorByIdSynchronous(outdoors.getId());
        assertEquals(outdoors, dbHikingRoute);
    }

    /**
     * Test for the Delete method.
     */
    @Test
    public void delete() {
        outdoorsDAO.insert(outdoors);

        assertNotNull(outdoorsDAO.getOutdoorByIdSynchronous(outdoors.getId()));

        outdoorsDAO.delete(outdoors);

        assertNull(outdoorsDAO.getOutdoorByIdSynchronous(outdoors.getId()));
    }

    /**
     * Test for the Update method.
     */
    @Test
    public void update() {
        outdoorsDAO.insert(outdoors);

        outdoors.setName("Updated Trail");
        outdoorsDAO.update(outdoors);

        Outdoors dbHikingRoute = outdoorsDAO.getOutdoorByIdSynchronous(outdoors.getId());

        assertEquals("Updated Trail", dbHikingRoute.getName());
    }
}
