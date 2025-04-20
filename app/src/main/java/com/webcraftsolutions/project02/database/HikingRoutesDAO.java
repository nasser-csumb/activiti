package com.webcraftsolutions.project02.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.webcraftsolutions.project02.database.entities.HikingRoutes;

import java.util.List;

@Dao
public interface HikingRoutesDAO {

    @Insert
    void insert(HikingRoutes... hikingRoute);

    @Query("SELECT * FROM " + ActivitiDatabase.HIKING_ROUTES_TABLE + " WHERE userId = :userId")
    LiveData<List<HikingRoutes>> getHikingRoutesForUser(int userId);
}
