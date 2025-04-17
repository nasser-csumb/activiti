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
import com.webcraftsolutions.project02.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class EventDAOTest {

    // CONSTANT FIELDS

    // User Username/Password String
    private String TEST_EVENT = "TEST_EVENT";
    private int TEST_USER_ID = -1;

    // FIELDS

    // Database Object
    private ActivitiDatabase db;

    // Event DAO Object
    private EventDAO eventDAO;

    // Event Object
    Event event;

    @Before
    public void setUp() throws Exception {
        // Get Database
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class).build();
        // Get UserDAO
        eventDAO = db.eventDAO();

        // Get User
        event = new Event(TEST_EVENT, TEST_EVENT, TEST_EVENT, TEST_EVENT, TEST_USER_ID);
        event.setEventId(1);
    }

    @After
    public void tearDown() throws Exception {
        // Close Database
        db.close();

        // Invalidate Variables
        eventDAO = null;
        event = null;
    }

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

    @Test
    public void getAllEventsByUserIdSynchronous() {
        // Insert Event into Database
        eventDAO.insert(event);

        // Get Event List from Database
        List<Event> eventList = eventDAO.getAllEventsByUserIdSynchronous(event.getUserId());

        // Check that Event List contains events with TEST_USER_ID.
        assertEquals(eventList.getFirst().getUserId(), event.getUserId());
    }

    @Test
    public void getEventByEventIdSynchronous() {
        // Insert Event into Database
        eventDAO.insert(event);

        // Check that Event IS in Database
        Event dbEvent = eventDAO.getEventByEventIdSynchronous(event.getEventId());
        assertEquals(event, dbEvent);
    }

    @Test
    public void update() {
        // Put Event in Database
        eventDAO.insert(event);
        Event eventOld = eventDAO.getEventByEventIdSynchronous(event.getEventId());

        // Update Event Entry in Database
        event.setUserId(TEST_USER_ID + 1);
        eventDAO.insert(event);

        // Check old and new versions of Events
        Event eventNew = eventDAO.getEventByEventIdSynchronous(event.getEventId());
        assertNotEquals(eventOld, eventNew);
    }
}