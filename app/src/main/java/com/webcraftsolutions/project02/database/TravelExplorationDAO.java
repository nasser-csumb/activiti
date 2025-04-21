/**
 * Title: Project 02: Activiti - Travel & Exploration
 * File: TravelExplorationDAO.java - Implementation of TravelExplorationDAO
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 13 April 2025
 * Explanation/Abstract: DAO for the travel exploration table.
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

import java.util.List;

@Dao
public interface TravelExplorationDAO {

    @Delete
    void delete(TravelExploration... travelExploration);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TravelExploration... travelExploration);

    @Query("SELECT * FROM " + ActivitiDatabase.TRAVEL_EXPLORATION_TABLE)
    List<TravelExploration> getAllTravelExplorations();

    @Query("SELECT * FROM " + ActivitiDatabase.TRAVEL_EXPLORATION_TABLE + " WHERE travelId = :travelId")
    TravelExploration getTravelExplorationById(int travelId);

    @Query("SELECT * FROM " + ActivitiDatabase.TRAVEL_EXPLORATION_TABLE)
    LiveData<List<TravelExploration>> getAllTravelExplorationsLive();
}
