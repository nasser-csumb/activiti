/**
 * Title: CST-338 Project 02: Activiti - WellnessMoodDAO Test
 * Author: Nasser Akhter
 * Date Created: 4/22/2025
 * Description: Unit tests for WellnessMoodDAO operations (insert, delete, get).
 */
package com.webcraftsolutions.project02.database;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.WellnessMood;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class WellnessMoodDAOTest {

    private final int TEST_USER_ID = -400;

    private ActivitiDatabase db;
    private WellnessMoodDAO dao;
    private WellnessMood entry;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.wellnessMoodDAO();

        entry = new WellnessMood(TEST_USER_ID, new Date(), "Happy", 8);
    }

    @After
    public void tearDown() {
        db.close();
        entry = null;
    }

    @Test
    public void insertAndGetByUserId() {
        dao.insert(entry);
        List<WellnessMood> result = dao.getAllEntriesByUserId(TEST_USER_ID);
        assertEquals(1, result.size());
        assertEquals("Happy", result.get(0).getMoodLabel());
    }

    @Test
    public void insertAndGetById() {
        dao.insert(entry);
        int id = dao.getAllEntriesByUserId(TEST_USER_ID).get(0).getEntryId();
        WellnessMood result = dao.getEntryById(id);
        assertNotNull(result);
        assertEquals(8, result.getEnergyLevel());
    }

    @Test
    public void insertMultipleAndGetAll() {
        dao.insert(
                new WellnessMood(TEST_USER_ID, new Date(), "Relaxed", 7),
                new WellnessMood(TEST_USER_ID, new Date(), "Tired", 3)
        );
        List<WellnessMood> result = dao.getAllEntries();
        assertEquals(2, result.size());
    }

    @Test
    public void deleteEntry() {
        dao.insert(entry);
        WellnessMood inserted = dao.getAllEntriesByUserId(TEST_USER_ID).get(0);
        dao.delete(inserted);
        List<WellnessMood> result = dao.getAllEntriesByUserId(TEST_USER_ID);
        assertTrue(result.isEmpty());
    }
}
