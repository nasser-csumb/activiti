/**
 * Title: Project 02: Activiti - WellnessJournal DAO
 * Author: Nasser Akhter
 * Date Created: 4/11/2025
 * Description: DAO for wellness journal table.
 */
package com.webcraftsolutions.project02.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.webcraftsolutions.project02.database.entities.WellnessJournal;

import java.util.List;

@Dao
public interface WellnessJournalDAO {

    @Delete
    void delete(WellnessJournal... entries);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WellnessJournal... entries);

    @Query("SELECT * FROM " + ActivitiDatabase.WELLNESS_JOURNAL_TABLE)
    List<WellnessJournal> getAllEntries();

    @Query("SELECT * FROM " + ActivitiDatabase.WELLNESS_JOURNAL_TABLE + " WHERE userId = :userId")
    List<WellnessJournal> getAllEntriesByUserId(int userId);

    @Query("SELECT * FROM " + ActivitiDatabase.WELLNESS_JOURNAL_TABLE + " WHERE entryId = :entryId")
    WellnessJournal getEntryById(int entryId);
}
