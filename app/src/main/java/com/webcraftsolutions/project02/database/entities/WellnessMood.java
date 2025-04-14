/**
 * Title: Project 02: Activiti - WellnessMood POJO
 * Author: Nasser Akhter
 * Date Created: 4/11/2025
 * Description: A POJO that stores mood wellness entries.
 */
package com.webcraftsolutions.project02.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.webcraftsolutions.project02.database.ActivitiDatabase;
import com.webcraftsolutions.project02.database.DateConverter;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = ActivitiDatabase.WELLNESS_MOOD_TABLE)
public class WellnessMood {
    // FIELDS
    @PrimaryKey(autoGenerate = true)
    private int entryId;

    private int userId;

    @TypeConverters(DateConverter.class)
    private Date date;

    private String moodLabel;

    private int energyLevel;

    // CONSTRUCTOR
    public WellnessMood(int userId, Date date, String moodLabel, int energyLevel) {
        this.userId = userId;
        this.date = date;
        this.moodLabel = moodLabel;
        this.energyLevel = energyLevel;
    }

    // GETTERS
    public int getEntryId() {
        return entryId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public String getMoodLabel() {
        return moodLabel;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    // SETTERS
    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMoodLabel(String moodLabel) {
        this.moodLabel = moodLabel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    // EQUALS & HASHCODE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WellnessMood that = (WellnessMood) o;
        return entryId == that.entryId &&
                userId == that.userId &&
                energyLevel == that.energyLevel &&
                Objects.equals(date, that.date) &&
                Objects.equals(moodLabel, that.moodLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId, userId, date, moodLabel, energyLevel);
    }
}
