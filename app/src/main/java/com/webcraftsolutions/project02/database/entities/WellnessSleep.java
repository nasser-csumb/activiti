/**
 * Title: Project 02: Activiti - WellnessSleep POJO
 * Author: Nasser Akhter
 * Date Created: 4/11/2025
 * Description: A POJO that stores sleep wellness entries.
 */
package com.webcraftsolutions.project02.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.webcraftsolutions.project02.database.ActivitiDatabase;
import com.webcraftsolutions.project02.database.DateConverter;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = ActivitiDatabase.WELLNESS_SLEEP_TABLE)
public class WellnessSleep {
    // FIELDS
    @PrimaryKey(autoGenerate = true)
    private int entryId;
    private int userId;
    private float durationHours;
    //    private Date startTime;
//    private Date endTime;
    @TypeConverters(DateConverter.class)
    private Date date;
    private boolean refreshing;
    private boolean restless;

    // CONSTRUCTOR
    public WellnessSleep(int userId, float durationHours, Date date, boolean refreshing, boolean restless) {
        this.userId = userId;
        this.durationHours = durationHours;
//        this.startTime = startTime;
//        this.endTime = endTime;
        this.date = date;
        this.refreshing = refreshing;
        this.restless = restless;
    }

    // GETTERS
    public int getEntryId() {
        return entryId;
    }

    public int getUserId() {
        return userId;
    }

    public float getDurationHours() {
        return durationHours;
    }

//    public Date getStartTime() {
//        return startTime;
//    }
//
//    public Date getEndTime() {
//        return endTime;
//    }

    public Date getDate() {
        return date;
    }

    public boolean isRefreshing() {
        return refreshing;
    }

    public boolean isRestless() {
        return restless;
    }

    // SETTERS
    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDurationHours(float durationHours) {
        this.durationHours = durationHours;
    }

//    public void setStartTime(Date startTime) {
//        this.startTime = startTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRefreshing(boolean refreshing) {
        this.refreshing = refreshing;
    }

    public void setRestless(boolean restless) {
        this.restless = restless;
    }

    @Override
    public String toString() {
        return "Duration: " + durationHours + "hrs\n" +
                "Refreshing: " + (refreshing ? "Yes" : "No") + "\n" +
                "Restless: " + (restless ? "Yes" : "No") + "\n";
    }

    // EQUALS & HASHCODE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WellnessSleep that = (WellnessSleep) o;
        return entryId == that.entryId &&
                userId == that.userId &&
                Float.compare(that.durationHours, durationHours) == 0 &&
                refreshing == that.refreshing &&
                restless == that.restless &&
//                Objects.equals(startTime, that.startTime) &&
//                Objects.equals(endTime, that.endTime) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId, userId, durationHours, date, refreshing, restless);
    }
}
