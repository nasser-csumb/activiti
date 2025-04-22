/**
 * Title: CST-338 Project 02: Activiti - EventDAO Test
 * @author Noah deFer
 * Date Created: 4/17/2025
 * Description: Java class for implementation tests for EventDAO.java.
 */
package com.webcraftsolutions.project02.database;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.Event;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class EventDAOTest {

    // CONSTANT FIELDS

    // User Username/Password String
    private final String TEST_EVENT = "TEST_EVENT";
    private final int TEST_USER_ID = -1;

    // FIELDS

    // Database Object
    private ActivitiDatabase db;

    // Event DAO Object
    private EventDAO eventDAO;

    // Event Object
    Event event;

    /**
     * Runs before each test. Sets up Database and event variables.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        // Get Database
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class).build();
        // Get UserDAO
        eventDAO = db.eventDAO();

        // Get User
        event = new Event(TEST_EVENT, TEST_EVENT, TEST_EVENT, TEST_EVENT, TEST_EVENT, TEST_USER_ID);
        event.setEventId(1);
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
        eventDAO = null;
        event = null;
    }

    /**
     * Implementation Test for the Event Table's Delete method.
     */
    @Test
    public void delete() {
        // Insert Event into Database
        eventDAO.insert(event);

        // Check that Event is in Database
        assertNotNull(eventDAO.getEventByEventIdSynchronous(event.getEventId()));

        // Delete Event
        eventDAO.delete(event);

        // Check that Event is NOT in Database
        assertNull(eventDAO.getEventByEventIdSynchronous(event.getEventId()));
    }

    /**
     * Implementation Test for the Event Table's Insert method.
     */
    @Test
    public void insert() {
        // Check that Event is NOT in Database
        assertNull(eventDAO.getEventByEventIdSynchronous(event.getEventId()));

        // Insert Event into Database
        eventDAO.insert(event);

        // Check that Event IS in Database
        Event dbEvent = eventDAO.getEventByEventIdSynchronous(event.getEventId());
        assertEquals(event, dbEvent);
    }

    /**
     * Implementation Test for the Event Table's getAllEventsByUserId method.
     */
    @Test
    public void getAllEventsByUserIdSynchronous() {
        // Insert Event into Database
        eventDAO.insert(event);

        // Get Event List from Database
        List<Event> eventList = eventDAO.getAllEventsByUserIdSynchronous(event.getUserId());

        // Check that Event List contains events with TEST_USER_ID.
        assertEquals(eventList.getFirst().getUserId(), event.getUserId());
    }

    /**
     * Implementation Test for the Event Table's getEventByEventId method.
     */
    @Test
    public void getEventByEventIdSynchronous() {
        // Insert Event into Database
        eventDAO.insert(event);

        // Check that Event IS in Database
        Event dbEvent = eventDAO.getEventByEventIdSynchronous(event.getEventId());
        assertEquals(event, dbEvent);
    }

    /**
     * Implementation Test for the Event Table's Insert method updating pre-existing events.
     */
    @Test
    public void update() {
        // Put Event in Database
        eventDAO.insert(event);
        Event eventOld = eventDAO.getEventByEventIdSynchronous(event.getEventId());

        // Update Event Entry in Database
        event.setUserId(TEST_USER_ID + 1);
        event.setName(TEST_EVENT + TEST_EVENT);
        eventDAO.insert(event);

        // Check old and new versions of Events
        Event eventNew = eventDAO.getEventByEventIdSynchronous(event.getEventId());
        assertNotEquals(eventOld, eventNew);
    }
}