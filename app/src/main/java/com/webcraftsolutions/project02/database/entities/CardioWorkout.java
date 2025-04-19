/**
 * Title: Project 02: Activiti - CardioWorkout Entity
 * Author: Suhaib Peracha
 * Date Created: 4/15/2025
 * Description: A POJO that stores cardio workout data.
 */
package com.webcraftsolutions.project02.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import com.webcraftsolutions.project02.database.ActivitiDatabase;

import java.util.Date;

@Entity(tableName = ActivitiDatabase.CARDIO_TABLE)
public class CardioWorkout {

    @PrimaryKey(autoGenerate = true)
    private int entryId;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "durationMinutes")
    private int durationMinutes;

    @ColumnInfo(name = "intensity")
    private String intensity;

    @ColumnInfo(name = "date")
    private Date date;

    // CONSTRUCTOR
    public CardioWorkout(int userId, String type, int durationMinutes, String intensity, Date date) {
        this.userId = userId;
        this.type = type;
        this.durationMinutes = durationMinutes;
        this.intensity = intensity;
        this.date = date;
    }

    // GETTERS AND SETTERS

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
