/**
 * Title: Project 02: Activiti - Visited Places
 * File: VisitedPlacesDAO.java - Implementation of VisitedPlacesDAO
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 23 April 2025
 * Explanation/Abstract: DAO for the visited places table.
 */

package com.webcraftsolutions.project02.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.webcraftsolutions.project02.database.entities.TravelExploration;
import com.webcraftsolutions.project02.database.entities.VisitedPlaces;

import java.util.List;

@Dao
public interface VisitedPlacesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(VisitedPlaces... VisitedPlace);

    @Delete
    void delete(VisitedPlaces... VisitedPlace);

    @Update
    void update(VisitedPlaces... VisitedPlace);

    @Query("SELECT * FROM " + ActivitiDatabase.VISITED_PLACES_TABLE + " WHERE userId = :userId")
    LiveData<List<VisitedPlaces>> getVisitedPlacesForUser(int userId);

    @Query("SELECT * FROM " + ActivitiDatabase.VISITED_PLACES_TABLE + " WHERE userId = :userId")
    LiveData<List<VisitedPlaces>> getVisitedPlacesByUserId(int userId);

    @Query("SELECT * FROM " + ActivitiDatabase.VISITED_PLACES_TABLE + " WHERE id = :id")
    VisitedPlaces getVisitedPlaceByIdSynchronous(int id);

    @Query("SELECT * FROM " + ActivitiDatabase.VISITED_PLACES_TABLE + " WHERE name LIKE '%' || :name || '%' LIMIT 1")
    LiveData<VisitedPlaces> getVisitedPlaceByName(String name);

    @Query("SELECT * FROM " + ActivitiDatabase.VISITED_PLACES_TABLE + " WHERE Id = :Id")
    LiveData<VisitedPlaces> getById(int Id);
}
