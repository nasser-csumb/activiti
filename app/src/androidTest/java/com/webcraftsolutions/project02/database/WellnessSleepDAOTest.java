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
}
