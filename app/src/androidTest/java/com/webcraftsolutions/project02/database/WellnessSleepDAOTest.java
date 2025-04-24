/**
 * Title: CST-338 Project 02: Activiti - WellnessSleepDAO Test
 * Author: Nasser Akhter
 * Date Created: 4/22/2025
 * Description: Unit tests for WellnessSleepDAO operations (insert, delete, get).
 */
package com.webcraftsolutions.project02.database;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.WellnessSleep;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class WellnessSleepDAOTest {

    private final int TEST_USER_ID = -500;

    private ActivitiDatabase db;
    private WellnessSleepDAO dao;
    private WellnessSleep entry;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.wellnessSleepDAO();

        entry = new WellnessSleep(TEST_USER_ID, 7.5f, new Date(), true, false);
    }

    @After
    public void tearDown() {
        db.close();
        entry = null;
    }

    @Test
    public void insertAndGetByUserId() {
        dao.insert(entry);
        List<WellnessSleep> result = dao.getAllEntriesByUserId(TEST_USER_ID);
        assertEquals(1, result.size());
        assertTrue(result.get(0).isRefreshing());
    }

    @Test
    public void insertAndGetById() {
        dao.insert(entry);
        int id = dao.getAllEntriesByUserId(TEST_USER_ID).get(0).getEntryId();
        WellnessSleep result = dao.getEntryById(id);
        assertNotNull(result);
        assertEquals(7.5f, result.getDurationHours(), 0.01);
    }

    @Test
    public void insertMultipleAndGetAll() {
        dao.insert(
                new WellnessSleep(TEST_USER_ID, 6.0f, new Date(), false, true),
                new WellnessSleep(TEST_USER_ID, 8.0f, new Date(), true, false)
        );
        List<WellnessSleep> result = dao.getAllEntries();
        assertEquals(2, result.size());
    }

    @Test
    public void deleteEntry() {
        dao.insert(entry);
        WellnessSleep inserted = dao.getAllEntriesByUserId(TEST_USER_ID).get(0);
        dao.delete(inserted);
        List<WellnessSleep> result = dao.getAllEntriesByUserId(TEST_USER_ID);
        assertTrue(result.isEmpty());
    }
}
