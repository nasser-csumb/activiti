package com.webcraftsolutions.project02.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
}
