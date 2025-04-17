/**
 * Title: Project 02: Activiti - Event DAO
 * @author Noah deFer
 * Date Created: 4/9/2025
 * Description: DAO for event table.
 */
package com.webcraftsolutions.project02.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.webcraftsolutions.project02.database.entities.Event;

import java.util.List;

@Dao
public interface EventDAO {

    @Delete
    void delete(Event... event);

    @Query("DELETE FROM " + ActivitiDatabase.EVENT_TABLE + " WHERE userId = :userId")
    void deleteAllEventsByUserId(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event... event);

    @Query("SELECT * FROM " + ActivitiDatabase.EVENT_TABLE)
    LiveData<List<Event>> getAllEvents();

    @Query("SELECT * FROM " + ActivitiDatabase.EVENT_TABLE + " WHERE userId = :userId")
    LiveData<List<Event>> getAllEventsByUserId(int userId);

    @Query("SELECT * FROM " + ActivitiDatabase.EVENT_TABLE + " WHERE eventId = :eventId")
    Event getEventByEventId(int eventId);
}
