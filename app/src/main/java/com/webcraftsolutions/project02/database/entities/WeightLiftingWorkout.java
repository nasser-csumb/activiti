/**
 * Title: Project 02: Activiti - WeightLiftingWorkout Entity
 * Author: Suhaib Peracha
 * Date Created: 4/15/2025
 * Description: A POJO that stores weightlifting workout data.
 */
package com.webcraftsolutions.project02.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import com.webcraftsolutions.project02.database.ActivitiDatabase;

import java.util.Date;

@Entity(tableName = ActivitiDatabase.LIFTING_TABLE)
public class WeightLiftingWorkout {

    @PrimaryKey(autoGenerate = true)
    private int entryId;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "exerciseName")
    private String exerciseName;

    @ColumnInfo(name = "sets")
    private int sets;

    @ColumnInfo(name = "totalReps")
    private int totalReps;

    @ColumnInfo(name = "durationMinutes")
    private int durationMinutes;

    @ColumnInfo(name = "date")
    private Date date;

    // CONSTRUCTOR
    public WeightLiftingWorkout(int userId, String exerciseName, int sets, int totalReps, int durationMinutes, Date date) {
        this.userId = userId;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.totalReps = totalReps;
        this.durationMinutes = durationMinutes;
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

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getTotalReps() {
        return totalReps;
    }

    public void setTotalReps(int totalReps) {
        this.totalReps = totalReps;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
