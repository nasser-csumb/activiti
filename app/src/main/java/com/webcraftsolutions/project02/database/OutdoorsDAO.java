/**
 * Title: Project 02: Activiti - Outdoors
 * File: OutdoorsDAO.java - Implementation of OutdoorsDAO
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 23 April 2025
 * Explanation/Abstract: DAO for the oudoors table.
 */

package com.webcraftsolutions.project02.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.webcraftsolutions.project02.database.entities.HikingRoutes;
import com.webcraftsolutions.project02.database.entities.Outdoors;

import java.util.List;

@Dao
public interface OutdoorsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Outdoors... outdoors);

    @Delete
    void delete(Outdoors... outdoors);

    @Update
    void update(Outdoors... outdoors);

    @Query("SELECT * FROM " + ActivitiDatabase.OUTDOORS_TABLE + " WHERE userId = :userId")
    LiveData<List<Outdoors>> getOutdoorsForUser(int userId);

    @Query("SELECT * FROM " + ActivitiDatabase.OUTDOORS_TABLE + " WHERE Id = :Id")
    Outdoors getOutdoorByIdSynchronous(int Id);

    @Query("SELECT * FROM " + ActivitiDatabase.OUTDOORS_TABLE + " WHERE name LIKE '%' || :name || '%' LIMIT 1")
    LiveData<Outdoors> getOutdoorByName(String name);
}
