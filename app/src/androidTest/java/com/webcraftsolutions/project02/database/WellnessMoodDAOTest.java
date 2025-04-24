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
}
