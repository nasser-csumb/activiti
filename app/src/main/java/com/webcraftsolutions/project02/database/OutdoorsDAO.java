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
    LiveData<List<HikingRoutes>> getOutdoorForUser(int userId);

    @Query("SELECT * FROM " + ActivitiDatabase.OUTDOORS_TABLE + " WHERE Id = :Id")
    HikingRoutes getOutdoorByIdSynchronous(int Id);
}
