/**
 * Title: CST-338 Project 02: Activiti - WellnessJournalDAO Test
 * Author: Nasser Akhter
 * Date Created: 4/22/2025
 * Description: Unit tests for WellnessJournalDAO operations (insert, delete, get).
 */
package com.webcraftsolutions.project02.database;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.webcraftsolutions.project02.database.entities.WellnessJournal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class WellnessJournalDAOTest {

    private final int TEST_USER_ID = -300;

    private ActivitiDatabase db;
    private WellnessJournalDAO dao;
    private WellnessJournal entry;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ActivitiDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.wellnessJournalDAO();

        entry = new WellnessJournal(TEST_USER_ID, new Date(), "Gratitude", "Thankful for life");
    }

    @After
    public void tearDown() {
        db.close();
        entry = null;
    }

    @Test
    public void insertAndGetByUserId() {
        dao.insert(entry);
        List<WellnessJournal> result = dao.getAllEntriesByUserId(TEST_USER_ID);
        assertEquals(1, result.size());
        assertEquals("Gratitude", result.get(0).getTitle());
    }

    @Test
    public void insertAndGetById() {
        dao.insert(entry);
        int id = dao.getAllEntriesByUserId(TEST_USER_ID).get(0).getEntryId();
        WellnessJournal result = dao.getEntryById(id);
        assertNotNull(result);
        assertEquals("Thankful for life", result.getContent());
    }

    @Test
    public void insertMultipleAndGetAll() {
        dao.insert(
                new WellnessJournal(TEST_USER_ID, new Date(), "Mood", "Feeling good"),
                new WellnessJournal(TEST_USER_ID, new Date(), "Sleep", "Slept 8 hours")
        );
        List<WellnessJournal> result = dao.getAllEntries();
        assertEquals(2, result.size());
    }

    @Test
    public void deleteEntry() {
        dao.insert(entry);
        WellnessJournal inserted = dao.getAllEntries().get(0);
        dao.delete(inserted);
        List<WellnessJournal> result = dao.getAllEntriesByUserId(TEST_USER_ID);
        assertTrue(result.isEmpty());
    }
}
