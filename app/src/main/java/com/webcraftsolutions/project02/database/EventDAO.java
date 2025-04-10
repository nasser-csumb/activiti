/**
 * Title: Project 02: Activiti - Event DAO
 * @author Noah deFer
 * Date Created: 4/9/2025
 * Description: DAO for event table.
 */
package com.webcraftsolutions.project02.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.webcraftsolutions.project02.database.entities.Event;

import java.util.ArrayList;

@Dao
public interface EventDAO {

    @Delete
    void delete(Event... event);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event... event);

    @Query("SELECT * FROM " + ActivitiDatabase.EVENT_TABLE)
    ArrayList<Event> getAllEvents();

    @Query("SELECT * FROM " + ActivitiDatabase.EVENT_TABLE + " WHERE userId == :userId")
    ArrayList<Event> getAllEventsByUserId(int userId);

    @Query("SELECT :eventId FROM " + ActivitiDatabase.EVENT_TABLE)
    ArrayList<Event> getEventByEventId(int eventId);
}
