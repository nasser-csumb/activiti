/**
 * Title: Project 02: Activiti - WeightLiftingWorkout DAO
 * Author: Suhaib Peracha
 * Date Created: 4/15/2025
 * Description: DAO for the WeightLiftingWorkout table.
 */
package com.webcraftsolutions.project02.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.webcraftsolutions.project02.database.entities.WeightLiftingWorkout;
import com.webcraftsolutions.project02.database.ActivitiDatabase;

import java.util.List;

@Dao
public interface WeightLiftingWorkoutDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeightLiftingWorkout... workouts);

    @Delete
    void delete(WeightLiftingWorkout... workouts);

    @Query("SELECT * FROM " + ActivitiDatabase.LIFTING_TABLE)
    List<WeightLiftingWorkout> getAllWeightLiftingWorkouts();

    @Query("SELECT * FROM " + ActivitiDatabase.LIFTING_TABLE + " WHERE userId = :userId")
    List<WeightLiftingWorkout> getWeightLiftingWorkoutsByUserId(int userId);
}
