/**
 * Title: Project 02: Activiti - Hiking Routes
 * File: HikingRoutesDAO.java - Implementation of HikingRoutesDAO
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 23 April 2025
 * Explanation/Abstract: DAO for the hikingRoutes table.
 */

package com.webcraftsolutions.project02.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.webcraftsolutions.project02.database.entities.Event;
import com.webcraftsolutions.project02.database.entities.HikingRoutes;
import com.webcraftsolutions.project02.database.entities.VisitedPlaces;

import java.util.List;

@Dao
public interface HikingRoutesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HikingRoutes... hikingRoute);

    @Update
    void update(HikingRoutes... hikingRoute);

    @Delete
    void delete(HikingRoutes... hikingRoute);

    @Query("SELECT * FROM " + ActivitiDatabase.HIKING_ROUTES_TABLE + " WHERE userId = :userId")
    LiveData<List<HikingRoutes>> getHikingRoutesForUser(int userId);

    @Query("SELECT * FROM " + ActivitiDatabase.HIKING_ROUTES_TABLE + " WHERE Id = :Id")
    HikingRoutes getHikingRouteByIdSynchronous(int Id);

    @Query("SELECT * FROM " + ActivitiDatabase.HIKING_ROUTES_TABLE + " WHERE name LIKE '%' || :name || '%' LIMIT 1")
    LiveData<HikingRoutes> getHikingRouteByName(String name);
}
