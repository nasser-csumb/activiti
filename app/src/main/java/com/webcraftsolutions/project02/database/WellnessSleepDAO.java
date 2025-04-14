/**
 * Title: Project 02: Activiti - WellnessSleep DAO
 * Author: Nasser Akhter
 * Date Created: 4/11/2025
 * Description: DAO for wellness sleep table.
 */
package com.webcraftsolutions.project02.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.webcraftsolutions.project02.database.entities.WellnessSleep;

import java.util.List;

@Dao
public interface WellnessSleepDAO {

    @Delete
    void delete(WellnessSleep... entries);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WellnessSleep... entries);

    @Query("SELECT * FROM " + ActivitiDatabase.WELLNESS_SLEEP_TABLE)
    List<WellnessSleep> getAllEntries();

    @Query("SELECT * FROM " + ActivitiDatabase.WELLNESS_SLEEP_TABLE + " WHERE userId = :userId")
    List<WellnessSleep> getAllEntriesByUserId(int userId);

    @Query("SELECT * FROM " + ActivitiDatabase.WELLNESS_SLEEP_TABLE + " WHERE entryId = :entryId")
    WellnessSleep getEntryById(int entryId);
}
