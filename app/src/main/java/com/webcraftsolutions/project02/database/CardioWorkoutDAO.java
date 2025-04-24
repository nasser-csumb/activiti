/**
 * Title: Project 02: Activiti - CardioWorkout DAO
 * Author: Suhaib Peracha
 * Date Created: 4/15/2025
 * Description: DAO for the CardioWorkout table.
 */
package com.webcraftsolutions.project02.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.webcraftsolutions.project02.database.entities.CardioWorkout;

import java.util.List;

@Dao
public interface CardioWorkoutDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(CardioWorkout... workouts);

    @Delete
    void delete(CardioWorkout... workouts);

    @Query("SELECT * FROM " + ActivitiDatabase.CARDIO_TABLE)
    List<CardioWorkout> getAllCardioWorkouts();

    @Query("SELECT * FROM " + ActivitiDatabase.CARDIO_TABLE + " WHERE userId = :userId")
    List<CardioWorkout> getCardioWorkoutsByUserId(int userId);

    @Query("DELETE FROM " + ActivitiDatabase.CARDIO_TABLE + " WHERE userId = :userId")
    void deleteAllByUserId(int userId);

    @Query("SELECT * FROM " + ActivitiDatabase.CARDIO_TABLE + " WHERE entryId = :entryId LIMIT 1")
    CardioWorkout getCardioWorkoutById(int entryId);
}
