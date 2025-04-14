/**
 * Title: Project 02: Activiti - Travel & Exploration
 * File: TravelExplorationDAO.java - Implementation of TravelExplorationDAO
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 13 April 2025
 * Explanation/Abstract: DAO for the travel exploration table.
 */

package com.webcraftsolutions.project02.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.webcraftsolutions.project02.database.entities.TravelExploration;

import java.util.List;

@Dao
public interface TravelExplorationDAO {

    @Insert
    void insert(TravelExploration travelExploration);

    @Delete
    void delete(TravelExploration travelExploration);

    @Update
    void update(TravelExploration travelExploration);

    @Query("SELECT * FROM TravelExploration_Table")
    List<TravelExploration> getAllTravelExplorations();

    @Query("SELECT * FROM TravelExploration_Table WHERE travelID = :travelID")
    TravelExploration getTravelExplorationById(int travelID);
}
