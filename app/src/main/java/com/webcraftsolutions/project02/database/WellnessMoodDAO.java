/**
 * Title: Project 02: Activiti - WellnessMood DAO
 * Author: Nasser Akhter
 * Date Created: 4/11/2025
 * Description: DAO for wellness mood table.
 */
package com.webcraftsolutions.project02.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.webcraftsolutions.project02.database.entities.WellnessMood;

import java.util.List;

@Dao
public interface WellnessMoodDAO {

    @Delete
    void delete(WellnessMood... entries);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WellnessMood... entries);

    @Query("SELECT * FROM " + ActivitiDatabase.WELLNESS_MOOD_TABLE)
    List<WellnessMood> getAllEntries();

    @Query("SELECT * FROM " + ActivitiDatabase.WELLNESS_MOOD_TABLE + " WHERE userId = :userId")
    List<WellnessMood> getAllEntriesByUserId(int userId);

    @Query("SELECT * FROM " + ActivitiDatabase.WELLNESS_MOOD_TABLE + " WHERE entryId = :entryId")
    WellnessMood getEntryById(int entryId);
}
